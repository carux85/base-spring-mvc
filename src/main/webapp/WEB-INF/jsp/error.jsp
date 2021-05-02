<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
</head>
<body>
<h1>Error Page</h1>
  <p>Application has encountered an error. Please contact support on ...</p>
    

    Failed URL: ${url}
    <br/>
    Exception:  ${exception.message}
    <!-- 
        <c:forEach items="${exception.stackTrace}" var="ste">    
        	${ste} 
    	</c:forEach>
    -->
</body>
</html>