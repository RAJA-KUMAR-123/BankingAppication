package com.digit.javaTraining.MVCApp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApplyLoan {
	int lid;
	String l_type;
	int tenure;
	int interest;
	String description;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	public ApplyLoan()
	{
		String url="jdbc:mysql://localhost:3306/bank_mvc";
		String user="root";
		String pwd="root*";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getL_type() {
		return l_type;
	}
	public void setL_type(String l_type) {
		this.l_type = l_type;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public boolean aloan(String loan_name)
	{
		try {
			String sql="select* from loan where l_type=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,loan_name);
			resultSet=pstmt.executeQuery();
			if(resultSet.next()==true)
			{
				this.setLid(resultSet.getInt("lid"));
				this.setL_type(resultSet.getString("l_type"));
				this.setTenure(resultSet.getInt("tenure"));
				this.setInterest(resultSet.getInt("interest"));
				this.setDescription(resultSet.getString("description"));
				return true;

			}
			else {
				return false;

			}	
		}catch(Exception e) {
			return false;

		}

	}
}

