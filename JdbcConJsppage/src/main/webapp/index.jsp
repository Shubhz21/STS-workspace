<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=java.sql.* %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>

<%
String url="jdbc:oracle:thin:@localhost:1521:xe";
String user="system";
String key="admin";

try{

Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(url,user,key);

Statement st = con.createStatement();

Resultset rs = st.execute("select * from fruits");



%>

<% catch(Exception e){
	e.printStackTrace();
}
	%>

<table>
<thead>
<tr>
<th>FruitName</th><th>Quantity</th>
</tr>
</thead>
<tbody>
<%

while(rs.next()){

%>
<tr>
<td><%= rs.getString(1) %></td><td><%= rs.getInt(2) %></td>
</tr>
<%
} 
%>
</tbody>
</table>




</body>
</html>