<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/tlds/sortNumbers.tld" prefix="ct" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sort Numbers Using Custom JSP Tag</title>
</head>
<body>
    <h2>Enter 10 numbers (comma-separated):</h2>
    <form method="post">
        <input type="text" name="numbers" required>
        <select name="order">
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>
        <input type="submit" value="Sort">
    </form>

    <%
        String numbers = request.getParameter("numbers");
        String order = request.getParameter("order");

        if (numbers != null && !numbers.isEmpty()) {
    %>
        <h3>Sorted Output:</h3>
        <ct:sortNumbers numbers="<%= numbers %>" order="<%= order %>" />
    <%
        }
    %>
</body>
</html>
