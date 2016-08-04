<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/cart.css">
<script type="text/javascript">
$(document).ready(function(){
	
})
</script>
</head>
<body>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="ru.myproject.entity.product.*"%>
<div>
<!-- Header -->
		<c:import url="utils/header.jsp" />
		<!-- /Header -->
</div>


	<%
		List<Saw> saw;
		HttpSession sessionsa = request.getSession(false);
		saw = (List<Saw>) sessionsa.getAttribute("sawCart");
		List<String> quant;
		quant = (List<String>) sessionsa.getAttribute("quantity");
		int num = 0;
		int total = 0;
		int quantity = 0;
		String test;
	%>
	<form:form method="GET" commandName="orderToEdit" action="order"
		class="editFields">


		<table id="mytab" class="spc">
			<caption>Cart</caption>


			<!-- <tr class="table">
			    <td class="table" onclick='sort(this)'>Product ID</td>
				<td class="table" onclick='sort(this)'>Photo</td>
				<td class="table" onclick='sort(this)'>Description</td>
				<td class="table" onclick='sort(this)'>Quantity</td>
				<td class="table" onclick='sort(this)'>Price</td>
				<td class="table" onclick='sort(this)'>Total price</td>
			</tr> -->
			<%
				if (saw != null) {
					//for (int i=0; i<saw.size();i++){
					for (Saw item : saw) {
			%>
			<%
			test = String.valueOf(quant.get(num));
				quantity = Integer.parseInt(test);
			%>
			<tr class="table">
				<!-- <td class="table" ondblclick='sel()'>
					<%
					//	out.println(item.getId());
					%>
				</td> -->
				<td class="table" ondblclick='sel()'><img class="image-cart"
					src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage1()%>"
					onerror="this.src='${pageContext.request.contextPath}/resources/assets/img_assets/image-not-found.jpg'" />
				</td>
				<td class="table" ondblclick='sel()'>
					<%
						out.println(item.getModel());
					%>
				</td>
				 <td class="table" ondblclick='sel()'>
					<button name ="button" value = "<%=item.getId()+"-"%>">-</button>
					<%
						out.println(String.valueOf(quant.get(num)));
					%>
					<!--  <input type="text" id="quantity">-->
					<button name ="button" value = "<%=item.getId()+"+"%>">+</button>
					</td>
				<td>
				<div class="table" ondblclick='sel()'>
					<%
						out.println("Price for one : "+item.getPrice());
					%>
				</div>
				<div class="table" ondblclick='sel()'>
					<%
						out.println("Full price : "+item.getPrice() * quantity);
						total = total + (item.getPrice() * quantity);
						num++;
					%>

				</div>
				</td>
				<td class="table" ondblclick='sel()'>
				<button name ="button" value = "<%=item.getId()+"x"%>">Delete</button>
		
				</td>
			</tr>
			<%
				}
					}
			%>
		</table>
		<label>  <% out.println("Order : "+String.valueOf(total)); %></label>
		<p>
			<input  class="button-cart" type="submit" name="pg" value="Order">
	</form:form>
	<form:form method="GET" commandName="sawToEdit" action="client"
		class="editFields">
		<input class="button-cart" type="submit" name="pg" value="Client page">
	</form:form>
		<!-- Ending -->
		<c:import url="utils/ending.jsp" />
		<!-- /Ending -->
</body>
</html>