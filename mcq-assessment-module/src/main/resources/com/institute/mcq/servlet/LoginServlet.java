package com.institute.mcq.servlet;

import com.institute.mcq.dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            boolean ok = UserDAO.validateLogin(username, password);
            if (ok) {
                int userId = UserDAO.getUserId(username);
                HttpSession s = req.getSession(true);
                s.setAttribute("userId", userId);
                s.setAttribute("username", username);
                resp.sendRedirect(req.getContextPath() + "/jsp/dashboard.jsp");
            } else {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
