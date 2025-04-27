<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%
 synchronized (application) {
 Integer userCount = (Integer) application.getAttribute("userCount");
 if (userCount != null && userCount > 0) {
 userCount--;
 application.setAttribute("userCount", userCount);
 }
 }

 session.invalidate();
 response.sendRedirect("index.jsp");
%>