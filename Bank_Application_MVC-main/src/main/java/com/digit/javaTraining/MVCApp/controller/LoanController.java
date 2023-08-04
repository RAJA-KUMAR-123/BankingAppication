package com.digit.javaTraining.MVCApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVCApp.model.ApplyLoan;
import com.digit.javaTraining.MVCApp.model.BankApp;
@WebServlet("/LoanController")
public class LoanController extends HttpServlet {
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		ApplyLoan applyloan=new ApplyLoan();
		HttpSession session=req.getSession();
		String loan_name=req.getParameter("text");
		boolean b=applyloan.aloan(loan_name);
		if(b==true)
		{
			session.setAttribute("lid",applyloan.getLid());

            session.setAttribute("l_type",applyloan.getL_type());

            session.setAttribute("tenure",applyloan.getTenure());

            session.setAttribute("interest",applyloan.getInterest());

            session.setAttribute("description",applyloan.getDescription());
			resp.sendRedirect("LoanDetails.jsp");
		}
		else
		{
			resp.sendRedirect("LoanFail.html");
		}
		
	}
	

}
