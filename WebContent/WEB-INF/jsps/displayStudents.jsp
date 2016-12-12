<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<c:url value="/scripts/script.js" var="scriptUrl" />
<script src="${scriptUrl}"></script>
</head>
<body>
	<h1>Move along.. nothing to see here. Please disperse. Nothing to
		see here.</h1>

	<c:url value="/getStudent/" var="JSONUrl" />
	<div id="studentList">
		<c:if test="${not empty studentList}">
			<c:forEach var="student" items="${studentList}">
				<h3>
					<a href="#" id="a${student.id}"
						onclick="getStudent('${JSONUrl}', ${student.id})">${student.name}</a>
					<div id="student${student.id}"></div>
					<br />
					<c:url value="/editStudent/${student.id}" var="editUrl" />
					<a href="${editUrl}">Edit ${student.name }</a>
					<br />
					<c:url value="/deleteStudent/${student.id}" var="deleteUrl" />
					<a href="${deleteUrl}">Delete ${student.name }</a>
				</h3>
			</c:forEach>
		</c:if>
	</div>


	<c:url value="/addStudent" var="addUrl" />
	<a href="${addUrl}">Add a Student</a>

</body>
<c:url value="/getAllStudents/" var="getStudentUrl" />
<script>	
	var intervalTime = 5000; //1 second
	
	$(function(){
	
		console.log("im going to make a get request to grab all students");
		
		//set an interval for 20 seconds
		setInterval(function(){
			
			$.get("/spring/getAllStudents", function(data){
				
				//so now that i know i got all the data and its stored in data.students
				//we have to loop through all the students and update the page.
				//a lot of this is going to be copy and paste.
				
				//clear the Student list on the page
				
				//loop through students
				for (var i = 0; i < data.students.length; i++) {
					console.log(data.students[i].id);
					
					//if theres no student with this id add it to page else do nothing
					if($("#a" + data.students[i].id).length == 0) {
					
						$("#studentList").append(addStudentToPage( data.students[i] ));
					} else {			
						console.log("id thats already there is: " + data.students[i].id);
						//do nothing
					}
				}
				
				
				
			})
			
		}, intervalTime)
		
		$("#studentList").delegate(".clickableStudent", "click", function(){
			
			
			var path = "/getStudent/";
			var id = $(this).attr('id').replace("a","");
			console.log("this is the id of the clicked student" + id);
			getStudent(path, id);
			
		
		});
		
		
		function addStudentToPage(student) {
			
			
			var template = 
				"<h3> " +
				" <a href='#' class='clickableStudent' id='a"+student.id+"' >"+student.name+"</a>"+
				"<a href='/deleteStudent/"+student.id+"'>Delete"+student.name+" </a>" +
				"<br />" +
				"<a href='/editStudent/"+student.id+"'>Edit"+student.name+" </a>" +
				"</h3>" +
				"<div id='student"+student.id+"'></div>";
				
			return template;
		}
		
		
	});
	
	

	
	
	
	
</script>
</html>