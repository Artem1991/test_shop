<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Shop</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/catalog-aside.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/css/catalog-list.css">


<script type="text/javascript" charset="UTF-8">
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
</script>



<meta charset="utf-8">
<title>aside</title>
<script>
   document.createElement('aside');
   document.createElement('article');
  </script>



</head>
<body class="catalog-body">
	<div class="working-space">
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="java.util.Set"%>
		<%@ page import="java.util.List"%>
		<%@ page import="ru.myproject.entity.product.*"%>
		<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>

		<%
			HttpSession sessionsa = request.getSession(false);
			List<Set> sortList = (List<Set>) sessionsa.getAttribute("sortList");
			Set<String> model_saw = sortList.get(0);
			Set<String> brand = sortList.get(1);
			Set<String> location = sortList.get(2);
			Set<String> manufacturer = sortList.get(3);
			Set<String> state = sortList.get(4);
			Set<String> year = sortList.get(5);
			int i = 0;
			String style = (String) sessionsa.getAttribute("style");
			List<Saw> saw;
			saw = (List<Saw>) sessionsa.getAttribute("saw");
			String[][] listOfSaws = new String[saw.size()][6];
			String filter;
			String jsonInString = new String();
			String jsonInString2 = new String();
			int page_number = (Integer) sessionsa.getAttribute("pageNumber");
			//---------------------------------------------------------

			if (saw != null) {

				ObjectMapper mapper = new ObjectMapper();
				jsonInString = mapper.writeValueAsString(saw);

				request.setAttribute("jsonInString", jsonInString);
				for (Saw item : saw) {
					jsonInString2 = mapper.writeValueAsString(item);
				}

				jsonInString = mapper.writeValueAsString(saw);

				saw = (List<Saw>) sessionsa.getAttribute("saw");
				i = 0;
				int sawCount = 0;
				System.out.println("Evaluating date now");
				System.out.println(jsonInString);
				for (Saw item : saw) {

					listOfSaws[sawCount][0] = item.getId();
					listOfSaws[sawCount][1] = item.getType();
					listOfSaws[sawCount][2] = item.getModel();
					listOfSaws[sawCount][3] = item.getManufacturer();
					listOfSaws[sawCount][4] = item.getState();
					listOfSaws[sawCount][5] = item.getYear();
					sawCount++;
				}
			}

			sessionsa.setAttribute("listOfSaws", listOfSaws);

			//------------------------------------------------------
		%>


		<!-- Header -->
		<c:import url="utils/header.jsp" />
		<!-- /Header -->


		<aside id="aside-panel"> <form:form method="GET"
			action="client" class="editFields" commandName="sawC">
			<div class="firstLeftHeader">
				<button class="filter-button-up" name="btn" value="">Filter</button>
				<label class="aside-caption">Brand</label>
			</div>
			<%
				i = 0;
					for (String str : brand) {
						if (i < 10) {
							i++;
							filter = "brend";
			%>
			<p>
			<div class="filter-saw">
				<label class="filter-saw-label"><input type="checkbox"
					name="filterBrand" value=<%=str%> onclick="sortAssets2()" /><%=str%></label>
			</div>

			<%
				}
					}
					if (i >= 10) {
			%>
			<select class="test" name="Brand" style="width: 250px">
				<option selected disabled>All brands</option>
				<%
					for (String str : brand) {
				%>
				<option value=<%=str%>><%=str%></option>
				<%
					}
				%>
			</select>
			<%
				}
			%>

			<p>


				<label>All</label>
			</p>


			<div class="firstLeftHeader">
				<label class="aside-caption">Location</label>
			</div>
			<%
				i = 0;
					for (String str : location) {
						if (i < 10) {
							i++;
							filter = "location";
			%>
			<p>
			<div class="filter-saw">
				<label class="filter-saw-label"><input type="checkbox"
					name="filterLocation" value=<%=str%> onclick="sortAssets2()" /><%=str%></label>
			</div>

			<%
				}
					}
					if (i >= 10) {
			%>
			<select class="test" name="Location" style="width: 250px">
				<option selected disabled>All locations</option>
				<%
					for (String str : location) {
				%>
				<option value=<%=str%>><%=str%></option>
				<%
					}
				%>
			</select>
			<%
				}
			%>

			<p>


				<label>All</label>
			</p>
			<label class="aside-caption">Price</label>
				<div>
				<label>minimum price</label>
				<input  name="min_price"  type="number" placeholder="0"></div>
				<div>
				<label>maximum price</label>
				<input  name="max_price"  type="number" placeholder="900000"></div>



			<button class="filter-button-down" name="btn" value="">Filter</button>

		</form:form> </aside>

		<%
			int sch = 0;
			int raws = 0;
			String[] checkboxValues;
		%>
		<form:form method="GET" action="client">

			<table class="list-view-table">
				<tr>
					<td>View:</td>
					<td><a href="client?style=grid"><img class="list-view"
							src="${pageContext.request.contextPath}/resources/assets/img_assets/grid.jpg" />
					</a> <a href="client?style=list"> <img class="list-view"
							src="${pageContext.request.contextPath}/resources/assets/img_assets/list.png" />
					</a></td>
				</tr>
			</table>

		</form:form>


		<form:form method="GET" action="compare" class="editFields">
			<table id="mytab" class="spc" width="900px">
				<caption></caption>

				<%
					if (saw != null) {
							if (style.equals("grid")) {
				%>
				<%
					for (Saw item : saw) {
									Saw toCart = item;
									if (sch == 3) {
										sch = 0;
				%>
				<tr>
					<%
						}
										sch++;
					%>

					<td class="table" ondblclick='sel()'>
						<div class="merchandise">
							<img class="image-out"
								src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage1()%>"
								onerror="this.src='${pageContext.request.contextPath}/resources/assets/img_assets/image-not-found.jpg'" />


							<p>
								<form:form method="GET" action="merchandise" class="editFields"
									commandName="sawC">
									<p>
										<a href="merchandise?btn=<%=item.getId()%>"
											class="catalog-add-button" name="btn"> <%out.println(item.getModel());%>
										</a>
									</p>
								</form:form>
							<div>
								<label class="type"> <%	out.println(item.getType()); %>
								</label>
							</div>
							<div>
								<label class="type">Bench size : <%
									out.println(item.getBenchSize());
								%></label>
							</div>
							<div>
								<label class="type">Weight : <%
									out.println(item.getBenchWeight());
								%></label>
							</div>
							<div>
								<label class="type">Blade power : <%
									out.println(item.getBladePower());
								%></label>
							</div>
							<div>
								<label class="type">Price per one: <%
									out.println(item.getPrice());
								%></label>
							</div>

							<%
								String task = "cart";
												session.setAttribute("task", task);
							%>

							<input type="checkbox" class="chkdata" name="compare"
								value="<%=item.getId()%>" />
						<button id="btn-compare" name="btn" value="compare">Compare</button>
							<a href="addToCart?quantity=1&pg=<%=item.getId()%>"><div
									id="ref-inCart">in cart</div></a>
						</div>
					</td>

					<%
						}
								} else {
									for (Saw item : saw) {
					%>
				</tr>
				<tr>
					<td><img class="image-out-list"
						src="${pageContext.request.contextPath}/resources/assets/img_assets/<%=item.getImage1()%>" />

					</td>


					<td><form:form method="GET" action="merchandise"
							class="editFields" commandName="sawC">
							<p>
								<a href="merchandise?btn=<%=item.getId()%>" name="btn"> <% 	out.println(item.getModel()); %>
								</a>
							</p>
						</form:form> <a> <label class="type-list">Bench size : <% 	out.println(item.getBenchSize()); %></label>
					</a> <a> <label class="type-list">Weight : <% 	out.println(item.getBenchWeight()); %></label>
					</a> <a> <label class="type-list">Blade power : <% 	out.println(item.getBladePower()); %></label>
					</a> <a> <label class="type-list">Price per one: <% 	out.println(item.getPrice()); %></label>
					</a></td>
					<td><input type="checkbox" class="chkdata" name="compare"
						value="<%=item.getId()%>" />
						<button id="btn-compare" name="btn" value="compare">Compare</button>


						<a href="addToCart?quantity=1&pg=<%=item.getId()%>"><div
								id="ref-inCart">in cart</div></a></td>

					<%
						}
								}

							}
					%>
				</tr>
			</table>

		</form:form>

		<input type="hidden" name="smname" id="smname" />
		<form:form method="GET" action="client">
			<%
				String task = "cart";
					for (i = 0; i < page_number; i++) {
			%><button id="btn-listing" name="page" value="<%=i + 1%>"><%=i + 1%></button>
			<%
				}
			%>
		</form:form>
	</div>
	<!-- Ending -->
	<c:import url="utils/ending.jsp" />
	<!-- /Ending -->
</body>
</html>