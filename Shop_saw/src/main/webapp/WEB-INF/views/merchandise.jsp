
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=cp1251">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/merchandise.css">

<title>Sale</title>
</head>
<body>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.myproject.entity.product.*" %>
<div>
<!-- Header -->
		<c:import url="utils/header.jsp" />
		<!-- /Header -->
</div>
		
<%
HttpSession sessionsa = request.getSession(false);
	Saw saw = (Saw)sessionsa.getAttribute("merchandise");
%>
<form:form method="GET" action="addToCart">
<div class="merchandise">
		<div class="images">
			<div>
				<img class="image-main" src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=saw.getImage1()%>" alt="jpg not found" height="100" width="100"/>
			</div>
				<img class="image-small" src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=saw.getImage2()%>" alt="jpg not found" height="100" width="100"/>
				<img class="image-small" src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=saw.getImage3()%>" alt="jpg not found" height="100" width="100"/>
				<img class="image-small" src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=saw.getImage4()%>" alt="jpg not found" height="100" width="100"/>
				<img class="image-small" src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=saw.getImage5()%>" alt="jpg not found" height="100" width="100"/>
		</div>
		<div class="information">
			<div><h4><%=saw.getType() %></h4></div>
			<div><h4><%=saw.getModel() %></h4></div>
			<div><h5><%=saw.getDescription() %></h5></div>
			<div><input type="text" name="quantity" value="1" title="quantity"></div>
			<div><button name="pg" value="<%=saw.getId()%>" >AddToCart</button></div>
		</div>
</div>
</form:form>
<form:form method="GET" commandName="sawToEdit" action="client" class="editFields">
	<input type="submit" name ="pg" value="Client page">
</form:form>
		<!-- Ending -->
		<c:import url="utils/ending.jsp" />
		<!-- /Ending -->
</body>
</html>