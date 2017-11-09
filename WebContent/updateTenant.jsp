<html>

<head>
		<title>Update tenant</title>
	
<style>

body
{ 
background-image: url("images/wild-sea.png");
}


</style>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2> Shelbyville Apartments </h2>
		</div>
	</div>
	
		<div id="container">
		<h3>update Tenant</h3>
		<form action="TenantServlet" method="GET">
			<input type="hidden" name="condition" value="update"/>
			<input type="hidden" name="aptNo" value="${editTenants.aptNo}"/>
				<table>
					<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td> <input type="text" name ="firstname" value="${editTenants.firstName}"/> </td> 
					</tr>	
					
					<tr>	
							<td> <label>Last Name:</label></td>
						<td><input type="text" name ="lastname" value="${editTenants.lastName}" /></td>
					</tr>
						
					<tr>		
						<td><label>Email id:</label></td>
						<td><input type="text" name ="email" value="${editTenants.emailid}"/>
					</tr>
						
						 <tr>
						<td><label>Apartment number:</label></td>
						<td><input type="text" name ="aptno" value="${editTenants.aptNo}" />
						</tr>  
						
						<tr>
						<td><label></label></td>
						<td><input type="submit" name ="save" value="Update tenant details"/>
						</tr>
					
						
					
					</tbody>
				
				</table>
				
		</form>
		
		<div style="clear: both;">
	    </div>
	    	<p>
	    		<a href="TenantServlet">Back to Add tenants page</a>
	    	</p>
		
		</div>
	
	
</body>

</html>