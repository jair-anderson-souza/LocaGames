<%-- 
    Document   : index
    Created on : 23/02/2016, 19:47:54
    Author     : Dijalma Silva <dijalmacz@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <%@include file="modalCliente.jsp" %>
    <body>
        <%@include file="header.jsp" %>

        <div class="dj-modal__section">
            <div class="dj-titulo__left">
                <c:choose>
                    <c:when test="${sessionScope.success != null}">
                        <div class="alert alert-success">
                            <strong>Sucesso</strong> ${sessionScope.success}
                        </div>
                        <c:remove var="success" scope="session" />
                    </c:when>
                    <c:when test="${sessionScope.info != null}">
                        <div class="alert alert-danger">
                            <strong>Falha</strong> ${sessionScope.info}
                            ${sessionScope.info}
                        </div>
                        <c:remove scope="session" var="info"></c:remove>
                    </c:when>
                    <c:when test="${sessionScope.error != null}">
                        <div class="alert alert-danger">
                            <strong>Falha</strong> ${sessionScope.error}
                        </div>
                        <c:remove scope="session" var="error"></c:remove>
                    </c:when>
                </c:choose>
            </div>
            <c:if test="${sessionScope.user == null}">
                <button data-toggle="modal" data-target="#modal" class="btn btn-info btn-sm">Identifique o cliente</button>
                <div class="text-right">

                </div>
                <%@include file="modalCliente.jsp" %>
            </c:if>

            <br>

            <div class="dj-titulo__left">
                <h2>Funcionalidades do sistema</h2>
            </div>
            <div>
                <div class="modal-header">
                    <h3>Jogos</h3>
                </div>
                <br>
                <ul>
                    <li>
                        <img src="icons/rent.png" class="dj-image__funcionalidade">
                        <a href="funcionario/alugar.jsp">Alugar</a>
                    </li>
                    <li>
                        <img src="icons/return.png" class="dj-image__funcionalidade">
                        <a href="funcionario/devolver.jsp">Devolver</a> 
                    </li>
                </ul>
            </div>
            <br><br>
            <div>
                <h3>Clientes</h3>
                <br>
                <ul>
                    <li>
                        <img src="icons/rent.png" class="dj-image__funcionalidade">
                        <a href="novoCliente.jsp">Novo Cliente</a>
                    </li>
                </ul>
            </div>

        </div>
        <c:remove scope="session" var="success"></c:remove>
    </body>
</html>
