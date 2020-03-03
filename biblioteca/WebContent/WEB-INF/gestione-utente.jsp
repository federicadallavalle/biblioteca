<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Gestione Utente</h1>
   <br>
   <div style="display: flex;">
   <form action="/cerca-utente">
  <label for="name">Nome:</label>
  <input type="text" id="name" name="nome" value="mario"><br>
   <label for="lname">Cognome</label>
  <input type="text" id="fname" name="cognome" value="rossi"><br>
   <label for="email">Email:</label>
  <input type="text" id="email" name="email" value="esempio@txt.it"><br>
  <label for="username">Usernsame:</label>
  <input type="text" id="username" name="username" value="000000"><br><br>
  <input type="submit" value="Cerca">
</form> 
</div>
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
<tr>
<td>
mario
</td>
<td>
rossi
</td>
<td>
esempio@txt.it
</td>
<td>
0000000
</td>
<td>
<button value="Cerca">modifica</button> 
<button value="Cerca">modifica</button> 
</td>
</tr>
</table>
</div>
</body>
</html>