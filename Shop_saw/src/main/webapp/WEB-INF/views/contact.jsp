<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/contact.css">
<title>Contacts</title>
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.myproject.entity.product.*" %>

		<!-- Header -->
		<c:import url="utils/header.jsp" />
		<!-- /Header -->
<main id="contact-us">
    <div>
        <div>
            
            <div>
                <section>
                
                    <h2 >Leave a Message</h2>

 			<form:form id="message" commandName="contact" action="message_send" method="POST">
                    <div class="contact-div">
                    	
                    	<form:label class="contact-label" path="name">Your Name*</form:label>
                    	<form:input  path="name"/> 
                    </div>
                    <div class="contact-div">
                    	<form:label class="contact-label" path="email">Your Email*</form:label>
						<form:input  path="email"/>
                    	
                    </div>	
             			                    
                    <div class="contact-div">
                    	<form:label class="contact-label" path="subject">Subject</form:label>
                        <form:input id="contact-input2" path="subject"/> 
                    </div>
                    <div class="contact-div">
                    	 <form:label class="contact-label" path="message">Your Message</form:label>
            			 <form:textarea path="message" rows="8"></form:textarea>   
                    </div>
                    <div class="contact-div">
                    	<button type="submit">Send Message</button>
                    </div>
                        
    		</form:form> 

                </section><!-- /.leave-a-message -->
            </div><!-- /.col -->

            <div>
                <p>Please email us: <a href="mailto:lysko_9191@mail.ru">lysko_9191@mail.ru</a></p>    
            </div><!-- /.col -->

        </div><!-- /.row -->
    </div><!-- /.container -->
</main>
		<!-- Ending -->
		<c:import url="utils/ending.jsp" />
		<!-- /Ending -->
</body>
</html>