 <%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="logic.WebCon"
    	 import="java.sql.SQLException"
    	 import="logic.ab.controller.WebControllerGoal"
    	 import="java.util.List"
    	 import="logic.ab.entity.Goal"
    	 import="logic.Session"
    	 
    	 
    	%>
<!-- 
<jsp:useBean id="loginBean" scope="request"
             class="logic.view.bean.LoginBean"/>
<jsp:setProperty name="loginBean" property="*"/>-->
    <%
    if (!WebCon.checkLogin(session)) {
		response.sendRedirect(WebCon.LOGINPAGE);
		return;
	}
    List<Goal> listOfGoals=WebControllerGoal.getGoalList(session);
    if(listOfGoals!=null)
    {
    	if(listOfGoals.isEmpty())
    		listOfGoals=null;
    }

 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>weSpend</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/dashboard.css" rel="stylesheet">
    <link href="css/bootstrap-grid.css" rel="stylesheet">
    <link href="css/bootstrap-grid.min.css" rel="stylesheet">
    <link href="css/bootstrap-reboot.css" rel="stylesheet">
    <link href="css/bootstrap-reboot.min.css" rel="stylesheet">
    <link href="css/docs.min.css" rel="stylesheet">
    <link href="css/bootstrap-reboot.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
    
    
    
    <link rel="icon" href="/docs/4.4/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.4/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/4.4/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/4.4/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="/docs/4.4/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/4.4/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">
    
    <!-- Libreria google chart -->
   	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

</head>
<body>

<jsp:include page="NavBar.jsp"/>


<!--  MODALE_ADDGOAL_INIZIO -->
<div class="modal fade" id="Modal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
 
   <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add a Goal!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">
         <form class="form-group" action="GoalServlet" method="post">
Name:<br>
<input type="text" name="name" class="form-control" id="inputName" aria-describedby="emailHelp" placeholder="Insert Name" required="">
Price:<br>
<input type="number" min="0" name="price" class="form-control" id="inputNumber" aria-describedby="emailHelp" placeholder="Insert Price" required="">
Link:<br>
<input disabled type="text" name="link" class="form-control" id="inputLink" aria-describedby="emailHelp" placeholder="Insert Name" required="">
<div class="custom-control custom-switch">
  <input  type="checkbox" class="custom-control-input" id="customSwitch1">
  <label class="custom-control-label" for="customSwitch1" ></label>
</div>

<!-- Default unchecked -->
<div class="custom-control custom-radio">
  <input disabled type="radio" class="custom-control-input" id="defaultUnchecked" name="defaultExampleRadios">
  <label class="custom-control-label" for="defaultUnchecked">Amazon</label>
</div>

<!-- Default checked -->
<div class="custom-control custom-radio">
  <input disabled type="radio" class="custom-control-input" id="defaultChecked" name="defaultExampleRadios" checked>
  <label class="custom-control-label" for="defaultChecked">Ebay</label>
</div>

Description:<br>
<textarea type="text" name="description" size="150" class="form-control" id="inputDescription" placeholder="Write description ..." required=""></textarea>
    <div class="invalid-feedback">
      Per favore inserisci del testo nell'area.
    </div>
<br><input type="reset" class="btn-primary" value="Reset">

      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button class="btn btn-primary" name="submit" type="submit" value="submit">Add</button>
      </div>
       </form>
       </div>
    </div>
  </div>
 
</div>
<!--  MODALE_FINE -->


<!--  MODALE_ADDMONEY_INIZIO -->
<div class="modal fade" id="Modal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="display: none;" aria-hidden="true">
 
   <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add money!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">
         <form class="form-group" action="AddMoneyServlet" method="get">

Price:<br>
<input type="number" name="price" class="form-control" id="inputNumber" aria-describedby="emailHelp" placeholder="Insert Price" required="">
<br><input type="reset" class="btn-primary" value="Reset">

<br>
<input type="hidden" min="0" name="idGoal" class="form-control" id="inputIdGoalAddMoney" aria-describedby="emailHelp" placeholder="Insert Price" required="">
      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button class="btn btn-primary" name="submit" type="submit" value="submit">Add</button>
      </div>
       </form>
       </div>
    </div>
  </div>
 
</div>
<!--  MODALE_FINE -->




