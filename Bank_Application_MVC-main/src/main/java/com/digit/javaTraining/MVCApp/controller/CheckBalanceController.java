package com.digit.javaTraining.MVCApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVCApp.model.BankApp;

@WebServlet("/CheckBalanceController")
public class CheckBalanceController extends HttpServlet{
	@Override
	public void service(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
		BankApp bankapp=new BankApp();
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");
		boolean b=bankapp.checkBalance(accno);
		if(b==true)
		{
			session.setAttribute("balance", bankapp.getBalance());
			resp.sendRedirect("Balance.jsp");
		}
		else
		{
			resp.sendRedirect("BalanceFail.jsp");
		}
		
	}

}
