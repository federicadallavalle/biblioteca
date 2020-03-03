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
		<h2>Login</h2>
		<form action='login.do' method='post'>
			<input type='text' name='username' placeholder='Username'> <br>
			<input type='password' name='password' placeholder='Password'>
			<br> <input type='submit' value='Login'>
		</form>
		<!-- TODO aggiungere visualizzazione messaggio errore -->
		<a href='password-dimenticata.jsp'>Password dimenticata</a>
	</div>
</body>
</html>