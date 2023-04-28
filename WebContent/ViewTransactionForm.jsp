<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		 import="logic.WebCon"
		 import="logic.controller.Table"
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
      <th scope="col">Notes</th>
      
    </tr>
  </thead>
  <tbody>
   <%  if(session.getAttribute("listtest")!=null){
	   try{
   		List<Table> list= (List<Table>) session.getAttribute("listtest");
   		for(int i=0;i<list.size();i++){
   			out.print("<tr>"+
   	     			 "<th scope=\"row\">"+list.get(i).getRDate()+"</th>"+
   	     			" <td>"+list.get(i).getRType()+"</td>"+
   	      		 	" <td>"+list.get(i).getRPrice()+"</td>"+
   	     	 		" <td>"+list.get(i).getRComment()+"</td>"+
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