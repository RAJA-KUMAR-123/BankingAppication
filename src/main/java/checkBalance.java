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




@WebServlet("/CheckBalance")
public class checkBalance extends HttpServlet {
	
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String pwd = "root";
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");

		// Database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
			pstmt = con.prepareStatement("select balance from bankapp where accno=?");

			 

            pstmt.setInt(1,accno);

 

 

            rs=pstmt.executeQuery();

            if(rs.next()==true) {

                session.setAttribute("balance", rs.getInt("balance"));

                //session.setAttribute("cust_name",rs.getString("cust_name"));

                res.sendRedirect("/Bankapplication/Balance.jsp");

            }
			else {
	 		res.sendRedirect("/Bankapplication/BalanceFail.jsp");
			}
		}
		catch (Exception e) {

			e.printStackTrace();

		}


}
}
