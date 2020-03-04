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
		<h2>Password Dimenticata</h2>
		<br> Inserisci l'email nel campo sottostante. 
		<br> Riceverai un link con la nuova password che dovrai inserire nel prossimo step.
		<form action='password-dimenticata.do' method='post'>
			<input type="text" name="email" placeholder="Indirizzo email"></input>
			<br>
			<input type="submit" value="Invia">
		</form>
		</div>
		
		<%
		String msg = "";
		System.out.println("Ti abbiamo inviato una mail con la nuova password");
		%>
		
</body>
</html>