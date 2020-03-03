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
		<h6>Password Dimenticata</h6>
		<br> Inserisci l'email nel campo sottostante. 
		<br> Riceverai un link con la nuova password che dovrai inserire nel prossimo step.
		<form action='password-dimenticata.do' method='post'>
			<input type="text" value="email" placeholder="Indirizzo email"></input>
			<br>
			<button type="submit">Invia</button>
		</form>
		</div>div>
</body>
</html>