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
                                        <c:out value="${user.id}" />Nome
                                    </td>
                                    <td>
                                        <c:out value="${user.name}" />Cognome
                                    </td>
                                    <td>
                                        <c:out value="${user.email}" />Username
                                    </td>
                                    <td>
                                        <c:out value="${user.country}" />email
                                    </td>
                                    <td><button type="button">Dettagli<a href="edit?id=<c:out value='${user.id}' /></button>
                            </c:forEach>
                          </tbody>
                    </table>
<tr>

</tr>
<tr></tr>
</table>
</div>
</body>
</html>