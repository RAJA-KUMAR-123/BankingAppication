<%@page import="com.digit.javaTraining.MVCApp.model.BankApp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance</title>
<style type="text/css">
body {
	margin: 0;
	padding-top: 100px;
}

.submit-area {
	padding: 30px;
	margin-top: 50px;
	border-radius: 10px;
	box-shadow: 5px 5px gray;
  color: white;
  background-color: royalblue;
}

#account-area {
	margin-top: 5%;
}

.balance {
	background-color: tomato;
}

.status {
	margin: 0 20px;
	color: white;
	padding: 20px;
	border-radius: 10px;
}

</style>
</head>
<body>
<%
session=request.getSession();
%>
<div id="account-area">
        <div class="container">
            <div class="row">
              
                <div class="col-lg-4 mb-3">
                    <div class="balance status">
                        <h5>Balance:</h5>
                        <h2>Rs-/ <span id="current-balance"><%=session.getAttribute("balance") %></span></h2>
                    </div>
                 </div>
            </div>
        </div>
    </div>
</body>
</html>