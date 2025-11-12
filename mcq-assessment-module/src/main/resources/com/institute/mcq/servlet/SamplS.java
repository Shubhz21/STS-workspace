package com.institute.mcq.servlet;

import com.institute.mcq.dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class SampleS extends GenericServlet {
	
	public void service(ServletRequest req, ServletResponse res) {
		
		resp.sendRedirect(req.getContextPath() + "/jsp/dashboard.jsp");
		
	}
	
}