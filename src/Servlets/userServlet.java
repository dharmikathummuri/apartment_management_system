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

import dataBaseFiles.UserDbConnection;
import pojoFiles.userRequests;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDbConnection udbc = new UserDbConnection();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		try {
			//System.out.println("do get");
				
				String GetCondition = request.getParameter("condition");
				//System.out.println("Request in "+GetCondition);
				
				
				if(request.getParameter("condition").equals("retreival"))
				{
					seeReq(request,response);
					
				}
				else if(GetCondition.equals("requests"))
				{
					
					SendRequest(request,response);
				}
				
				
				else
				{
					
					response.sendRedirect("UserHome.jsp");
				}
			
				
			} 
		
			catch (Exception e) 
			{
			
				throw new ServletException(e);
			
				}
		
		
	}//end doget


	private void SendRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String aptnoS = request.getParameter("aptn");
		String maintereq = request.getParameter("txtar");
		
		if(!(aptnoS.equals("") || (maintereq.equals("")))){
		
			 int aptno = Integer.parseInt(aptnoS);
			 userRequests ur = new userRequests(aptno,maintereq);
		
			boolean valid= udbc.CheckAptno(aptno);
			if(valid)
			{ 
				boolean sent = udbc.sendReqToDb(ur);
				if(sent)
				{
					//System.out.println("I am in sent true");
					//response.sendRedirect("thankyou.jsp");
					out.println("<center><h2 style='color:green'>Thank you for your request. Ou maintainance team will contact you soon</h2></center>");

					getServletContext().getRequestDispatcher("/sendmaint.jsp").include(request, response);
					
				}
			}
			else
			{
				//response.sendRedirect("apterror.jsp");
				out.println("<center><h2 style='color:red'>Please enter a valid apartment number </h2></center>");

				getServletContext().getRequestDispatcher("/sendmaint.jsp").include(request, response);
				
			}
			
		}
		else
		{
			//response.sendRedirect("aptnull.jsp");
			out.println("<center><h2 style='color:red'> Please Enter data to be sent </h2></center>");

			getServletContext().getRequestDispatcher("/sendmaint.jsp").include(request, response);
		}
		
		
	}

	private void seeReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		//System.out.println("I am in seeReq");
		UserDbConnection udb = null; 
		try{

			 udb = new UserDbConnection();

			//System.out.println(udb);
			 List<userRequests> req  = udb.getRequests();
				//System.out.println(ten);
				//add tenant to request

				request.setAttribute("getrequests", req);
				//System.out.println(req);


				//send data to jsp
				RequestDispatcher dispatcher = request.getRequestDispatcher("seeRequests.jsp");
				dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		
		

	}
	
	

}
