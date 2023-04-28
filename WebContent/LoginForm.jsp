<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="logic.WebCon"
    	 import="logic.exceptions.DataInsertionException"
    	 import="java.sql.SQLException"%>

<jsp:useBean id="loginBean" scope="request"
             class="logic.view.bean.LoginBean"/>
<jsp:setProperty name="loginBean" property="*"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>weSpend-Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="NavBar.jsp"/>
<div class="container">
	<div class="col-6">	
	<form action="LoginFormServlet" method="POST">
	  	<div class="form-group">
    		<label for="user" style="color:green">
    		<%
    		if (request.getParameter("stat") != null) {
        		if (request.getParameter("stat").equals("ok")) {
        			out.print("<br>Account properly created, now log in!");
        		}
    		}
    		%>
    		</label>
  		</div>
  		<div class="form-group">
    		<label for="user">User name</label>
    		<input type="text" class="form-control" id="user" name="user">
  		</div>
  		
  		<div class="form-group">
    		<label for="pass">Password</label>
    		<input type="password" class="form-control" id="pass" name="pass">
  		</div>

		<input type="submit" type="button" class="btn btn-primary"
                       value="Login" name="submit" >
		<input type="submit" type="button" class="btn btn-primary"
                       value="Create account" name="submit">
	</form>	
	<hr>
	<label><%if(request.getParameter("response") != null) {
		out.println(request.getParameter("response"));
	}%>
	</label><hr class="mb-4">
	</div>
</div>
</body>
</html>