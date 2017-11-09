<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>see requests here</title>
<style>
	body {
		    background-image: url("images/wild-sea.png");
		    
		}
		.mainTable {
    width: 60%;
}

th {
    height: 50px;
}
tr:hover {background-color: #f5f5f5}

</style>

</head>

<body>
<h1> Shelbyville Apartments</h1>
	<table align="left" border="1" cellspacing="0" cellpadding="1" class="mainTable">
		<tr>
			<th>Apartment number</th>
			<th>Request</th>
			
		</tr>
		<c:forEach var="tempreq" items="${getrequests}"> 
			<tr>
				<td>${tempreq.aptno}</td>
				<td>${tempreq.req}</td>
			
			
			</tr>
		</c:forEach>


	</table>


</body>


</html>