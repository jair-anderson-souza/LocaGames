<%-- 
    Document   : observar
    Created on : 24/02/2016, 10:50:45
    Author     : Dijalma Silva <dijalmacz@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>
        <div class="dj-modal__section">


            <div class="form-group dj-form__input">
                <label for="buscar">Buscar jogo:</label>
                <input class="form-control" id="buscar" name="buscar" autofocus="">
            </div>
            <div class="text-left dj-table">
                <h3>Jogos</h3>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Gênero</th>
                            <th>Receber notificaçao</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.listGames}" var="game">
                            <tr>
                                <td>${game.idGame}</td>
                                <td>${game.name}</td>
                                <td>${game.gender}</td>
                                <td><a>Observar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <br>
            <div class="text-right dj-button__submit">
                <input type="submit" class="btn btn-primary btn-lg" value="Observar" <c:if test="${clientes == null}">disabled=""</c:if>><br><br>
            </div>
            <br>
            <h4><a href="index.jsp">Voltar ao início</a></h4>
        </div>
    </body>
</html>
