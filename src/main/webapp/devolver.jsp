<%-- 
    Document   : devolver
    Created on : 23/02/2016, 23:26:47
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
            <c:if test="${sessionScope.user == null}">
                <button data-toggle="modal" data-target="#modal" class="btn btn-info btn-sm">Identifique o cliente</button>
                <div class="text-right">
                    
                </div>
                <%@include file="modalCliente.jsp" %>
            </c:if>

            <br><br>
            <div class="form-group dj-form__input">
                <label for="buscar">Buscar jogo:</label>
                <input class="form-control" id="buscar" alt="table" name="buscar" autofocus="">
            </div>
            <div class="text-left dj-table">
                <h3>Jogos</h3>
                <table class="table table-responsive">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Gênero</th>
                            <th>Alugar</th>
                        </tr>
                    </thead>
                    <tbody id="table">
                        <c:forEach items="${sessionScope.listGames}" var="game">
                            <tr>
                                <td>${game.idGame}</td>
                                <td>${game.name}</td>
                                <td>${game.gender}</td>
                                <c:choose>
                                    <c:when test="${sessionScope.user != null}">
                                        <td><a href="front?command=devolutionGame&idGame=${game.idGame}">Devolver</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Operaçao nao permitida</td>
                                    </c:otherwise>

                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <br>
            <form action="addAluguel" method="post" class="form-group">

            </form>

            <br>
            <h4><a href="index.jsp">Voltar ao início</a></h4>
        </div>
    </body>
</html>

    

    <%--<%@include file="head.jsp" %>--%>
    <!--<body>-->
        <%--<%@include file="header.jsp" %>--%>
        <!--<div class="dj-modal__section">-->
            <%--<c:if test="${sessionScope.user == null}">--%>
<!--                <button data-toggle="modal" data-target="#modal" class="btn btn-info btn-sm">Novo Cliente</button>
                <div class="text-right">

                </div>-->
                <%--<%@include file="modalCliente.jsp" %>--%>
            <%--</c:if>--%>
<!--           <div class="form-group dj-form__input">
                <label for="buscar">Buscar jogo:</label>
                <input class="form-control" id="buscar" name="buscar" autofocus="">
            </div>-->
<!--            <br><br>
            <div class="text-left dj-table">
                <h3>Jogos</h3>
                <table class="table table-hover">
                    <thead>-->
<!--                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Gênero</th>
                            <th>Devolver</th>-->
<!--                        </tr>
                    </thead>-->
                    <!--<tbody>-->
                        <%--<c:forEach items="${sessionScope.listGames}" var="game">--%>
                            <!--<tr>-->
<!--                                <td>${game.idGame}</td>
                                <td>${game.name}</td>
                                <td>${game.gender}</td>-->
                                <%--<c:choose>--%>
                                    <%--<c:when test="${sessionScope.user != null}">--%>
                                        <!--<td><a href="front?command=availableGame&idGame=${game.id}">Devolver</a></td>-->
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <!--<td>Operaçao nao permitida</td>-->
                                    <%--</c:otherwise>--%>

                                <%--</c:choose>--%>
                            <!--</tr>-->
                        <%--</c:forEach>--%>
<!--                    </tbody>
                </table>
            </div>
            <br>
            <div class="text-right dj-button__submit">
                <input type="submit" class="btn btn-primary btn-lg" value="Devolver">
            </div>-->
        
    <!--<br>-->
    <!--<h4><a href="index.jsp">Voltar ao início</a></h4>-->
<!--</div>-->
</body>
</html>
