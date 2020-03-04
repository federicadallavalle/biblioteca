<%@page import="model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="listaUtenti" scope="request" class="model.ListaUtente" />
<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
<title>Gestione Scadenze</title>
</head>
<body>
	<h1>Gestione Scadenze</h1>
	<div>
		<table>
			<tr>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Username</th>
				<th>Email</th>
			</tr>
			<%
				for (Utente user : listaUtenti.getLista()) {
			%>
			<tr>
				<td>
					<%
						out.print(user.getNome());
					%>
				</td>
				<td>
					<%
						out.print(user.getCognome());
					%>
				</td>
				<td>
					<%
						out.print(user.getUsername());
					%>
				</td>
				<td>
					<%
						out.print(user.getEmail());
					%>
				</td>
				<td><a href='dettagli-prestiti?id=<%out.print(user.getId());%>'><button>Dettagli</button></a>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>