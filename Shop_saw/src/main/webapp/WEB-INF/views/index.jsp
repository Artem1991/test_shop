<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page import="javax.swing.text.html.ImageView"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Shop</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/catalog-aside.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/catalog-list.css">

</head>
<body>
<form:form method="GET" commandName="sawToCart" action="client" class="editFields">

	<!-- Header -->
		<c:import url="utils/header.jsp" />
		<!-- /Header -->

		
		<aside>


		<div class="firstLeftHeader">
			<label class="aside-caption">All Departments</label>
		</div>
		
		<p>
		<a id="button-first" href="client?">Saws</a>
		</p>

		</aside>


</form:form>
</body>
</html>