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



@WebServlet("/Login")
public class Login extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

  @Override
protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 
		int cust_id=Integer.parseInt(req.getParameter("cust_id"));
		int pin=Integer.parseInt(req.getParameter("pin"));
		
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String pwd = "root";
		HttpSession session=req.getSession(true);

		// Database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
			pstmt=con.prepareStatement("select * from bankapp where cust_id= ? and pin= ?");
			
			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, pin);
			

			resultSet = pstmt.executeQuery();

			if(resultSet.next()==true) {
                session.setAttribute("accno",resultSet.getInt("accno"));
                session.setAttribute("cust_name",resultSet.getString("cust_name"));
		//		res.sendRedirect("/Bankapplication/Home.html");  HomePage.jsp
				res.sendRedirect("/Bankapplication/HomePage.jsp");
			}

			else {

				res.sendRedirect("/Bankapplication/LoginFail.html");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}
}

