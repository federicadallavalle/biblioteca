<%@ page import="model.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Libri del Gestore</title>
<style>
body {
	text-align: center;
}
</style>
</head>

<body>
	<h2>Lista Libri del Gestore</h2>
	<div>
		<form action='' method='post'>
			<input type="text" name="Cerca" placeholder="Cerca..."></input>
			&nbsp;&nbsp;&nbsp; <input type='submit' value='Invio'>
			<hr>
			<table>
				<tr>
					<th>Titolo</th>
					<th>Autore</th>
					<th>Editore</th>
					<th>ISBN</th>
					<th>Quantità</th>
					<th>Corsia</th>
					<th>Libreria</th>
					<th>Scaffale</th>
					<th>Conferma</th>
					<th>Annulla</th>
				</tr>
				
				<% for(Libro l : listaLibri) %>
				<tr>
				
				</tr>
				<% %>
				
				
			</table>
		</form>
	</div>
</body>

</html>