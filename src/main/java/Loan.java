import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loan")
public class Loan extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	

  @Override
protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int l_id=Integer.parseInt((String)req.getParameter("l_id"));
		
		
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String pwd = "root";
		HttpSession session=req.getSession();

		// Database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
			String  sql="select * from loan where l_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, l_id);
			resultSet = pstmt.executeQuery();
			

			if(resultSet.next()==true) {
                session.setAttribute("l_id",resultSet.getInt("l_id"));
                session.setAttribute("l_type",resultSet.getString("l_type"));
                session.setAttribute("benure",resultSet.getInt("benure"));
                session.setAttribute("intrest",resultSet.getInt("intrest"));
                session.setAttribute("descrip",resultSet.getString("descrip"));
		
				res.sendRedirect("/Bankapplication/LoanDetails.jsp");
			}

			else {

				res.sendRedirect("/Bankapplication/LoanDetailsFail.jsp");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
