/**
 * 
 */
//alert("Hi there!");
var filterBrand = document.getElementsByName('filterBrand');
var filterManufacturer = document.getElementsByName('filterManufacturer');
var filterType = document.getElementsByName('filterType');
var filters = new Array(filterBrand,filterManufacturer,filterType);

//elementsList2[0]=elementsList;
//var filter = [,];
function sortAssets2() {
	//}
	changeList();
	}

function changeList(){
	var sawList=JSON.parse(jsonSaws);
	var filter = new Array();
	var f=0;
	for(var i=0; i<filters[0].length; i++){
		if (filters[0][i].checked) {
			var thing = filters[0][i].value;
			//alert(thing+ " checked" );
			//filter.
			filter[f]=thing;
			f++;
			}
		}
	sawList=sortBrand(filter,sawList);
	filter = new Array();
	
	for(var i=0; i<filters[1].length; i++){
		if (filters[1][i].checked) {
			var thing = filters[1][i].value;
			//alert(thing+ " checked" );
			//filter.
			filter[f]=thing;
			f++;
			}
		}
	sawList=sortManufacturer(filter,sawList);
	
	



	filter = new Array();
	
	for(var i=0; i<filters[2].length; i++){
		if (filters[2][i].checked) {
			var thing = filters[2][i].value;
			//alert(thing+ " checked" );
			//filter.
			filter[2]=thing;
			f++;
			}
		}
	sawList=sortType(filter,sawList);
	
	
//f++;

drawTable(sawList);
  
//	document.getElementById("mytab").innerHTML = "<caption >Model/Description</caption>";   

		//document.getElementById("mytab").innerHTML = ;
	/*	document.getElementById("mytab").innerHTML =
			"<caption >Model/Description</caption>"+
			"<tr>"+
			"<td>"+"<img src='data:image/png;base64,'"+sawList[0].image1+"'/>"+"<p>"+sawList[0].type+"<p>"+sawList[0].brend+"<p>"+sawList[0].model+
		//"<form:form method='POST'  action='merchandise' class='editFields' commandName='sawC'>"+
		"<p><button  name='btn' value="+sawList[0].id+" >view</button></p></td>"+
	//	"</form:form>"+
			

		"<td>"+"<img src='data:image/png;base64,'"+sawList[1].image1+"'/>"+"<p>"+sawList[1].type+"<p>"+sawList[1].brend+"<p>"+sawList[1].model+
		//"<form:form method='POST'  action='merchandise' class='editFields' commandName='sawC'>"+
		"<p><button  name='btn' value="+sawList[1].id+" >view</button></p></td>"+
	//	"</form:form>"+
		

		"<td>"+"<img src='data:image/png;base64,'"+sawList[2].image1+"'/>"+"<p>"+sawList[2].type+"<p>"+sawList[2].brend+"<p>"+sawList[2].model+
		//"<form:form method='POST'  action='merchandise' class='editFields' commandName='sawC'>"+
		"<p><button  name='btn' value="+sawList[2].id+" >view</button></p></td>"+
	//	"</form:form>"+


		"</tr>";
	//}
*/


}

function sortBrand(filter,sawList){
	  var sawListNew=[];
	  var countSaw=0;
	  debugger;
	  if(filter.length>0){
		  for(var i=0;i<filter.length;i++){
			  for(var j=0; j<sawList.length;j++){
					if(filter[i]==sawList[j].brend){
						sawListNew[countSaw]=sawList[j];
						countSaw++;
					}
			  }
		  }
	  }
	  else{
		  sawListNew=sawList;
	  }
	  return sawListNew;
	  }
		  
	
	function sortManufacturer(filter,sawList){
	  var sawListNew=[];
	  var countSaw=0;
	  debugger;
	  if(filter.length>0){
		  for(var i=0;i<filter.length;i++){
			  for(var j=0; j<sawList.length;j++){
					if(filter[i]==sawList[j].manufacturer){
						sawListNew[countSaw]=sawList[j];
						countSaw++;
					}
			  }
		  }
	  }
	  else{
		  sawListNew=sawList;
	  }
	  return sawListNew;
	}
	
		
	
	function sortType(filter,sawList){
	  var sawListNew=[];
	  var countSaw=0;
	  debugger;
	  if(filter.length>0){
		  for(var i=0;i<filter.length;i++){
			  for(var j=0; j<sawList.length;j++){
					if(filter[i]==sawList[j].type){
						sawListNew[countSaw]=sawList[j];
						countSaw++;
					}
			  }
		  }
	  }
	  else{
		  sawListNew=sawList;
	  }
	  return sawListNew;
	}
		 
	function drawTable(sawList){
		var table = document.getElementById("mytab");
		var inRow=4;
		var currentCell=0;
		var currentRow=0;
		while (table.tBodies[0].rows[0]) {
		    table.tBodies[0].deleteRow(0);
		}
		
		for(var i =0; i<sawList.length; i++){
			
		

			var htmlText="<img src='data:image/png;base64,'"+sawList[i].image1+"'/>"+"<p>"+sawList[i].type+"<p>"+sawList[i].brend+"<p>"+sawList[i].model+
			"<p><button  name='btn' value="+sawList[i].id+" >view</button></p>";


			if(inRow>=3){
				var row = table.insertRow(currentRow);
				currentRow++;
				currentCell=0;
				inRow=0;
			}
			var cell = row.insertCell(currentCell);
			cell.innerHTML = htmlText;
			currentCell++;
			inRow++;
		}
		
		
		
	    
	    //var cell2 = row.insertCell(1);
	  //  cell1.innerHTML = "NEW CELL1";
	   // cell2.innerHTML = "NEW CELL2";
		ggg=88;
	}
	
	
	
	/*  
	  
//	document.getElementById("mytab").innerHTML = "<caption >Model/Description</caption>";   
	for(var sch=0;sch<=f;sch++){
		//document.getElementById("mytab").innerHTML = ;
		document.getElementById("mytab").innerHTML ="<caption >Model/Description</caption>"
	//	for(var sch=0;sch<=f;sch++){
		+"<tr><td>"+sawListNew[0].type+"</td><td>"+filter.length+"</td><td>"+sawList.length+"</td></tr>"};
	//}*/
