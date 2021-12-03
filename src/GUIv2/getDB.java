package GUIv2;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class getDB {
	public ResultSet executeQuery(String sql) throws Exception {
		 java.sql.Connection con;
		 con = DriverManager.getConnection("jdbc:mysql://10.0.0.205/db_pbl4","root","");
		 if (con != null) {
			  System.out.println("Ket noi thanh cong");
		}
		 Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 return rs;
	}
	public void updateQuery(String sql) throws Exception {
		 java.sql.Connection con;
		 con = DriverManager.getConnection("jdbc:mysql://10.0.0.205/db_pbl4","root","");
		 if (con != null) {
			  System.out.println("Ket noi thanh cong");
		}
		 Statement st = con.createStatement();
		 st.executeUpdate(sql);
	}
}
