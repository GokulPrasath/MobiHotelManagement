<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
	<label for="uname"><b>User Name</b></label>
	    <input type="text" placeholder="Enter name" name="uname" id="uname" required> 
	    <br>
	    <label for="pass"><b>Password</b></label>
	    <input type="password" placeholder="Enter Password" name="pass" id="pass" required>
	    <br>
	    <input type="submit" value="login" />
</form>	    
</body>
</html>