<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Utente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Privata</title>
</head>
<body>
<%
	//TODO : Stub
	request.getSession().setAttribute("ruolo", "amministratore");
	if (((String)request.getSession().getAttribute("ruolo")).equals("amministratore")) {
%>

	<h1>Amministratore</h1>
	<div>
		<a href="gestione-utente.jsp"><button>Gestione delle
				iscrizioni degli utenti</button></a><br> <br> <a
			href="lista-libri-gestore.jsp"><button>Gestione del
				collocamento dei libri nella biblioteca</button></a><br> <br>
		<button>Gestione dei prestiti ad un utente iscritto</button>
		<br> <br> <a href="gestione-scadenze.jsp"><button>Gestione
				delle scadenze</button></a><br> <br>
		<button>Gestione dei profili utenti(addetti-utenti)</button>
	</div>
<%
	} else {
%>

	<div>
		<h1>Gestore</h1>
		<a href="gestione-utente.jsp"><button>Gestione delle
				iscrizioni degli utenti</button></a><br> <br> <a
			href="lista-libri-gestore.jsp"><button>Gestione del
				collocamento dei libri nella biblioteca</button></a><br> <br>
		<button>Gestione dei prestiti ad un utente iscritto</button>
		<br> <br> <a href="gestione-scadenze.jsp"><button>Gestione
				delle scadenze</button></a><br> <br>

	</div>

<%
	}
%>
</body>
</html>