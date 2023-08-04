package com.digit.javaTraining.MVCApp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

public class transferstatus{
	int transfer_id;
	int cust_id;
	String bank_name;
	String IFSC;
	int sender_accno;
	String receiver_IFSC;
	int receiver_accno;
	float amount;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private ResultSet res1;
	private ResultSet res3;
	private ResultSet res4;
	public int getTransfer_id() {
		return transfer_id;
	}
	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getIFSC() {
		return IFSC;
	}
	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}
	public int getSender_accno() {
		return sender_accno;
	}
	public void setSender_accno(int sender_accno) {
		this.sender_accno = sender_accno;
	}
	public String getReceiver_IFSC() {
		return receiver_IFSC;
	}
	public void setReceiver_IFSC(String receiver_IFSC) {
		this.receiver_IFSC = receiver_IFSC;
	}
	public int getReceiver_accno() {
		return receiver_accno;
	}
	public void setReceiver_accno(int receiver_accno) {
		this.receiver_accno = receiver_accno;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public transferstatus() {

		String url = "jdbc:mysql://localhost:3306/bank_mvc";

		String user = "root";

		String pwd = "root";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public boolean transfer(int pin) {
		try {
			pstmt=con.prepareStatement("select *from BankApp where cust_id=? and ifsc_code=? and accno=? and pin=?");
			pstmt.setInt(1,cust_id);
			pstmt.setString(2,IFSC);
			pstmt.setInt(3,sender_accno);
			pstmt.setInt(4,pin);
			res1=pstmt.executeQuery();
			//1
			if(res1.next()==true) {
				//2.receiver ifsc and accno
				pstmt=con.prepareStatement("select* from BankApp where ifsc_code=? and accno=?");
				pstmt.setString(1,receiver_IFSC);
				pstmt.setInt(2,receiver_accno);
				ResultSet res2 = pstmt.executeQuery();
				//2
				if(res2.next()==true) {
					//3.sender balance checking
					pstmt=con.prepareStatement("select balance from BankApp where accno=?");
					pstmt.setInt(1,sender_accno);
					res3=pstmt.executeQuery();
					res3.next();
					int bal=res3.getInt(1);
					//3
					if(bal>amount) {
						//4.debit amount from sender
						pstmt=con.prepareStatement("update BankApp set balance=balance-? where accno=?");
						pstmt.setFloat(1,amount);
						pstmt.setInt(2,sender_accno);
						int x1=pstmt.executeUpdate();
						//4
						if(x1>0) {
							//5.credit amount to receiver
							pstmt=con.prepareStatement("update BankApp set balance=balance+? where accno=?");
							pstmt.setFloat(1,amount);
							pstmt.setInt(2,receiver_accno);
							int x2=pstmt.executeUpdate();
							//5
							if(x2>0) {
								//6.store transaction details
								pstmt=con.prepareStatement("insert into TransferStatus values(?,?,?,?,?,?,?,?)");
								int id=ThreadLocalRandom.current().nextInt(1,100000);
								pstmt.setInt(1,id);
								pstmt.setInt(2,cust_id);
								pstmt.setString(3,bank_name);
								pstmt.setString(4,IFSC);
								pstmt.setInt(5,sender_accno);
								pstmt.setString(6,receiver_IFSC);
								pstmt.setInt(7,receiver_accno);
								pstmt.setFloat(8,amount);

								int x3=pstmt.executeUpdate();
								//6
								if(x3>0) {
									return true;
								}
								else {
									return false;
								}
							}
							//6
							else {
								return false;
							}
						}
						//5
						else {
							return false;
						}
					}
					//4
					else {
						return false;
					}
				}
				//3
				else {
					return false;
				}
			}
			//2
			else {

				return false;
			}
			//1

		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}


