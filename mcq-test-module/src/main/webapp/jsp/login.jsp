<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>
<body>
  <h2>Login</h2>
  <form method="post" action="${pageContext.request.contextPath}/sample">
    <label>Username: <input type="text" name="username" required></label><br/>
 
    <label>Password: <input type="password" name="password" required></label><br/>
    <button type="submit">Login</button>
  </form>
  <div style="color:red">${requestScope.error}</div>
</body>

</html>
