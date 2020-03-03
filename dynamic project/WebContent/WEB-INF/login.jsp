<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
body {
	text-align: center;
}
</style>
</head>
<body>
	<div>
		<h6>Login</h6>
		<form action='login.do' method='post'>
			<input type='text' value='username' placeholder='Username'>
			<br> <input type='password' value='password' placeholder='Password'> 
			<br>
			<button type='submit' value='Login'></button>
		</form>
		<a href=''>Password dimenticata</a>
	</div>
</body>
</html>