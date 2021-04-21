<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
	.error{
		color:red;
		font-weight: bold;
	}
</style>
<body>
		<h3>Contact Us</h3>
		
		<p class="error">${error}</p>
        <form:form method="POST" 
          action="/contact" modelAttribute="contactform">
             <table>
                <tr>
                    <td><form:label path="name">Name *</form:label></td>
                    <td>
                    	<form:input path="name"/>
                    	<form:errors path = "name" cssClass = "error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="surname">Surname *</form:label></td>
                    <td>
                    	<form:input path="surname"/>
                    	<form:errors path = "surname" cssClass = "error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="email">Your email *</form:label></td>
                    <td>
                    	<form:input path="email"/>
                    	<form:errors path = "email" cssClass = "error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="phone">Phone</form:label></td>
                    <td>
                    	<form:input path="phone"/>
                    	<form:errors path = "phone" cssClass = "error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="subject">Subject *</form:label></td>
                    <td>
                    	<form:input path="subject"/>
                    	<form:errors path = "subject" cssClass = "error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="message">Message(max 255) *</form:label></td>
                    <td>	
                    	<form:textarea path="message" style="width:500px; height:150px"/>
                    	<form:errors path = "message" cssClass = "error" />
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
</body>
</html>