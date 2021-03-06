<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/contact.css">
<title>Payment</title>
</head>
<body>

		<!-- Header -->
		<c:import url="utils/header.jsp" />
		<!-- /Header -->
		<table>
		<tr><td>Наличный расчет</td></tr>
		<tr><td>Оплата электронными деньгами EasyPay</td></tr>
		<tr><td>Оплата электронными деньгами WebMoney (WMB)</td></tr>
		</table>
		<!-- Ending -->
		<c:import url="utils/ending.jsp" />
		<!-- /Ending -->
</body>
</html>