<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" 
         import="logic.entity.TransactionManagerWeb"
         import="logic.entity.decorator.TransactionComponent"
         import="logic.controller.AddDebtController"
         import="logic.entity.User"
         import="logic.WebCon"
         import="logic.enumerations.Month"
         import="javafx.collections.ObservableList"%>

<!-- HTML 5 -->
<!DOCTYPE html>
<html>
<head>
    <title>weSpend</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
</head>

<body>
<%
	if (!WebCon.checkTransaction(session)) {
		response.sendRedirect(WebCon.MAINPAGE);
		return;
	}
	if (!WebCon.checkLogin(session)) {
		response.sendRedirect(WebCon.LOGINPAGE);
		return;
	}
%>
<jsp:include page="NavBar.jsp"/>   
<div class="container">
<div class="row">

	<div class="col">

	<br>
	<div class="card" style="width: 18rem;">
		<h5 class="card-header">Create a debt</h5>
  		<div class="card-body">
    		<h5 class="card-title">Transaction data</h5>
    		<%
    		TransactionComponent tr = TransactionManagerWeb.getSessionInstance(session).getTransaction();

    		out.print("<p class=\"card-text\" value=\""+tr.getValue()+"\">Import: " + tr.getValue() + " €</p>");
    		out.print("<p class=\"card-text\" value=\""+tr.getComment()+"\">Comment: " + tr.getComment() + "</p>");
    		out.print("<p class=\"card-text\">Category: " + tr.getCategory() + "</p>");
    		out.print("<p class=\"card-text\">Date: " + tr.getTime().getDayOfMonth() + " " +
       				Month.fromId(tr.getTime().getMonthValue()).getName() + " " +
       				tr.getTime().getYear() + " " + tr.getTime().getHour() + ":" + 
       				tr.getTime().getMinute() + "</p>");
    		%>
    		<a href="DeleteTransactionServlet" class="card-link"><font color="red">Cancel</font></a>
		</div>
	</div>

	</div>
	<div class="col">

	  		<form action="AddDebtFormServlet" method="POST">	
 				<div class="form-group">
    				<label for="exampleFormControlSelect2">Select a friend</label>
    				<select class="form-control" id="exampleFormControlSelect2" id="friends" name="friends">
    				<%
    				AddDebtController addDebtController = new AddDebtController();
    				ObservableList<String> friendUsers = addDebtController.getFriendUsers((String)session.getAttribute(WebCon.USERNAME));
    		  		
    		   		for (int i=0; i<friendUsers.size(); i++) {
    		   			out.print("<option>" + friendUsers.get(i) + "</option>");
    		   		}
    				%>
    				</select>
  				</div>
  				<input name="submit" type="submit" id="submit"
                       value="Create" class="btn btn-success">
                <%
       	  		if (!tr.hasSharedExpense()) {
                    out.println("<input name=\"share\" type=\"submit\" id=\"share\" value=\"Divide expense\" class=\"btn btn-info\">");
       	  		}
                %>
                <%
       	  		if (!tr.hasDebt()) {
                    out.println("<input name=\"select\" type=\"submit\" id=\"select\"value=\"Select friend\" class=\"btn btn-info\">");
       	  		}
                %>
			</form>
	</div>
	
</div>
<br>
<div class="row">

	<div class="col">
	
		<div class="card" style="width: 18rem;">
  			<div class="card-body">
    			<h5 class="card-title">You are creating a debt to:</h5>
    			<p class="card-text">
    			<%
					if (tr.getDebtUser() != null)
						out.println("<br>" + tr.getDebtUser().getName());
    			%>
    			</p>
			</div>
		</div>
	</div>
	
	<div class="col">
		<div class="card" style="width: 18rem;">
  			<div class="card-body">
    			<h5 class="card-title">You are sharing your transaction with:</h5>
    			<p class="card-text">
    			<%
    			for (int i=0; i<tr.getSharedUsers().size(); i++) {
    				out.println("<br>" + tr.getSharedUsers().get(i).getName());
		   		}
    			%>
    			</p>
			</div>
		</div>
	</div>
		
</div>
 <hr class="mb-4">
<div class="row">
	<div class="col">
		<label><% 
		if (session.getAttribute("err") != null) {
			out.print(session.getAttribute("err"));
			session.setAttribute("err", null);
		} 
		%></label><hr class="mb-4">
	</div>
</div>
  
</div>
</body>
</html>