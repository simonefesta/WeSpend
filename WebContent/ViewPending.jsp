<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		 import="logic.WebCon"
		 import="logic.controller.PendingTable"
		 import="java.util.List"%>    
<!DOCTYPE html>
<html>
<head>
<head>
    <title>weSpend</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
</head>
</head>
<body>
<%
	if (!WebCon.checkLogin(session)) {
		response.sendRedirect(WebCon.LOGINPAGE);
		return;
	}
%>
<jsp:include page="NavBar.jsp"/>
<table class="table table-hover">
  <thead>
   <thead class="thead-dark">
    <tr>
     <th scope="col">Date</th>
      <th scope="col">Type</th>
      <th scope="col">Price</th>
      <th scope="col">Created by</th>
      <th scope="col">Notes</th>
      
    </tr>
  </thead>
  <tbody>
   <%  if(session.getAttribute("pending")!=null){
	   try{
   		List<PendingTable> pend= (List<PendingTable>) session.getAttribute("pending");
   		for(int i=0;i<pend.size();i++){
   			out.print("<tr>"+
   	     			 "<th scope=\"row\">"+pend.get(i).getRDate()+"</th>"+
   	     			" <td>"+pend.get(i).getRType()+"</td>"+
   	      		 	" <td>"+pend.get(i).getRPrice()+"</td>"+
   	      		    " <td>"+pend.get(i).getRUser()+"</td>"+
   	     	 		" <td>"+pend.get(i).getRComment()+"</td>"+
   	                "</tr>");
   		}
	   }
	   catch(Exception e){}
   }
   
   %>

  </tbody>
</table>

</body>
</html>