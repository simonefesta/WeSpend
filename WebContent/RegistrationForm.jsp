<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="logic.WebCon"
    	 import="logic.exceptions.DataInsertionException"
    	 import="java.sql.SQLException"%>

<jsp:useBean id="registrationBean" scope="request"
             class="logic.view.bean.RegistrationBean"/>
<jsp:setProperty name="registrationBean" property="*"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>weSpend</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="NavBar.jsp"/>	
<div class="container">
	<br>
	<div class="col-6">
	
	<form action="RegistrationFormServlet" method="POST">
  		<div class="form-group">
    		<label for="user">User name</label>
    		<input type="text" class="form-control" id="user" name="user" placeholder="type your username...">
  		</div>
  		<div class="form-group">
    		<label for="pass">Password</label>
    		<input type="password" class="form-control" id="passOne" name="passOne" placeholder="type your password...">
  		</div>
  		<div class="form-group">
    		<label for="pass">Repeat password</label>
    		<input type="password" class="form-control" id="passTwo" name="passTwo" placeholder="repeat your password...">
  		</div>
  		<div class="form-group">
 			<label for="validationDefaultUsername">Start deposit</label>
      			<div class="input-group">
        			<div class="input-group-prepend">
          				<span class="input-group-text" id="inputGroupPrepend2">€</span>
        			</div>
       		 		<input type="text" class="form-control" id="deposit" name="deposit" aria-describedby="inputGroupPrepend2"
       		 			   placeholder="0.00">
      			</div>
      	</div> 	
      
		<input type="submit" type="button" class="btn btn-primary"
                       value="Login" name="submit" >
		<input type="submit" type="button" class="btn btn-primary"
                       value="Create account" name="submit" >
	</form>	
	<hr class="mb-4">
	<label><%if(request.getParameter("response") != null) {
		out.println(request.getParameter("response"));
	}%></label><hr class="mb-4">
	</div>
</div>
</body>
</html>