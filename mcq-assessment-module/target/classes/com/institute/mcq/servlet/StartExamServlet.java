package com.institute.mcq.servlet;

import com.institute.mcq.dao.ExamDAO;
import com.institute.mcq.util.DBUtil;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/startexam")
public class StartExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int examId = Integer.parseInt(req.getParameter("examId"));
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        int userId = (int) session.getAttribute("userId");
        try (Connection conn = DBUtil.getConnection()) {
            // Optional: check IP restriction (left as exercise)
            String insert = "INSERT INTO attempts (user_id, exam_id, started_at, status) VALUES (?,?,NOW(),'in_progress')";
            try (PreparedStatement ps = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setInt(2, examId);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int attemptId = rs.getInt(1);
                        Integer duration = ExamDAO.getDurationMinutes(examId);
                        if (duration == null) duration = 60; // fallback
                        // optionally save end_time in attempts (not shown)
                        long endMillis = System.currentTimeMillis() + duration * 60L * 1000L;
                        JSONObject o = new JSONObject();
                        o.put("attemptId", attemptId);
                        o.put("endTimeMillis", endMillis);
                        resp.setContentType("application/json");
                        resp.getWriter().write(o.toString());
                        return;
                    }
                }
            }
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
