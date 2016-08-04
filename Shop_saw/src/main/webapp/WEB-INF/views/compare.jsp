<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compare saws</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/catalog-aside.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/catalog-list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/compare.css" >
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.myproject.entity.product.*" %>

<div>
<!-- Header -->
		<c:import url="utils/header.jsp" />
		<!-- /Header -->
</div>
		

<table id="mytab" class="spc" >
		<caption>Compare</caption>


<%List<Saw> saw;
HttpSession sessionsa = request.getSession(false);
saw = (List<Saw>) sessionsa.getAttribute("sawCompare");%>

<tr><td>image</td>
<%if(saw!=null){
	for(Saw item : saw){%>
<td><img src=${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage1()%> alt="jpg not found" height="250" width="250"/></td>
<%}}%></tr>

<tr><td>type</td>
<%if(saw!=null){
	for(Saw item : saw){%>
<td><%out.println(item.getType());%></td>
<%}}%></tr>

<tr><td>model</td>
<%if(saw!=null){
	for(Saw item : saw){%>
<td><%out.println(item.getModel());%>
</td><%}}%></tr>

<tr><td>description</td>
<%if(saw!=null){
	for(Saw item : saw){%>
<td><%out.println(item.getDescription());%>
</td><%	}}%></tr>

<tr><td>brand</td>
<%if(saw!=null){
	for(Saw item : saw){%>
<td><%out.println(item.getBrand());%>
</td><%}}%></tr>

<tr><td>manufacturer</td>
<%if(saw!=null){
	for(Saw item : saw){%>
<td><%out.println(item.getManufacturer());%>
</td><%}}%></tr>
</table>




<form:form method="GET" commandName="sawToEdit" action="client" class="editFields">
	<input id="btn-compare" type="submit" name ="pg" value="Return">
</form:form>
</body>
</html>