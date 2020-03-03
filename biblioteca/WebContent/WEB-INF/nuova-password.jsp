<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	text-align: center;
}
</style>
</head>
<body>
	<div>
		<h6>Conferma la nuova password che hai ricevuto</h6>
		<form action='nuova-password.do' method='post'>
			<input type="text" value="email" placeholder="Indirizzo email"></input>
			<br> <input type="password" value="password"
				placeholder="Nuova Password"></input> <br>
			<button type="submit">Conferma</button>
		</form>
	</div>
</body>
</html>