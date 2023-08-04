package com.digit.javaTraining.MVCApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVCApp.model.BankApp;

@WebServlet("/changepasswordController")
public class changepasswordController extends HttpServlet{
	@Override
	public void service(HttpServletRequest req,HttpServletResponse resp)  throws ServletException, IOException{
		BankApp bankapp=new BankApp();
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");
		bankapp.setPin(Integer.parseInt(req.getParameter("oldpassword")));
		int newpassword=Integer.parseInt(req.getParameter("newpassword"));
		int renewpassword=Integer.parseInt(req.getParameter("renewpassword"));
		if(renewpassword==newpassword)
		{
			boolean b=bankapp.changePassword(accno,newpassword);
			if(b==true)
			{
				resp.sendRedirect("passwordchangesuccess.html");
			}
			else
			{
				resp.sendRedirect("passwordchangefailure.html");
			}
		}
		
		
	}

}