<div class="container-fluid" wfd-id="0">
<div class="row flex-xl-nowrap" wfd-id="1">
 <div class="col-md-3 col-xl-2 sidebar" wfd-id="157" style="position:static">
 <div class="row mb-3">
 <div class="col-md-2 themed-grid-col"><button type="button" class="btn btn-success" style="margin-top: 15px;" data-toggle="modal"data-target="#Modal1">+</button></div>
 <div class="col-md-10 themed-grid-col"><form role="search" class="bd-search d-flex align-items-center" wfd-id="183">
 
  <span class="algolia-autocomplete" wfd-id="184" style=" visibility: hidden; position: relative; display: inline-block; direction: ltr;"><input type="search" class="form-control ds-input" id="search-input" placeholder="Search..." aria-label="Search for..." autocomplete="off" data-docs-version="4.4" spellcheck="false" role="combobox" aria-autocomplete="list" aria-expanded="false" aria-owns="algolia-autocomplete-listbox-0" dir="auto" style="position: relative; vertical-align: top;" wfd-id="203"><pre aria-hidden="true" style="position: absolute; visibility: hidden; white-space: pre; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, &quot;Noto Sans&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;; font-size: 16px; font-style: normal; font-variant: normal; font-weight: 400; word-spacing: 0px; letter-spacing: normal; text-indent: 0px; text-rendering: auto; text-transform: none;"></pre><span class="ds-dropdown-menu" role="listbox" id="algolia-autocomplete-listbox-0" wfd-id="185" style="position: absolute; top: 100%; z-index: 100; display: none; left: 0px; right: auto;"><div class="ds-dataset-1"></div></span></span>
  <button class="btn btn-link bd-search-docs-toggle d-md-none p-0 ml-3" type="button" data-toggle="collapse" data-target="#bd-docs-nav" aria-controls="bd-docs-nav" aria-expanded="false" aria-label="Toggle docs navigation" wfd-id="210"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 30 30" role="img" focusable="false"><title>Menu</title><path stroke="currentColor" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2" d="M4 7h22M4 15h22M4 23h22"></path></svg></button>
</form></div>
 </div>
 
 <header>Goal List</header>
        <nav class="bd-links" id="bd-docs-nav" aria-label="Main navigation"style="height:60%; overflow:hidden; overflow-y:scroll;">
            <ul class="list-group list-group-flush">
  				<% 
  				if(listOfGoals != null){
  		 			//steps = new Vector<Vector<History>>();
  		 			for(int i=0; i<listOfGoals.size(); i++){
  		 				if(listOfGoals.get(i)!=null){
  		 					out.print("<li id=\""+i+"\" value=\""+(listOfGoals.get(i).getId())+ "\"  class=\"list-group-item\" ><div class=\"row mb-3\">"+
  		 								"<button id=\""+(listOfGoals.get(i).getId())+ "\"  type=\"button\" class=\"btn btn-success\" style=\"margin-right: 5px; background-color:#ffffff\"></button>"+
										"<a href=\"#\" onclick=\"gestore("+(listOfGoals.get(i).getId())+")\">"+(listOfGoals.get(i).getNome())+"</a></li>");
  		 					
  		 					
  		 					
  		 					
  		 					
  		 					//steps.add(listOfGoals.get(i).getHistory());
  		 				}
  		 			}
  		 		}
  				else
  					out.print("<li class=\"list-group-item\" ><div class=\"row mb-3\"><p>No goal. Add it!</p></div></li>");
  				%>
      
			</ul>
       </nav>
</div>


<div id="chart_div" style="width: 900px; height: 500px"></div>

<div class="col-md-3 col-xl-2 bd-sidebar" wfd-id="157">


        <nav class="bd-links" id="bd-docs-nav" aria-label="Main navigation"style="height:60%; overflow:hidden;">
            <ul class="list-group list-group-flush" >
            	<li class="list-group-item"></li>
           	 <li class="list-group-item" ><p style="float: right;"><strong>Name</strong><br> <%if(listOfGoals != null){for(int i=0; i<listOfGoals.size(); i++){ out.print("<p id=\"1p"+(listOfGoals.get(i).getId()) +"\" style=\"display: none; visibility: hidden;\">"+(listOfGoals.get(i).getNome())+"</p>");}}%>  </p></li>
           	 <li class="list-group-item"><p style="float: right;"><strong>Prezzo</strong><br> <%if(listOfGoals != null){for(int i=0; i<listOfGoals.size(); i++){ out.print("<p id=\"2p"+(listOfGoals.get(i).getId()) +"\" style=\"display: none; visibility: hidden;\">EUR "+(listOfGoals.get(i).getPrezzo())+"</p>");}}%></p></li>
           	 <li class="list-group-item"><form class="form-group" action="RefreshServlet" method="get">
           	<input type="hidden" type="number" min="0" name="goalIdToRefresh" class="form-control" id="goalIdToRefresh" required=""><button class="btn btn-info" style="float: right;" name="submit" type="submit" value="submit" >Refresh</button></form></li>
           	
           	 <li class="list-group-item"><button type="button" class="btn btn-success" style="float: right;" data-toggle="modal"data-target="#Modal2">Add money</button></li>
           	
           	 <li class="list-group-item"><form class="form-group" action="DeleteGoalServlet" method="get">
           	<input type="hidden" type="number" min="0" name="goalIdToDelete" class="form-control" id="goalIdToDelete" required=""><button class="btn btn-danger" style="float: right;" name="submit" type="submit" value="submit" >Delete</button></form></li>
  				
  				<div id="grafico">
  				<% 
  				if(listOfGoals != null){
  				for(int i=0; i<listOfGoals.size(); i++)
  				{
  					out.print("<div  id=\"3p"+(listOfGoals.get(i).getId())+"\">");
  					if(listOfGoals.get(i).getHistory()!=null)
  					{
  						for(int j=0; j<listOfGoals.get(i).getHistory().size(); j++)
  						{
  							out.print("<p hidden>"+listOfGoals.get(i).getHistory().get(j).getDate()+"M"+listOfGoals.get(i).getHistory().get(j).getMoney()+"</p>");
  						}
  					}
  					out.print("</div>");
  				} }%>
      			</div>
			</ul>
       </nav>
