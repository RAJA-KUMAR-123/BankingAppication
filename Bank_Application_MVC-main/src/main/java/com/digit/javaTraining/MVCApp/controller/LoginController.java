package com.digit.javaTraining.MVCApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVCApp.model.BankApp;
@WebServlet("/LoginController")
public class LoginController extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		BankApp bankapp=new BankApp();
		HttpSession session=req.getSession(true);
		bankapp.setCust_id(Integer.parseInt(req.getParameter("cust_id")));
		bankapp.setPin(Integer.parseInt(req.getParameter("pin")));
		boolean b=bankapp.login();
		if(b==true)
		{	session.setAttribute("accno",bankapp.getAccno());
			session.setAttribute("cust_name",bankapp.getCust_name());
			resp.sendRedirect("HomePage.jsp");
		}
		else
		{
			resp.sendRedirect("LoginFail.html");
		}
		
	}

}
