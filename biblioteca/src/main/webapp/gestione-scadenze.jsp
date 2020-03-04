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
                                                     
                        <tbody>
                            <c:forEach var="user" items="${listUser}">
                                <tr>
                                    <td>
                                        <c:out value="${user.nome}" />Nome
                                    </td>
                                    <td>
                                        <c:out value="${user.cognome}" />Cognome
                                    </td>
                                    <td>
                                        <c:out value="${user.username}" />Username
                                    </td>
                                    <td>
                                        <c:out value="${user.email}" />email
                                    </td>
                                    <td><button type="button">Dettagli<a href="edit?id=<c:out value='${user.id}' /></button>
                            </td>
                            </c:forEach>
                          </tbody>
                    </table>
<tr>
</table>
</div>
</body>
</html>