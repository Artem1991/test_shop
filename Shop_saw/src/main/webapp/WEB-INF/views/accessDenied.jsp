<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Access Denied</title>

</head>
<body>
У Вас нет прав доступа к этой странице
<p>
<form:form method="GET" commandName="sawToCart" action="client" class="editFields">
<button  name="pg" >Shop</button>

</form:form>
</body>
</html>