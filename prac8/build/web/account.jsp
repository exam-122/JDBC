<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="account" class="beans.BankAccount" scope="session" />
<jsp:setProperty name="account" property="*" />

<!DOCTYPE html>
<html>
<head>
    <title>Bank Account Details</title>
</head>
<body>
    <h2>Account Details</h2>
    <p><strong>Account Holder:</strong> <jsp:getProperty name="account" property="accountHolder" /></p>
    <p><strong>Account Number:</strong> <jsp:getProperty name="account" property="accountNumber" /></p>
    <p><strong>Balance:</strong> <jsp:getProperty name="account" property="balance" /></p>

    <h2>Perform Transactions</h2>
    <form action="account.jsp" method="post">
        <label>Amount:</label>
        <input type="number" name="amount" step="0.01" required><br>

        <button type="submit" name="action" value="deposit">Deposit</button>
        <button type="submit" name="action" value="withdraw">Withdraw</button>
    </form>

    <%
        String action = request.getParameter("action");
        String amountStr = request.getParameter("amount");

        if (action != null && amountStr != null) {
            double amount = Double.parseDouble(amountStr);
            
            if ("deposit".equals(action)) {
                account.deposit(amount);
                out.println("<p style='color:green;'>Amount Deposited: " + amount + "</p>");
            } else if ("withdraw".equals(action)) {
                if (account.withdraw(amount)) {
                    out.println("<p style='color:green;'>Amount Withdrawn: " + amount + "</p>");
                } else {
                    out.println("<p style='color:red;'>Insufficient Balance!</p>");
                }
            }
        }
    %>
</body>
</html>