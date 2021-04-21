<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Injection Example</title>
</head>
<body>

	<h3>@Resourse</h3>
	<div>resourseByType:&nbsp;${resourseByType.name}</div>
	<div>resourseByName1:&nbsp;${resourseByName1.name}</div>
	<div>resourseByName2:&nbsp;${resourseByName2.name}</div>
	<br/>
	<h3>@Inject</h3>
	<div>injectByType:&nbsp;${injectByType.name}</div>
	<div>injectByName1:&nbsp;${injectByName1.name}</div>
	<div>injectByName2:&nbsp;${injectByName2.name}</div>
	<br/>
	<h3>@Autowired</h3>
	<div>autowiredByType:&nbsp;${autowiredByType.name}</div>
	<div>autowiredByName1:&nbsp;${autowiredByName1.name}</div>
	<div>autowiredByName2:&nbsp;${autowiredByName2.name}</div>
	
</body>
</html>