<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Details</title>
</head>
<body align="center">
  <h1>Welcome to the Bank</h2>
  <%
    session = request.getSession();
    String s1=(String)session.getAttribute("cust_name");
    out.println(s1+" welcome to your account.Please selectan operation to perform.");
    
  %>
  <br>
  <br>
  <a href="CheckBalance">1. Check Balance</a><br>
  <a href="changePwd.html">2. Change password</a><br>
  <a href="Loan.jsp">3. Apply Loan</a><br>
  <a href="Logtout">4. Logout</a><br>
  <a href="transfer.html">5.Transfer</a>
</body>
</html>