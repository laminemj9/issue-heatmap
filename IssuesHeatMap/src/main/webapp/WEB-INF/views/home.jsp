<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>HeatMap Display</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script
	  
	<link rel="stylesheet" type="text/css" href="css/style.css" media="screen"/>
<script language="javascript">

 $( function() {
    var dateFormat = "mm/dd/yy",
      from = $( "#startDate" )
        .datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 3
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#endDate" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
  } );
  

function fetchData()
{

	var startDateVal=$('#startDate');
	var endDateVal=$('#endDate');
	var regionVal=$('#endDate');

	if (validateDateFormat(startDateVal)&&  validateDateFormat(endDateVal) )
	{
		$.ajax({
			   url: 'http://localhost:8080/IssuesHeatMap/rest/issues/byregionanddate',
			   data: {
				  startdate:startDateVal
				  enddate:endDateVal,
				  region:regionVal
				  },
			   error: function (jqXHR, exception) {
						var msg = '';
						if (jqXHR.status === 0) {
							msg = 'Not connect.\n Verify Network.';
						} else if (jqXHR.status == 402) {
							msg = jqXHR.responseText;
						}else if (jqXHR.status == 404) {
							msg = 'Requested page not found. [404]';
						} else if (jqXHR.status == 500) {
							msg = jqXHR.responseText;
						} else if (exception === 'parsererror') {
							msg = 'Requested JSON parse failed.';
						} else if (exception === 'timeout') {
							msg = 'Time out error.';
						} else if (exception === 'abort') {
							msg = 'Ajax request aborted.';
						} else {
							msg = 'Uncaught Error.\n' + jqXHR.responseText;
						}
					
						$('#msg').html('<p>'+msg+'</p>');
					}
			   ,
			   dataType: 'json',
			   success: function(data) {
				  
					var json_issues = $.parseJSON(data);//parse JSON
					
					
					for (var i in json_issues) 
					{
						var addressVal=json_issues[i].address;
						
					}
				  
			   },
			   type: 'GET'
			});

		
	}
	
	
}

function validateDateFormat(dateVal)
{
	var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
	  // Match the date format through regular expression
	  if(dateVal.match(dateformat))
	  {
		  
		  //Test which seperator is used '/' or '-'
		  var opera1 = dateValsplit('/');
		  var opera2 = dateVal.split('-');
		  lopera1 = opera1.length;
		  lopera2 = opera2.length;
		  // Extract the string into month, date and year
		  if (lopera1>1)
		  {
			var pdate = inputText.value.split('/');
		  }
		  else if (lopera2>1)
		  {
			var pdate = inputText.value.split('-');
		  }
		  var dd = parseInt(pdate[0]);
		  var mm  = parseInt(pdate[1]);
		  var yy = parseInt(pdate[2]);
		  // Create list of days of a month [assume there is no leap year by default]
		  var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
		  if (mm==1 || mm>2)
		  {
			  if (dd>ListofDays[mm-1])
			  {
			  alert('Invalid date format!');
			  return false;
			  }
		  }
		  if (mm==2)
		  {
			  var lyear = false;
			  if ( (!(yy % 4) && yy % 100) || !(yy % 400)) 
			  {
				lyear = true;
			  }
			  if ((lyear==false) && (dd>=29))
			  {
				  alert('Invalid date format!');
				  return false;
			  }
			  if ((lyear==true) && (dd>29))
			  {
				  alert('Invalid date format!');
				  return false;
			  }
		  }
		  
		  return true;
	  }
	  else
	  {
	  
		return false;
	  }
}  

function addDataPoint(addressVal)
{
	var leftVal=0;
	var topVal=0;
	//code for address to be converted  to lat long google map api using and 
	//then its scaled to left  top values on given size of map using formulea
	//begin
	
	
	
	//this implementation needs more time and was ingored
	
	
	
	//end
	
	
						$('#map').append($('<div/>', {
								css: {
									left			: leftVal,
									top				: topVal,
									display			: 'inline'
								},
								className			: 'dot'
						}));
						
}



</script>

</head>
<body>

<div id="msg"></div>
<h2>Select Date </h2>

<label for="startDate">From</label>
<input type="text" id="startDate" name="startDate" name="from">
<label for="endDate">to</label>
<input type="text" id="endDate" name="endDate" name="to">
<label for="from">From</label>
<select name="region" id="region">
<option  value=""> Select Region</option >
<option  value="USA">USA</option >
<option  value="CA">CANADA</option >
<option  value="UK">United Kingdom</option >


</select>
<input type="button" name="fetchData"  value="FetchData" onclick="fetchData()">


<div id="map" >

</div>
</body>
</html>
