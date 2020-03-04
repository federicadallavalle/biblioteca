<%@page import="model.Libro"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <jsp:useBean id="libri" scope="session" class="ArrayList<Libro>" /> --%>
<%
	ArrayList<Libro> libri = new ArrayList<>();
	// TODO : stub
	Libro libro = new Libro();
	libro.setId(1l);
	libro.setTitolo("TitoloTest1");
	libro.setQta(12);
	libri.add(libro);
	libro = new Libro();
	libro.setId(2l);
	libro.setTitolo("TitoloTest2");
	libro.setQta(24);
	libri.add(libro);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME-PUBBLICA</title>
</head>
<h1>HOME</h1>
<body>
	<table>
		<%
			for (Libro l : libri) {
		%>
		<tr>
			<td>
				<%
					out.print(l.getTitolo());
				%>
			</td>
			<td>
				<%
					out.print(l.getQta());
				%>
			</td>
			<td><a
				href='dettagli-libro.do?idLibro=<%out.print(l.getId());%>'><button>Dettagli</button></a></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>