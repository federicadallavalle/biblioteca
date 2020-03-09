<%@ page import="model.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="listaLibri" scope="session" class="model.ListaLibri" />

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
		<form action='lista-libri-gestore.do' method='post'>
			<input type="text" name="cerca" placeholder="Cerca...">
			&nbsp;&nbsp;&nbsp; <input type='submit' value='Invio'>
		</form>

		<hr>
		<table>
			<tr>
				<th>Titolo</th>
				<th>Autore</th>
				<th>Editore</th>
				<th>ISBN</th>
				<th>Quantità</th>
				<th>Libreria</th>
				<th>Scaffale</th>
				<th></th>
				<th></th>
			</tr>

			<%
				for (Libro l : listaLibri.getLista()) {
					out.println("<tr>");
					out.println("<td>" + l.getTitolo() + "</td>");
					out.println("<td>" + l.getAutore() + "</td>");
					out.println("<td>" + l.getEditore() + "</td>");
					out.println("<td>" + l.getIsbn() + "</td>");
					out.println("<td>" + l.getQta() + "</td>");
					out.println("<td>" + l.getLibreria() + "</td>");
					out.println("<td>" + l.getScaffale() + "</td>");
					out.println("<td>" + "<form action='modifica-libro.do' method='post'>");
					out.println("<input type='hidden' name='id' value='" + l.getId() + "'>");
					out.println("<input type='submit' value='Modifica')'>");
					out.println("</form>" + "</td>");
					out.println("<td>" + "<form action='elimina-libro.do' method='post'>");
					out.println("<input type='hidden' name='id' value='" + l.getId() + "'>");
					out.println("<input type='submit' value='Elimina')'>");
					out.println("</form>" + "</td>");
					out.println("</tr>");

				}
			%>


		</table>
	</div>
</body>

</html>