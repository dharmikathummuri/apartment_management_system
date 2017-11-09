<html>
<head>
<title>

	Login page

</title>
<style>
body
{ 
background-image: url("images/wild-sea.png");
}


</style>
</head>

<body>



<div id="adiv">

<form action="loginServlet" method="get">
		${message}
		<center>
		<h1> ShelbyVille Apartments Login Page</h1>
		<label>User name:</label>
		 <input type="text" name="loginName" id="loginid"/><br/>
		
		 <label>Password:</label>
		 <input type="password" name="loginpassword" id="pwdid"/><br/>
	  
	  		<input type="radio" name="useradmin" value="user"> Login as user<br/>
	  		<input type="radio" name="useradmin" value="admin"> Login as admin<br/>
	  		<input type="submit" name="loginButton" value="login here" />
	  		</center>
</form>
		
</div>


</body>


</html>