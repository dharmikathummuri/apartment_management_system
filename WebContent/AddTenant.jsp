<html>

<head>
		<title>Add tenant</title>
	
<style>
		body {
		    background-image: url("images/wild-sea.png");
		  
		}
		
</style>
		
		
</head>

<body background="unnamed.jpg">
	<div id="wrapper">
		<div id="header">
			<h1> Shelbyville Apartments </h1>
		</div>
	</div>
	
		<div id="container">
		<h3>Add new Tenant</h3>
	
		<form font-size="75px;" action ="TenantServlet" method="get">
			<input type="hidden" name="condition" value="Add"/>
				<table>
					<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td> <input type="text" name ="firstname" id="fid"/> </td> 
					</tr>	
					
					<tr>	
							<td> <label>Last Name:</label></td>
						<td><input type="text" name ="lastname" id="lid"/></td>
					</tr>
						
					<tr>		
						<td><label>Email id:</label></td>
						<td><input type="text" name ="email" id="eid"/>
					</tr>
						
						<tr>
						<td><label>Apartment number:</label></td>
						<td><input type="text" name ="aptno" id="aid"/>
						</tr>
						
						<tr>
						<td><label></label></td>
						<td><input type="submit" name ="save" value="add tenant"/>
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