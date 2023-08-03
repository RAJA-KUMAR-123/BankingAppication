import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/trans")
public class transfer extends HttpServlet {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1, res2;
	private ResultSet res3;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int cust_id=Integer.parseInt(req.getParameter("cust_id"));
		String bank_name=req.getParameter("bank_name");
		String ifsc_code=req.getParameter("ifsc_code");
        int sender_accno=Integer.parseInt(req.getParameter("sender_accno"));
		String reciver_ifsc=req.getParameter("reciver_ifsc");
		int reciver_accno=Integer.parseInt(req.getParameter("reciver_accno"));
		int amount=Integer.parseInt(req.getParameter("amount"));
		int pin=Integer.parseInt(req.getParameter("pin"));
		
		
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String pwd = "root";

		HttpSession session = req.getSession();

		// Database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);

			pstmt = con.prepareStatement("select * from bankapp where cust_id=? and ifsc_code=? and accno=? and pin = ?");
			pstmt.setInt(1, cust_id);
			pstmt.setString(2, ifsc_code);
			pstmt.setInt(3, sender_accno);
			pstmt.setInt(4, pin);

			res1 = pstmt.executeQuery();

			if (res1.next() == true) {
				pstmt = con.prepareStatement("select * from bankapp where ifsc_code = ? and accno=?");
				pstmt.setString(1,ifsc_code);
				pstmt.setInt(2,sender_accno);
				

				res2 = pstmt.executeQuery();

				if (res2.next() == true) {
					pstmt = con.prepareStatement("select balance from bankapp where accno=?");
					pstmt.setInt(1,sender_accno);

					res3 = pstmt.executeQuery();
					res3.next();
					int bal = res3.getInt(1);

					if (bal >amount ) {
						pstmt = con.prepareStatement("update bankapp set balance=balance -? where accno=?");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, reciver_accno);
						
						
						int x1 = pstmt.executeUpdate();

						if (x1 > 0) {
							pstmt = con.prepareStatement("update bankapp set balance =balance-? where accno=?");
							pstmt.setInt(1, amount);
							pstmt.setInt(2, reciver_accno);
							int x2 = pstmt.executeUpdate();

							if (x2 > 0) {
								pstmt = con.prepareStatement("insert into TransferStatus values(?,?,?,?,?,?,?)");
								pstmt.setInt(1, cust_id);
								pstmt.setString(2, bank_name);
								pstmt.setString(3, ifsc_code);
								pstmt.setInt(4, sender_accno);
								pstmt.setString(5, reciver_ifsc);
								pstmt.setInt(6, reciver_accno);
								pstmt.setInt(7, amount);
								
								int x3=pstmt.executeUpdate();
								if(x3>0) {
									resp.sendRedirect("/Bankapplication/TransferSuccessfully.html");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
