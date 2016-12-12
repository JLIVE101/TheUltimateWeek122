<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Move along.. nothing to see here.  Please disperse.  Nothing to see here.</h1>
	
	<c:url value="/saveStudent" var="url" />
	<form:form commandName="student" method="post" action="${url}">
		<form:hidden path="id" />
		Name: <form:input path="name" /><br />
		Instrument: <form:input path="music.instrument" /><br />
		Song Writer: <form:checkbox path="music.songWriter" /><br />
		Genre: <form:select path="music.genre" items="${student.music.genres}" /><br />
		<input type="submit" value="Save Student!" />
	</form:form>
	
</body>
</html>