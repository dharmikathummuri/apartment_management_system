package dataBaseFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojoFiles.Tenant;



public class TenantDbConnection {
	String url;
	Connection con;

	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/aps_tracker_Database?useSSL=false";
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

	public List<Tenant> getTenants() throws SQLException {
		List<Tenant> tenantsList = new ArrayList<>();

		Connection mycon = null;
		Statement st = null;
		ResultSet rs = null;
		//mycon = connect();
		try {
			mycon = this.connect();
			st = mycon.createStatement();
			String sql = "select * from tenant";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int apart_id = rs.getInt("apid");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				Tenant tempTenant = new Tenant(firstName, lastName, apart_id, email);
				tenantsList.add(tempTenant);
				//System.out.println(tenantsList);

			}

		}

		catch (Exception ex) {

			System.out.println(ex);
		}

		finally {
			close(mycon, st, rs);

		}
		return tenantsList;

	}

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


	public boolean addTenant(Tenant temptenant) throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection mycon = this.connect();
		/*boolean flag = true;
		System.out.println("inside add student");
		
		String beforequery = "select count(apid) as rec_count from tenant where email=?";
		st = mycon.prepareStatement(beforequery);
		 st.setString(1,temptenant.getEmailid());
		System.out.println("result set");
		rs=st.executeQuery();
		while(rs.next())
		{
			System.out.println("while loop");
			int count = rs.getInt("rec_count");
			if(count>=1)
			{
				flag = false;
				
			}
			else
			{*/
				try
				{
				
				String Query = "insert into tenant" + "(apid,first_name,last_name,email)" + "values(?,?,?,?)";		
				st = mycon.prepareStatement(Query);
				st.setInt(1,temptenant.getAptNo());
				st.setString(2, temptenant.getFirstName());
				st.setString(3, temptenant.getLastName());
				st.setNString(4, temptenant.getEmailid());
				st.execute();
				//flag=true;
				}
				
				finally
				{
					close(mycon,st,rs);
					
				}
				
		/*	}//end else
			
		}//end while
		*/
		
		
		return true;
		
		

}//end add tenant

	public Tenant getTenant(String aptno) {
		// TODO Auto-generated method stub
		
		Tenant t= null;
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		int Aptno;
		try
		{
			Aptno= Integer.parseInt(aptno);
			con = connect();
			String query = "select * from tenant where apid=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Aptno);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				String fname= rs.getString("first_name");
				String lname = rs.getString("last_name");
				String mail = rs.getString("email");
				t = new Tenant(fname,lname,Aptno,mail);
				
				
			}
			else
			{
				System.out.println("count not find tenant  with apartnment no " +Aptno);
				
			}
			
			
			
			
		}
		catch(Exception exc)
		{
			System.out.println(exc);
			
		}
		finally
		{
			close(con,ps,rs);
			
		}
		return t;
		
		
	}

	public void updateTenant(Tenant ten) {
		
		Connection con=null;
		PreparedStatement pst =null;
		
		try
		{
			
			con = this.connect();
			String UpdateQuery = "update tenant " + "set first_name=?, last_name=?, email=? " + "where apid=?";
			pst=con.prepareStatement(UpdateQuery);
			pst.setString(1,ten.getFirstName());
			pst.setString(2,ten.getLastName());
			pst.setString(3,ten.getEmailid());
			pst.setInt(4,ten.getAptNo());
			pst.execute();
			
			
			
			
		}
		catch(Exception exx)
		{
			
			System.out.println(exx);
		}
		finally{
			close(con,pst,null);
			
			
		}
		
		
		
		
		
	}

	public void deleteTenant(Tenant ten) {
		
		Connection con= null;
		PreparedStatement pst = null;
		//System.out.println("eneterd delete tenant");
		try
		{
			
			con= this.connect();
			String deleteQuery ="DELETE FROM tenant WHERE apid=?";
			pst = con.prepareStatement(deleteQuery);
			pst.setInt(1, ten.getAptNo());
			pst.execute();
			
			
			
			
			
			
		}
		catch(Exception excc)
		{
			
			System.out.println(excc);
		}
		
		finally
		{
			close(con,pst,null);
			
		}
		
		
	}

	public boolean validateAdmin(String uname, String pwd) {
		
		Connection con=null;
		Statement pst = null;
		ResultSet rs= null;
		boolean validate= false;
		try
		{
			con = this.connect();
			String ValidateQuery = "select * from user";
			pst = con.createStatement();
			rs = pst.executeQuery(ValidateQuery);
			while(rs.next()) 
			{
				String username = rs.getString(1);
				String password = rs.getString(2);
			
				if(uname.equals(username) && pwd.equals(password))
				{
					
					validate = true;
					break;
				}
				
			}
			
		}
		catch(Exception validateexception)
		{
			
			System.out.println(validateexception);
		}
		finally
		{
			
			close(con,pst,rs);
		}
		return validate;
		
		
	}

	public boolean validateUser(String uname, String pwd) {
		
		
		
		Connection con=null;
		Statement pst = null;
		ResultSet rs= null;
		boolean validate= false;
		//System.out.println("enetered validate user");
		try
		{
			con = this.connect();
			String ValidateQuery = "select * from userTenant";
			System.out.println(ValidateQuery);
			pst = con.createStatement();
			rs = pst.executeQuery(ValidateQuery);
			while(rs.next()) 
			{
				String username = rs.getString(1);
				String password = rs.getString(2);
			
				if(uname.equals(username) && pwd.equals(password))
				{
					
					validate = true;
					break;
				}
				
			}
			
		}
		catch(Exception validateexceptions)
		{
			
			System.out.println(validateexceptions);
		}
		finally
		{
			
			close(con,pst,rs);
		}
		return validate;
	}

	public boolean createTenantUser(String firstname, String lastName,int aptno) {
		
		Connection con=null;
		PreparedStatement pst = null;
		ResultSet rs= null;
		try
		{
			con = this.connect();
			String Query = "insert into userTenant" + "(username,password,apid)" + "values(?,?,?)";
			
			//System.out.println("tenant creating");
			pst = (PreparedStatement) con.prepareStatement(Query);
			pst.setString(1, firstname);
			pst.setString(2, lastName);
			pst.setInt(3, aptno);
			pst.execute();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			close(con,pst,rs);
			
		}
		
		return true;
	
		
	}

	public boolean deleteTenantUser(String fname, String lname, int aptno) {
		
		Connection con=null;
		PreparedStatement pst = null;
		ResultSet rs= null;
		try
		{
			con = this.connect();
			String Query = "DELETE FROM usertenant WHERE apid=?";
			
			//System.out.println("tenant creating");
			pst = (PreparedStatement) con.prepareStatement(Query);
			
			pst.setInt(1, aptno);
			pst.execute();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			close(con,pst,rs);
			
		}
		
		return true;
	
		
		
	}

	/*public boolean checkApartnumber(int aptnoModified) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst = null;
		ResultSet rs= null;
		System.out.println("hi");
		boolean flag = false;
		int count;
		try
		{
			System.out.println("hiiiiiiiiiii");
			con = this.connect();
			String Query = "select count(apid) as aptnum from tenant where apid=?";
			
			//System.out.println("tenant creating");
			pst =  con.prepareStatement(Query);
			
			pst.setInt(1, aptnoModified);
			
			rs=pst.executeQuery();
			rs.next();
			System.out.println("inside while");
				
				count = rs.getInt("aptnum");
				if(count==1)
				{
					System.out.println("flag");
					flag = false;
					
				}
				else
				{
					flag=true;
				}
					
				
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			close(con,pst,rs);
			
		}
		
		return flag;
	
		
	}
*/
	

}