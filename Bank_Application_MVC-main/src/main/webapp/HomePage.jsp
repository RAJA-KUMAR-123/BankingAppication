<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-size: cover;
    background-position: center;
  }

  .container {
    max-width: 400px;
    padding: 30px;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 10px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    text-align: center;
  }

  h1 {
    color: #3366cc;
    margin-bottom: 20px;
  }

  p {
    margin-bottom: 20px;
  }

  a {
    display:block;
    margin: 10px 0;
    padding: 10px 20px;
    background-color: #3366cc;
    color: #fff;
    text-decoration: none;
    border-radius: 4px;
    font-weight: bold;
    transition: background-color 0.4s ease;
  }

  a:hover {
    background-color: #005580;
  }
</style>
<body>
<div class="container">
	<h1>Welcome to the Bank </h1>
	<%
		session=request.getSession();
		String s1=(String)session.getAttribute("cust_name");
		out.println(s1+",   Welcome to your account. Please select an operation to perform");
	%>
	<br>
	<br>
	<a href="CheckBalanceController">1. Check Balance</a>
	<a href="changepassword.html"> 2. Change Password</a>
	<a href="Loan.jsp">3. Apply Loan</a>
	<a href="Transfer.html">4. Transfer Amount</a>
	<a href="CheckTransaction.jsp"> 5. Check Transaction Details</a>
	<a href="LogoutController">5. Logout</a>

</div>
</body>
</html>