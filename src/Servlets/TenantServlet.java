package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBaseFiles.TenantDbConnection;
import pojoFiles.Tenant;


@WebServlet("/TenantServlet")
public class TenantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TenantDbConnection tdb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			
			String GetCondition = request.getParameter("condition");

			if (GetCondition == null) {
				GetCondition = "LIST";

			}

			switch (GetCondition) {
			case "LIST":

				TenantsList(request, response);
				break;
			case "Add":
				addTenant(request, response);
				break;
			case "load":
				loadTenant(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "delete":

				delete(request, response);
				break;

			default:

				TenantsList(request, response);
			}

			// TenantsList(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int aptno = Integer.parseInt(request.getParameter("Aptno"));
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");

		String email = request.getParameter("email");

		boolean created = tdb.deleteTenantUser(fname, lname, aptno);
		System.out.println(created);
		Tenant ten = new Tenant(fname, lname, aptno, email);

		tdb = new TenantDbConnection();
		tdb.deleteTenant(ten);
		System.out.println("your are in delete mehtod");
		TenantsList(request, response);

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter pw = response.getWriter();
		String apid = request.getParameter("aptno");
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");

		if (fname == null || fname.equals("")) {

			pw.println("<center><h2 style='color:red'>Please enter valid firstname </h2></center>");

			getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

		} else if (lname == null || lname.equals("")) {

			pw.println("<center><h2 style='color:red'>Please enter valid lastname</h2></center>");

			getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

		} else if (!email.contains("@") || email.equals("")) {
			pw.println("<center><h2 style='color:red'>Please enter valid emailId</h2></center>");

			getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

		}

		else {
			int AptnoModified = 0;

			try {
				AptnoModified = Integer.parseInt(apid);
				// is an integer!
				Tenant ten = new Tenant(fname, lname, AptnoModified, email);
				tdb = new TenantDbConnection();
				tdb.updateTenant(ten);

				TenantsList(request, response);

			} catch (NumberFormatException e) {
				// not an integer!
				pw.println("<center><h2 style='color:red'>Please enter valid number for Apartment no.</h2></center>");

				getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

			}

		}

	}

	private void loadTenant(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String aptno = request.getParameter("Aptno");
		Tenant t = (Tenant) tdb.getTenant(aptno);
		request.setAttribute("editTenants", t);
		RequestDispatcher rd = request.getRequestDispatcher("/updateTenant.jsp");
		rd.forward(request, response);
	}

	private void addTenant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		PrintWriter pw = response.getWriter();
		String Firstname = request.getParameter("firstname");
		String LastName = request.getParameter("lastname");
		String Email = request.getParameter("email");
		String Aptnos = request.getParameter("aptno");
		

		if (Firstname == null || Firstname.equals("")) {

			pw.println("<center><h2 style='color:red'>Please enter valid firstname </h2></center>");

			getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

		} else if (LastName == null || LastName.equals("")) {

			pw.println("<center><h2 style='color:red'>Please enter valid lastname</h2></center>");

			getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

		} else if (!Email.contains("@") || Email.equals("")) {
			pw.println("<center><h2 style='color:red'>Please enter valid emailId</h2></center>");

			getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

		}

		else {
			int AptnoModified = 0;
			AptnoModified = Integer.parseInt(Aptnos);
			//boolean flag = tdb.checkApartnumber(AptnoModified);
			try {
				/*if(flag)
				{*/
					//System.out.println("checked");
					//is an integer!
					Tenant temptenant = new Tenant(Firstname, LastName, AptnoModified, Email);

					boolean done = tdb.addTenant(temptenant);
					if (done) {
						//System.out.println("hit");
						tdb.createTenantUser(Firstname, LastName, AptnoModified);

						TenantsList(request, response);
					
						}
				/*else 
				{
					System.out.println("unchecked");
					pw.println("<center><h2 style='color:red'>Apartment number already exists.cannot add another number with same aprtment number.</h2></center>");

					getServletContext().getRequestDispatcher("AddTenant.jsp").include(request, response);
					
				
				}*/
					
			/*	}//end main if
*/		

			} //end try
			catch (NumberFormatException e) {
				// not an integer!
				pw.println("<center><h2 style='color:red'>Please enter valid number for Apartment no.</h2></center>");

				getServletContext().getRequestDispatcher("/AddTenant.jsp").include(request, response);

			}//edn catch
				

		}//end else

	}//end add

	private void TenantsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			
			/* Implementing Session */
			/*HttpSession session = request.getSession();
			String sessionUser = (String) session.getAttribute("user");
			PrintWriter pw = response.getWriter();
			if (!(sessionUser.equals("") || sessionUser == null)) {*/

				tdb = new TenantDbConnection();

				System.out.println(tdb);

				List<Tenant> ten = tdb.getTenants();
				// System.out.println(ten);
				// add tenant to request

				request.setAttribute("tenants", ten);

				// send data to jsp
				RequestDispatcher dispatcher = request.getRequestDispatcher("/TenantList.jsp");
				// System.out.println("dispatcher");
				dispatcher.forward(request, response);
				// System.out.println("request");
			/*} else {

				pw.println("<center><h2 style='color:red'>you black sheep!</h2></center>");

				getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);

			}*/

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
