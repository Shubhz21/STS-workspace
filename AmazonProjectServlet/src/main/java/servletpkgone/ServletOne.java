package servletpkgone;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ServletOne")
public class ServletOne extends GenericServlet {
	
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String data = req.getParameter("box1");
		
		
		PrintWriter pw = res.getWriter();
		
		pw.println("<br/><br/><br/><center><h2>value of box 1 is : "+data+" </h2></center>");
		
	}

	

}
