<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Booking
	
	<label>No of Bookings for the day</label>	
	
	No.of Rooms available
	<br/>
	<input type="number" id="rooms" name="rooms"/>
	
	
	<div>
		<c:forEach items="${bookings}" var="booking">
            <div>${booking.date}</div>
            <a href="/books/${booking.id}">${booking.roomnum}</a>
            <div>${booking.status}</div>
            <br>
        </c:forEach>
	</div>
	
	
</body>
</html>