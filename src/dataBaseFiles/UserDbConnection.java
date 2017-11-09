package dataBaseFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import pojoFiles.userRequests;

public class UserDbConnection {
	
	String url;
	Connection con;

	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/aps_tracker_Database";
			// System.out.println(url);
			con = DriverManager.getConnection(url, "root", "Sumukh123*");
			//System.out.println("Connection successfull");

			// System.out.println(con);

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return con;
	}// end db connection method
	
	private void close(Connection mycon, Statement st, ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			if (rs != null) {

				rs.close();
			}
			if (st != null) {

				st.close();
			}
			if (mycon != null) {

				mycon.close();
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}


	

	public boolean sendReqToDb(userRequests ur) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection mycon = connect();
		try
		{
			//System.out.println("we are here");
			mycon = this.connect();
			String Query = "insert into tenantuser" + " (aptid,maintainreq) " + " values(?,?)";
			st = mycon.prepareStatement(Query);
			int aptno= ur.getAptno();
			st.setInt(1,aptno);
			String maintereq = ur.getReq();
			st.setString(2,maintereq );
			
			 st.executeUpdate();//come back
			
			//System.out.println("Executed the query "+i);
			
		}
		catch(Exception ex)
		{
			
			System.out.println(ex);
		}
		finally
		{
			close(mycon,st,rs);
			
		}
		return true;
	}
	
	
	public List<userRequests> getRequests() throws SQLException {
		List<userRequests> reqlist = new ArrayList<>();

		Connection mycon = null;
		Statement st = null;
		ResultSet rs = null;
		//mycon = connect();
		//System.out.println("uers req db");
		try {
			mycon = this.connect();
			st = mycon.createStatement();
			String sql = "select * from tenantuser";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int apart_id = rs.getInt("aptid");
				String mr = rs.getString("maintainreq");
				
				userRequests ur = new userRequests(apart_id,mr);
				reqlist.add(ur);
				//System.out.println(tenantsList);

			}

		}

		catch (Exception ex) {

			System.out.println(ex);
		}

		finally {
			close(mycon, st, rs);

		}
		return reqlist;

	}

	public boolean CheckAptno(int apno) {
		
		Connection mycon = null;
		Statement st = null;
		ResultSet rs = null;
		//mycon = connect();
		//System.out.println("uers req db");
		try {
			mycon = this.connect();
			st = mycon.createStatement();
			String sql = "select * from tenant";
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int apart_id = rs.getInt("apid");
				if(apart_id==apno)
				{
					return true;
					
				}
			
				
			
			}

		}

		catch (Exception ex) {

			System.out.println(ex);
		}

		finally {
			close(mycon, st, rs);

		}
		
		return false;
	}

}
