<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione Utente</title>
</head>
<body>
<!--  Pagina web per l'inserimento dell'utente
		campi gestiti:	nome, cognome, email, via, civico, citt�, provincia, cap, telefono, ruolo
		
		Il campo ruolo visualizza la selezione iscritto per gli utenti che possono accedere alla pagina
		mentre visualizza le selezioni gestore e amministratore solamente per gli utenti amministratore
		
		Il pulsante aggiungi inserisce l'utente mentre il pulsante annulla ripulisce i campi della pagina
-->
	<h1>Registrazione Utente</h1>
	<br>
	<div style="display: flex;">
		<form action="registrazione.do" method="post">
			<label for="name">Nome: </label> 
			<input type="text" id="name" name="nome" value="nome">
			<br>
			<label for="cognome">Cognome: </label>
			<input type="text" id="cognome" name="cognome" value="cognome">
			<br>
			<label for="email">Email: </label> 
			<input type="text" id="email" name="email" value="email">
			<br>
			<label for="via">Via: </label> 
			<input type="text" id="via" name="via" value="via">
			<label for="civico">Civico: </label> 
			<input type="text" id="civico" name="civico" value="civico">
			<br>
			<label for="citta">Citt�: </label> 
			<input type="text" id="citta" name="citta" value="citt�" size="5">
			<label for="provincia">Provincia: </label> 
			<input type="text" id="provincia" name="provincia" value="provincia" size="3">
			<br>
			<label for="cap">CAP: </label> 
			<input type="text" id="cap" name="cap" value="cap" size="5">
			<br>
			<label for="telefono">Telefono: </label> 
			<input type="text" id="telefono" name="telefono" value="telefono">
			<input type="submit"  value="submit">
			
			<br>
			<label for="ruolo">Ruolo: </label> 
			<input type="radio" id="ruolo" name="iscritto" value="iscritto">Iscritto
			<input type="radio" id="ruolo" name="gestore" value="gestore">Gestore
			<input type="radio" id="ruolo" name="amministratore" value="amministratore">Amministratore
			<br>
			<br>
			<button name="aggiungi" onclick="creaUtente(Utente utente)">Aggiungi</button>
			&nbsp;
			<button type="reset" name="annulla">Annulla</button>
		</form>
	</div>
	<br>
</body>
</html>