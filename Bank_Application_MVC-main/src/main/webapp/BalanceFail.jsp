<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

  h4 {
    color: #3366cc;
    margin-bottom: 20px;
  }

  a {
    display: inline-block;
    padding: 10px 20px;
    background-color: #3366cc;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    font-weight: bold;
    transition: background-color 0.3s ease;
  }

  a:hover {
    background-color: #005580;
  }
</style>
</head>
<body>
	<div class="container">
    <h4>
      <%
      session=request.getSession();
      out.print("Dear " + session.getAttribute("cust_name") + ", your balance could not be fetched.");
      %>
    </h4>
    <a href="HomePage.jsp">Click here to try again</a>
  </div>
</body>
</html>