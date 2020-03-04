
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<c:forEach var="user" items="${listaUtenti}">
				<tr>
					<td><c:out value="${user.nome}" /></td>
					<td><c:out value="${user.cognome}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td>
						<button type="button">
							Dettagli <a
								href="dettagli-prestiti?id=<c:out value='${user.id}' />"> </a>
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>