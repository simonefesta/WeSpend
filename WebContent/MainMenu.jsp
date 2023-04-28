<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		 import="logic.WebCon"%>
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

<%	
	if (!WebCon.checkLogin(session)) {
		response.sendRedirect(WebCon.LOGINPAGE);
		return;
	}
%>
<jsp:include page="NavBar.jsp"/>
<div class="container">
 	<div class="row">
    	<div class="col-sm">
      		<%      		
      		if (request.getParameter("stat") != null) {
      		
				if (request.getParameter("stat").equals("success")) {
					out.print("Transaction properly added!");
				}
				else if (request.getParameter("stat").equals("fail")) {
					out.print("Failure!");
				}
				else if (request.getParameter("stat").equals("delete")) {
					out.print("Transaction properly deleted!");
				}
      		}
			%>
    	</div>

  	</div>
</div>

</body>
</html>