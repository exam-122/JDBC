<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession();
    String name = (String) sessionObj.getAttribute("name");
    String id = (String) sessionObj.getAttribute("id");
    String department = (String) sessionObj.getAttribute("department");
    String result = (String) sessionObj.getAttribute("result");

    
    if (result == null) {
        result = "Fail";  
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
    <style>
        .pass { color: green; font-weight: bold; }
        .fail { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>Student Details</h2>
    <p>Name: <%= name != null ? name : "Unknown" %></p>
    <p>ID: <%= id != null ? id : "Unknown" %></p>
    <p>Department: <%= department != null ? department : "Unknown" %></p>

    <h3>Result:</h3>
    <p class="<%= ("Pass".equals(result)) ? "pass" : "fail" %>"><%= result %></p>
</body>
</html>
