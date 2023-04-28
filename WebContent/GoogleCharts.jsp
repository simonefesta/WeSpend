<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		 import="logic.WebCon"%>    
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
<div id="piechart"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
// Carico google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Rappresentazione grafico
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Task', 'Hours per Day'],
  ['Vestiti', 7
  ],
  ['Cibo', 2],
  ['Casa', 4],
  ['Sport', 2],
]);

  var options = {'title':'Le mie spese', 'width':550, 'height':400};

  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>

</body>
</html>