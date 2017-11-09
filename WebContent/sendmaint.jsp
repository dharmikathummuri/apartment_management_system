<html>
<head>
<title>User Home Page</title>
<style>
body {
	background-image: url("images/wild-sea.png");
}
textarea {
  width: 300px;
  height: 150px;
}
</style>

</head>

<body>
<h1> Welcome to Shelbyville Apartments Website</h1>

	<form action="userServlet" method="get">

		<input type="hidden" name="condition" value="requests" /> 
		Enter your	Apartment Number:           <input type="number" name="aptn" id="aptid" /><br />

		Send Request to maintainance Team:
		<textarea name="txtar" rows="10" cols="50"> </textarea>
		<br />
		<table>
		<tr>
		<td><input type="submit" value="send your requests" name="condition" /></td>
		<td><form method="post" action="AddTenant.jsp"><td><input type="submit" value="logout" onclick="window.location.href='login.jsp'; return false;" class="add-student-button"/></td></form></td>
		</tr>
		</table>
	</form>





</body>




</html>