package com.digit.javaTraining.MVCApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVCApp.model.transferstatus;
@WebServlet("/TransferController")
public class TransferController extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		HttpSession session=req.getSession();
        transferstatus trans=new transferstatus();
        trans.setCust_id(Integer.parseInt(req.getParameter("cust_id")));
        trans.setBank_name(req.getParameter("bank_name"));
        trans.setIFSC(req.getParameter("IFSC"));
        trans.setSender_accno(Integer.parseInt(req.getParameter("sender_accno")));
        trans.setReceiver_IFSC(req.getParameter("receiver_IFSC"));
        trans.setReceiver_accno(Integer.parseInt(req.getParameter("receiver_accno")));
        trans.setAmount(Integer.parseInt(req.getParameter("amount")));
        int pin=Integer.parseInt(req.getParameter("pin"));
        boolean b=trans.transfer(pin);

        if(b==true) {
            session.setAttribute("transfer_id",trans.getTransfer_id());
            session.setAttribute("sender_accno",trans.getSender_accno());
            session.setAttribute("receiver_accno",trans.getReceiver_accno());
            session.setAttribute("amount",trans.getAmount());
            resp.sendRedirect("TransferSuccess.html");
        }
        else {
        	session.setAttribute("message", "There is an Error in code. Please Check.");
        	resp.sendRedirect("transferfail.jsp");
        }
	}
}
