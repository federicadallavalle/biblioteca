<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        

        <body>
	  </div>
		<ul class="navbar-nav">
 <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
         </ul>
           </header>
            <br>
            <div class="row">
                    <div class="container">
                    <h3 class="text-center">Dettagli Prestiti</h3>
                    <hr>
                    <div class="container text-left">

                        <button type="button"><a href="<%=request.getContextPath()%>/new" class="btn btn-success">In Corso</a>
                       <button type="button"> <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Storico</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ISBN</th>
                                <th>Titolo</th>
                                <th>Autore</th>
                                <th>Editore</th>
                                <th>Data prestito</th>
                                <th>Data Consegna</th>
                                <th>Data Ultimo Sollecito</th>
                            </tr>
                        </thead>
                        <tbody>
           <c:forEach var="user" items="${listUser}">
                     <tr>
                            <td>
                            <c:out value="${ISBN}" />
                            </td>
                            <td>
                            <c:out value="${TITOLO}" />
                             </td>
                           <td>
                              <c:out value="${AUTORE}" />
                               </td>
                              <td>
                            <c:out value="${EDITORE}" />
                           </td>
                            <td>
                           <c:out value="${DATA PRESTITO}" />
                            </td>
                           <td>
                          <c:out value="${DATA ULTIMO SOLLECITO}" />
                         </td>
                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </body>
        </html>
