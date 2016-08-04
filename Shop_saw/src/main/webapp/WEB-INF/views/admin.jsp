<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page import="javax.swing.text.html.ImageView"%>
<%@page import="java.sql.ResultSet"%>

<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=cp1251" />

	<title>Home</title>
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.myproject.entity.product.*" %>




<% 
String imageAdress = "${pageContext.request.contextPath}/resources/assets/img_assets";
String xmlAdress = "${pageContext.request.contextPath}/resources/assets/xlsx";
List<Saw> saw;
HttpSession sessionsa = request.getSession(false);
saw = (List<Saw>) sessionsa.getAttribute("saw"); 
%>



<table id="mytab" class="spc" border="1" cellspacing="2" cellpadding="2" width="98%"   >
		<caption>Information</caption>


<tr>
<td onclick='sort(this)'>
ID
</td>
<td onclick='sort(this)'>
Type
</td>
<td onclick='sort(this)'>
Model
</td>
<td  onclick='sort(this)'>
Brand
</td>
<td  onclick='sort(this)'>
Manufacturer
</td>
<td  onclick='sort(this)'>
State
</td>
<td  onclick='sort(this)'>
Year
</td>
<td onclick='sort(this)'>
Location
</td>
<td onclick='sort(this)'>
Round size
</td>
<td onclick='sort(this)'>
Rectangular length
</td>
<td onclick='sort(this)'>
Rectangular width
</td>
<td onclick='sort(this)'>
Blade speed
</td>
<td onclick='sort(this)'>
Blade power
</td>
<td onclick='sort(this)'>
Hydro power
</td>
<td onclick='sort(this)'>
Pump power
</td>
<td onclick='sort(this)'>
Blade size
</td>
<td onclick='sort(this)'>
Blade tension
</td>
<td onclick='sort(this)'>
Transfer placement
</td>
<td onclick='sort(this)'>
System control
</td>
<td onclick='sort(this)'>
Bench size
</td>
<td onclick='sort(this)'>
Bench weight
</td>
<td onclick='sort(this)'>
Prize
</td>
<td onclick='sort(this)'>
Image 1
</td>
<td onclick='sort(this)'>
Image 2
</td>
<td onclick='sort(this)'>
Image 3
</td>
<td onclick='sort(this)'>
Image 4
</td>
<td onclick='sort(this)'>
Image 5
</td>
<td onclick='sort(this)'>
Description
</td>
</tr>
<%
if(saw!=null){
	for(Saw item : saw){
%>
		
		<tr><td ondblclick='sel()'>
		<%out.println(item.getId());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getType());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getModel());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getBrand()); %>
		</td><td ondblclick='sel()'>
		<%out.println(item.getManufacturer());%>
		</td><td ondblclick="sel()">
		<%out.println(item.getState());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getYear()); %>
		</td><td ondblclick='sel()'>
		<%out.println(item.getLocation());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getRoundSize());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getRectangularLength());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getRectangularWidth());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getBladeSpeed());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getBladePower());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getHydroPower());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getPumpPower());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getBladeSize());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getBladeTension());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getTransferPlacement());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getSystemControl());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getBenchSize());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getBenchWeight());%>
		</td><td ondblclick='sel()'>
		<%out.println(item.getPrice());%>
		</td><td ondblclick='sel()'>
		<img src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage1()%>" alt="jpg not found" height="100" width="100"/>
		</td><td ondblclick='sel()'>
		<img src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage2()%>" alt="jpg not found" height="100" width="100"/>
		</td><td ondblclick='sel()'>
		<img src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage3()%>" alt="jpg not found" height="100" width="100"/>
		</td><td ondblclick='sel()'>
		<img src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage4()%>" alt="jpg not found" height="100" width="100"/>
		</td><td ondblclick='sel()'>
		<img src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage5()%>" alt="jpg not found" height="100" width="100"/>
		</td><td ondblclick='sel()'>
		<%out.println(item.getDescription());%>
		</td></tr>
		<%
	}
}
%>
</table>

