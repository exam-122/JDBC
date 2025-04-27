<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@ page import="jakarta.servlet.http.*" %>
<%
 // Synchronize to avoid race conditions
 synchronized (application) {
 Integer userCount = (Integer) application.getAttribute("userCount");
 if (userCount == null) {
 userCount = 0;
 }

 if (userCount >= 3) {
 response.sendRedirect("error.jsp");
 return;
 }
 // Increment user count and store in application scope
 userCount++;
 application.setAttribute("userCount", userCount);
 // Store user session to track logout
 session.setAttribute("loggedIn", true);
 }
%>
<!DOCTYPE html>
<html>
<head>
 <title>JSP Application</title>
</head>
<body>
 <h2>Welcome to the JSP Application</h2>
 <p>You have successfully logged in.</p>
 <a href="logout.jsp">Logout</a>
</body>
</html>
