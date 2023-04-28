<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" 
         import="logic.WebCon"
         import="logic.exceptions.DataInsertionException"
         import="logic.enumerations.Category"
         import="logic.enumerations.Month"
         import="logic.view.bean.Date"
         import="java.time.LocalDateTime"%>

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
	if (!WebCon.checkLogin(session)) {
		response.sendRedirect(WebCon.LOGINPAGE);
		return;
	}
%>
<jsp:include page="NavBar.jsp"/>
<div class="container">
	<form action="AddTransactionFormServlet" name="myform" method="POST">
		<div class="row">
			<div class="col-md-6 mb-3">
          		<label for="value"></label>
          		<div class="input-group">
            		<div class="input-group-prepend">
              			<span class="input-group-text">€</span>
            		</div>
            		<input type="text" class="form-control" id="value" name="value" placeholder="0.00" required
            			<%
            			try{
            				if (!request.getParameter("value").isEmpty()){
            					out.print("value=\"" + request.getParameter("value") + "\" ");
            				}
            			} catch (Exception e) {}%>>
          		</div>
        	</div>
   		</div>
   		
        <div class="row">
        	<div class="col-md-6 mb-3">
            	<label for="comment">Comment</label>
            	<input type="text" class="form-control" id="comment" name="comment" required
            		   placeholder="Write your comment..."
            			<%
            			try{
            				if (!request.getParameter("comment").isEmpty()){
            					out.print("value=\"" + request.getParameter("comment") + "\" ");
            				}
            			} catch (Exception e) {}%>>
          	</div>
          	<div class="col-md-6 mb-3">
            	<label for="type">Category</label>
            	<select class="custom-select d-block w-100" id="type" name="type" required>
            	<%
            	for (Category cat : Category.values()) {
            		if (cat.getName().equals(request.getParameter("type")))
            			out.println("<option selected>" + cat.getName() + "</option>");
            		else
            			out.println("<option>" + cat.getName() + "</option>");
           		}%>
            	</select>
          	</div>
        </div>

        <div class="row">
          	<div class="col-md-3 mb-3">
            	<label for="day">Day</label>
            	<select class="custom-select d-block w-100" id="day" name="day" required>
             	<%
             	LocalDateTime now = LocalDateTime.now(); 
            	int actualDay = now.getDayOfMonth();
           		for (int i=1; i<32; i++) {
           			if (i == actualDay)
           				out.println("<option selected>" + Integer.toString(i) + "</option>");
           			else
           				out.println("<option>" + Integer.toString(i) + "</option>");
           		}%>
            	</select>
          	</div>
          	<div class="col-md-3 mb-3">
            	<label for="month">Month</label>
            	<select class="custom-select d-block w-100" id="month" name="month" required>
            	<%
            	String actualMonth = Month.valueOf(now.getMonth().name()).getName();
            	for (Month mon : Month.values()) {
            		if (!mon.getName().equals(actualMonth))
            			out.println("<option>" + mon.getName() + "</option>");
            		else
            			out.println("<option selected>" + mon.getName() + "</option>");
           		}%>
            	</select>
          	</div>
          	<div class="col-md-3 mb-3">
            	<label for="year">Year</label>
            	<input type="text" class="form-control" id="year" name="year" required placeholder="yyyy"
            		   value="<% out.println(Integer.toString(now.getYear())); %>">
          	</div>
        </div>

		<br>
        <div class="row">
           	<div class="col-md-3 mb-3">
            	<input type="text" class="form-control" id="hour" name="hour" required
            		   placeholder="hh" value="<% out.println(Integer.toString(now.getHour())); %>">
          	</div>
          	<p style="padding-top:5px">:</p>
          	<div class="col-md-3 mb-3">
            	<input type="text" class="form-control" id="minute" name="minute" required
            		   placeholder="mm" value="<% out.println(Integer.toString(now.getMinute())); %>">
          	</div>
       		<div class="col">
				<input type="submit" id="submit" type="button" class="btn btn-success"
                       value="Create" name="submit" >
				<a href="DeleteTransaction.jsp" class="btn btn-danger" tabindex="-1" role="button" aria-disabled="true">Cancel</a>
			</div>
			<div class="col">
			</div>
        </div>
        <hr class="mb-4">
        <div class="row">
			<div class="col-md-4">
				<input name="debt" type="submit" id="debt" 
                	   value="Create debt" class="btn btn-info">
            </div>
           	<div class="col-md-4">
				<input name="share" type="submit" id="share"
                       value="Divide expense" class="btn btn-info">
			</div>
        </div>       
   	</form>
   	<hr class="mb-4">
	<label><%if (session.getAttribute("err") != null) {
		out.println(session.getAttribute("err"));
		session.setAttribute("err", null);
	}%></label> 
	
      
</div>
</body>
</html>