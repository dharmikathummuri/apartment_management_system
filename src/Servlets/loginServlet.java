package Servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBaseFiles.TenantDbConnection;




/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TenantDbConnection tdb = new TenantDbConnection();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		PrintWriter pw =response.getWriter();
		String uname = request.getParameter("loginName");
		String pwd = request.getParameter("loginpassword");
		String Select = request.getParameter("useradmin");
		
		/*Start : Implementing Session
		
		HttpSession session = request.getSession();
		session.setAttribute("user", uname);
		//setting session to expiry in 30 mins
		//session.setMaxInactiveInterval(30);
		
		
		End : Implementing Session*/
		
		if((!(uname.equals(null) || uname.equals("")) && (!(pwd.equals(null) || pwd.equals(""))) && !(Select==null) ))
		{
			if(Select.equals("admin"))
			{
				boolean validate = tdb.validateAdmin(uname,pwd);
				if(validate==true)
				{
					response.sendRedirect("TenantServlet");
				}
				else
				{
					pw.println("<center><h2 style='color:red'>Error In Login.Please try again with valid login details</h2></center>");
					
					 
					getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
				}

			}

			if(Select.equals("user"))
			{
				boolean validate = tdb.validateUser(uname,pwd);
				if(validate==true)
				{
					/*Start : Implementing Session*/
					/*Cookie unameCookie = new Cookie("unameCookie",
							uname);
					unameCookie.setMaxAge(0);
					response.addCookie(unameCookie);
*/
					/*End : Implementing Session*/


					//System.out.println("entered user radio button");
					response.sendRedirect("sendmaint.jsp");
				}
				else
				{
					pw.println("<center><h2 style='color:red'>Error In Login.Please try again with valid login details</h2></center>");
					
					 
					getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
				}


			}


		}


		else
		{
			pw.println("<center><h2 style='color:red'>Error In Login.Please try again with valid login details</h2></center>");
			
			 
			getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
		}





	}






}
