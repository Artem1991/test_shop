<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/login.css" >

</head>
<body>

<form method="POST"
	action="j_spring_security_check"
 name="form_login"  >
	
	
	<table class="form-login">
	
	
		<tr>
			<td>User</td>
			<td><input type="text" name="username" value=""></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
		
		</tr>
		
		<tr>
			<td><input name="ttt" type="checkbox" class="checkAdmin" value="true"/> 
		
		<c:if test="${not empty error }">
		<span class="error">${error}</span>
		</c:if>
		
		<label for="remember_me">Remember me</label>
		</td>
		
		<td><input type="submit" name="btnLogin" value="Submit"></td>
		</tr>
		
		<tr>
			<td><a href="j_spring_security_logout">logout</a></td>
		</tr>
	</table>

</form>
</body>
</html>