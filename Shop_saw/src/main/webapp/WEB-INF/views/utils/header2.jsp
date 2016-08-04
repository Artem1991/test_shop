<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/catalog-header.css" >


</head>

<body>

 <div id="loginBody">
 
 <%@ page import="ru.myproject.entity.product.*"%>
			<%
			
			HttpSession sessionsa = request.getSession(false);
			String name = (String) sessionsa.getAttribute("sessionName");
			Integer inCart = (Integer)sessionsa.getAttribute("inCart");
			Integer totalPrice = (Integer)sessionsa.getAttribute("totalPrice");
		//	String name = (String)request.getAttribute("sessionName");
			if(inCart==null){
				inCart=0;
			}
			if(totalPrice==null){
				totalPrice=0;
			}
			if(name!=null&&name!="anonymousUser"){
				%>
				<a class="rightHeader" href="login">Welcome <%out.println(name);%></a>
			<%
			}else{
				%>
				<a class="rightHeader" href="login">Login</a>
				<a id="register" class="rightHeader" href="toRegistrationForm">Register    </a>
			<%
			}
			%>

 </div>
<div id="mainBody">

<%
if(name!=null){
	if(name.equals("admin")){
		%>
		
<a href="admin">Admin</a>
		<%
	}
}
%>
<a><img id="header-label" src="${pageContext.request.contextPath}/resources/assets/img_assets/shop_label.jpeg" onerror="this.src='${pageContext.request.contextPath}/resources/assets/img_assets/image-not-found.jpg'"   /></a>

<a href="cart?pg="><img id="cart-icon" src="${pageContext.request.contextPath}/resources/assets/img_assets/icon-cart.png" onerror="this.src='${pageContext.request.contextPath}/resources/assets/img_assets/image-not-found.jpg'"  />
<span class="symbol"><%out.println(inCart);%></span>
<span id="label-your-cart">Your cart:</span>
<span id="totalPrice"><%out.println(totalPrice);%>$</span>
<span></span>
</a>

<!-- <a> <form:form method="GET" commandName="sawToCart" action="cart" class="editFields" >
<button  name="pg" >Cart</button>
</form:form></a>



<a> <form:form method="GET" commandName="sawToCart" action="admin" class="editFields">
<button  name="pg" >Admin</button>
</form:form></a>



<a><form:form method="GET" commandName="sawToCart" action="login" class="editFields">
<button  name="pg" >Login</button>
</form:form></a>

<a><form:form method="POST" commandName="sawToCart" action="toRegistrationForm" class="editFields">
<button  name="pg" >Registration</button>
</form:form></a> -->
</div>
</body>
</html>