<form:form method="POST" commandName="sawToEdit" action="edit" class="editFields">

	<input type="submit" name ="pg" value="Add">
	
	<input type="submit" name ="pg" value="Delete">
	
	<input type="submit" name ="pg" value="Edit">
	<p>
	<form:label path="id">ID</form:label>
	</p><p>
	<form:input path="id"/>
	</p><p>
	<form:label path="type">Type</form:label>
	</p><p>
	<form:input path="type"/>
	</p><p>
	<form:label path="model">Model</form:label>
	</p><p>
	<form:input path="model"/>
	</p><p>
	<form:label path="brand">Brand</form:label>
	</p><p>
	<form:input path="brand"/>
	</p><p>
	<form:label path="manufacturer">Manufacturer</form:label>
	</p><p>
	<form:input path="manufacturer"/>
	</p><p>
	<form:label path="state">State</form:label>
	</p><p>
	<form:input path="state"/>
	</p><p>
	<form:label path="year">Year</form:label>
	</p><p>
	<form:input path="year"/>
	</p><p>
	<form:label path="location">Location</form:label>
	</p><p>
	<form:input path="location"/>
	</p><p>
	<form:label path="roundSize">Round size</form:label>
	</p><p>
	<form:input path="roundSize"/>
	</p><p>
	<form:label path="rectangularLength">Rectangular length</form:label>
	</p><p>
	<form:input path="rectangularLength"/>
	</p><p>
	<form:label path="rectangularWidth">Rectangular width</form:label>
	</p><p>
	<form:input path="rectangularWidth"/>
	</p><p>
	<form:label path="bladeSpeed">Blade speed</form:label>
	</p><p>
	<form:input path="bladeSpeed"/>
	</p><p>
	<form:label path="bladePower">Blade power</form:label>
	</p><p>
	<form:input path="bladePower"/>
	</p><p>
	<form:label path="hydroPower">Hydro power</form:label>
	</p><p>
	<form:input path="hydroPower"/>
	</p><p>
	<form:label path="pumpPower">Pump power</form:label>
	</p><p>
	<form:input path="pumpPower"/>
	</p><p>
	<form:label path="bladeSize">Blade size</form:label>
	</p><p>
	<form:input path="bladeSize"/>
	</p><p>
	<form:label path="bladeTension">Blade tension</form:label>
	</p><p>
	<form:input path="bladeTension"/>
	</p><p>
	<form:label path="transferPlacement">Transfer placement</form:label>
	</p><p>
	<form:input path="transferPlacement"/>
	</p><p>
	<form:label path="systemControl">System control</form:label>
	</p><p>
	<form:input path="systemControl"/>
	</p><p>
	<form:label path="benchSize">Bench size</form:label>
	</p><p>
	<form:input path="benchSize"/>
	</p><p>
	<form:label path="benchWeight">Bench weight</form:label>
	</p><p>
	<form:input path="benchWeight"/>
	</p><p>
	<form:label path="price">Price</form:label>
	</p><p>
	<form:input path="price"/>
	</p><p>
	<form:label path="image1">Image 1</form:label>
	</p><p>
	<input type="file" name="image1"/>
	</p><p>
	<form:label path="image2">Image 2</form:label>
	</p><p>
	<input type="file" name="image2"/>
	</p><p>
	<form:label path="image3">Image 3</form:label>
	</p><p>
	<input type="file" name="image3"/>
	</p><p>
	<form:label path="image4">Image 4</form:label>
	</p><p>
	<input type="file" name="image4"/>
	</p><p>
	<form:label path="image5">Image 5</form:label>
	</p><p>
	<input type="file" name="image5"/>
	</p><p>
	<form:label path="description">Description</form:label>
	</p><p>
	<form:input path="description"/>
	</p>
</form:form>

<form:form method="POST" commandName="sawToEdit" action="addFromXml" class="editFields">
	<input type="file" name="adressExcel" accept=".xlsx">
	<input type="submit" name="pg" value="Input" title="input from excel">
</form:form>


<form:form method="GET" commandName="sawToEdit" action="client" class="editFields">
	<input type="submit" name ="pg" value="Client page">
</form:form>




</body>
</html>
