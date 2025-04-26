<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="account" class="beans.BankAccount" scope="session" />
<jsp:setProperty name="account" property="*" />

<!DOCTYPE html>
<html>
<head>
    <title>Bank Account Operations</title>
</head>
<body>
    <h2>Bank Account Information</h2>
    <form action="account.jsp" method="post">
        <label>Account Holder:</label>
        <input type="text" name="accountHolder" required><br>

        <label>Account Number:</label>
        <input type="text" name="accountNumber" required><br>

        <label>Initial Balance:</label>
        <input type="number" name="balance" step="0.01" required><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>