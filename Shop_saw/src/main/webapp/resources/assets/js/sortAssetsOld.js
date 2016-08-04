/**
 * 
 */
//alert("Hi there!");
var elementsList = document.getElementsByName('formula');
var elementsList3 = document.getElementsByName('formula2');
var elementsList2 = new Array(elementsList,elementsList3);

var elementsList4 = document.getElementsByName('formula5');
elementsList2[0]=elementsList;
var filter = new Array();
var currentFilter;
var jsSaw; 
var oneSaw;
var f,f2;
function sortAssets2(saw,filterName,listOfSawrgdgs) {
	 jsSaw= listOfSawrgdgs;

	//f2=document.getElementById("fffo2").getAttribute("value");
//	f=document.getElementById("fffo").getAttribute("value");
	alert("Alert"+f);
	//qqqqqqqqq=listOfSawrgdgs;
//	oneSaw=JSON.parse(f);
	//oneSaw = jsSaw[1];
//currentFilter=oneSaw.getType();
	for(var xx=0; xx<elementsList2[0].length; xx++){
		if (elementsList2[0][xx].checked) {
			var thing = elementsList2[0][xx].value;
			alert(thing+ " checked"+saw.getType );
			filter[f]=thing;
			f++;
			}
		}
	changeList();
	}

function changeList(){
	return filter;
}