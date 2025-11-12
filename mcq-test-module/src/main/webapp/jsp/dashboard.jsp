<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
  if (session.getAttribute("userId") == null) {
    response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
    return;
  }
%>
<html>
<head><title>Dashboard</title></head>
<body>
  <h2>Welcome ${session.username}</h2>
  <h3>Available Exams</h3>
  <!-- TODO: list exams from DB -->
  <!-- For demo, let user enter exam id -->
  <form id="startForm">
    <label>Exam ID to start: <input id="examId" name="examId" /></label>
    <button type="button" onclick="startExam()">Start</button>
  </form>

  <script>
    function startExam() {
      const examId = document.getElementById('examId').value;
      fetch('${pageContext.request.contextPath}/startExam', {
        method: 'POST',
        headers: {'Content-Type':'application/x-www-form-urlencoded'},
        body: 'examId=' + encodeURIComponent(examId)
      }).then(r => r.json()).then(j => {
        // j.attemptId and j.endTimeMillis
        sessionStorage.setItem('attemptId', j.attemptId);
        sessionStorage.setItem('endTime', j.endTimeMillis);
        window.location = '${pageContext.request.contextPath}/jsp/exam_take.jsp';
      }).catch(e => alert('Could not start exam: ' + e));
    }
  </script>
</body>
</html>
