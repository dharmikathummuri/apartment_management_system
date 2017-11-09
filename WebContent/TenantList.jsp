<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>Tenants tracker app</title>

<style>

body
{ 
background-image: url("images/wild-sea.png");
}
.mainTable {
    width: 100%;
}

th {
    height: 50px;
}

legend {
  font-weight: bold;
  color: black;
  background-color: white;
  border: 1px solid #cccccc;
  padding: 4px 2px;
}


tr:hover {background-color: #f5f5f5}
</style>

</head>




<body>
 
		<div id="wrapper">
			<div id="header">
			
			
				<h1> Shelbyville Apartments</h1>
				 
				<div id="container">
					<div id="content">
					<table>
					<tr>
					<form method="post" action="AddTenant.jsp"><td><input type="submit" value="Add new Tenant" onclick="window.location.href='AddTenant.jsp'; return false;" class="add-student-button"/></td></form>
					<form method="get" action="userServlet"><input type="hidden" name="condition" value="retreival"/><td><input type="submit" name="seereqbutton" value="See maintainance requests" class="add-student-button"/></td></form>
					<form method="post" action="AddTenant.jsp"><td><input type="submit" value="logout" onclick="window.location.href='login.jsp'; return false;" class="add-student-button"/></td></form>
					</tr>
					
					</table>	
					<fieldset>
					<legend>Admin Login</legend>	
						<table align="left" border="1" cellspacing="0" cellpadding="1" class="mainTable id="customers">
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Apartment Number</th>
								<th>Action</th>
							</tr>
							<c:forEach var="temptenant" items="${tenants}"> 
							
							<c:url var="templink" value="TenantServlet">
								
								<c:param name="condition" value="load"/>
								<c:param name="Aptno" value="${temptenant.aptNo}"/>
								
							
							</c:url>
							
							
												
							<c:url var="deleteLink" value="TenantServlet">
								
								<c:param name="condition" value="delete"/>
								<c:param name="Aptno" value="${temptenant.aptNo}"/>
								
							
							</c:url>
								
							<tr>
								<td>${temptenant.firstName}</td>
								<td>${temptenant.lastName}</td>
								<td>${temptenant.emailid}</td>
								<td>${temptenant.aptNo}</td>
								<td>
									<a href="${templink}">Update</a>&emsp;
									| 
									<a href="${deleteLink}" onclick ="if(!(confirm('Are you sure you want to delete tenant?'))) return false">Delete</a>
								</td>
								
							</tr> 
							
							</c:forEach>
							</fieldset>
						</table>
						
						
					</div>
				</div>
			</div>
		</div>
</body>
</html>
