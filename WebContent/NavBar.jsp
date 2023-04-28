<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		 import="logic.WebCon"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="<%=WebCon.MAINPAGE%>">
		<img src="image/wespendlogo.png" width="30" height="30" class="d-inline-block align-top" alt="\">
			weSpend
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
	<ul class="navbar-nav">
  		<li class="nav-item dropdown">
    		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     			New
    		</a>
    		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
     			<a class="dropdown-item" href="AddTransactionForm.jsp">Transaction</a>
    		</div>
  		</li>
  		<li class="nav-item dropdown">
    		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     			View
    		</a>
    		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
     			<a class="dropdown-item" href="ListTransactionServlet">Transaction list</a>
      			<a class="dropdown-item" href="PendingServlet">Pending</a>
      			<a class="dropdown-item" href="GoogleCharts.jsp">Charts</a>
    		</div>
  		</li>
  	    <li class="nav-item dropdown">
    		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     			Goal
    		</a>
    		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
     			<a class="dropdown-item" href="GoalPage.jsp">View goal</a>
    		</div>
  		</li>
  	    <li class="nav-item dropdown">
    		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
     			Account
    		</a>
    		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
     			<a class="dropdown-item" href="#"><%if (session.getAttribute(WebCon.USERNAME) != null)
     				out.println(session.getAttribute(WebCon.USERNAME));
     			%></a>
      			<a class="dropdown-item" href="LogoutServlet">Exit</a>
    		</div>
  		</li>
	</ul>
	</div>
</nav>