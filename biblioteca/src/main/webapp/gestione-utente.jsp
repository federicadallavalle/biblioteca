<%@page import="model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="listaUtenti" scope="session" class="model.ListaUtente" />
    <%
    Utente utente = new Utente("nome","cognome","emayyyil","usernaame","ruolo");
    listaUtenti.getLista().add(utente);
    Utente utente2 = new Utente("nome2","cognome2","emayyysil2","usernaame2","ruolo2");
    listaUtenti.getLista().add(utente2);
%>
<!DOCTYPE html>
<!--TODO: stub -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Gestione Utente</h1>
   <br>
   <div style="display: flex;">
   <form action="cerca-utenti.do" method="post">
  <label for="name">Nome:</label>
  <input type="text" id="name" name="nome" value=""><br>
   <label for="lname">Cognome:</label>
  <input type="text" id="fname" name="cognome" value=""><br>
   <label for="email">Email:</label>
  <input type="text" id="email" name="email" value=""><br>
  <label for="username">Usernsame:</label>
  <input type="text" id="username" name="username" value=""><br><br>
  <input type="submit" value="Cerca">
</form> 
</div>
<br>
   <a href="registrazione-utente.jsp" > Iscrivi nuovo utente </a>
    <br>
    <br>
<div>
<table border="1">
<tr>
<th>Nome</th>
<th>Cognome</th>
<th>Email</th>
<th>Username</th>
<th>Azione</th>
</tr>
<%
	for(Utente u : listaUtenti.getLista()){	
%>
<tr>
<td>
<%
	out.print(u.getNome());
%>
</td>
<td>
<%
	out.print(u.getCognome());
%>
</td>
<td>
<%
	out.print(u.getEmail());
%>
</td>
<td>
<%
	out.print(u.getUsername());
%>
</td>
<td>
<button value="Cerca">modifica</button> 
<button value="Cerca">modifica</button> 
</td>
</tr>
<%
	}
%>
</table>
</div>
</body>
</html>