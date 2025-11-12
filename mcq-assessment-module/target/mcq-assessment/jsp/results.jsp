<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
  if (session.getAttribute("userId") == null) {
    response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
    return;
  }
  String attemptId = request.getParameter("attemptId");
%>
<html>
<head><title>Results</title></head>
<body>
  <h2>Results (Attempt: <%= attemptId %>)</h2>
  <!-- Fetch attempt score from DB and display -->
</body>
</html>