</div>
</div>
</div>

</body>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="/docs/4.4/dist/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.js"></script><script src="/docs/4.4/assets/js/docs.min.js"></script>
<script>

var date=[];
var money=[];

var date_prec=[];
var money_prec=[];
var N;

var ids=[];

document.getElementById("inputIdGoalAddMoney").value =-1;
document.getElementById("goalIdToDelete").value =-1;
document.getElementById("goalIdToRefresh").value =-1;

function gestore(id)
{
	date=[];
	money=[];
	var flag=-1;
	var num = ids.push(id);
	for(i=0; i<num; i++)
	{
		if(document.getElementById(ids[i]).style.backgroundColor=="rgb(40, 167, 69)")
		{
			flag=ids[i];
			break;
		}
	}
	//document.getElementById("1p"+id).removeAttribute("hidden");
	
	
	

	
	if(flag!=-1)
	{
		document.getElementById(id).style.backgroundColor ="#28a745";
		document.getElementById(flag).style.backgroundColor ="#ffffff";
		
		//mostra nome
		document.getElementById("1p"+id).style.display="inline";
		document.getElementById("1p"+id).style.visibility = "visible";
		//mostra prezzo
		document.getElementById("2p"+id).style.display="inline";
		document.getElementById("2p"+id).style.visibility = "visible";
		
		//nascondi nome
		document.getElementById("1p"+flag).style.display="none";
		document.getElementById("1p"+flag).style.visibility = "hidden";
		
		//nascondi prezzo
		document.getElementById("2p"+flag).style.display="none";
		document.getElementById("2p"+flag).style.visibility = "hidden";
	}
	else
	{
		document.getElementById(id).style.backgroundColor ="#28a745";
		document.getElementById("1p"+id).style.display="inline";
		document.getElementById("1p"+id).style.visibility = "visible";
		document.getElementById("2p"+id).style.display="inline";
		document.getElementById("2p"+id).style.visibility = "visible";
	}
		
	
	//history.pushState(null, '', '?id='+id);    
	
	document.getElementById("inputIdGoalAddMoney").value =id;
	//var url = "http://localhost:55122/weSpendProject/GoalPage.jsp?id=" + id;
	//window.location.href=url;
	
	//return false;
	document.getElementById("goalIdToDelete").value =id;
	document.getElementById("goalIdToRefresh").value =id;

	
	var j=0;
	var string="";
	var html=document.getElementById("3p"+id).innerHTML;
	//alert(document.getElementById("3p"+id).innerHTML);
	for(i=0; i<html.length; i++)
	{
		if(html[i]=="\"" && html[i+1]==">")
		{
			string="";
			for(j=2; j<=20; j++)
				string+=html[i+j];
			date.push(string);
		}
		if(html[i]=="M")
		{
			j=1;
			string="";

			while(html[i+j]!="<")
			{
			
				string+=html[i+j];
				j=j+1;
			}
			money.push(parseInt(string));
		}
	}
	/*for(i=0; i<date.length; i++)
	{
		alert(date[i]);
		alert(money[i]);
	}*/
	N=date.length;

	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
	
}

      function drawChart() {

    	var sum=0;
        var data = new google.visualization.DataTable();
        
        data.addColumn('date', 'Time of Day');
        data.addColumn('number', 'Rating');
	
        data.addRows([[new Date("2020-02-01"),0]]);
        for(i=0; i<N; i++)
        {
        	sum=sum+money[i];
        	
        	data.addRow([new Date(date[i]), sum]);
        }
        	
        

		
        var options = {
          title: 'Goal History',
          width: 900,
          height: 500,
          hAxis: {
            format: 'M/d/yy HH:MM:SS',
            gridlines: {count: 15}
          },
          vAxis: {
            gridlines: {color: 'none'},
            minValue: 0
          }
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);

        var button = document.getElementById('change');

        button.onclick = function () {

          // If the format option matches, change it to the new option,
          // if not, reset it to the original format.
          options.hAxis.format === 'M/d/yy' ?
          options.hAxis.format = 'MMM dd, yyyy' :
          options.hAxis.format = 'M/d/yy';

          chart.draw(data, options);
        };
      }
      
      //FINE GESTIONE GRAFICO


$('#customSwitch1').click(function () {
    if ($('#customSwitch1').is(":checked")) 
    {
    	$("#inputLink").removeAttr("disabled")
		$("#defaultUnchecked").removeAttr("disabled")
		$("#defaultChecked").removeAttr("disabled")
    }
    else 
    {
    	 $("#inputLink").val('');
         $("#inputLink").attr("disabled", "disabled")
         $("#defaultUnchecked").attr("disabled", "disabled")
         $("#defaultChecked").attr("disabled", "disabled")
    }
});
      
      



</script>

</html>