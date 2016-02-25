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
            <c:choose>
                <c:when test="${games != null}">
                    <div class="dj-titulo__left">
                        <h2>Atenção!</h2>
                    </div>
                    <br>
                    <h4>Ainda não há jogos cadastrados. Clique <a href="#">aqui</a> para
                        cadastrar!</h4>
                    </c:when>
                    <c:otherwise>
                    <h2 class="dj-titulo__left">Observar jogo</h2>
                    <br>
                    <c:choose>
                        <c:when test="${clientes != null}">
                            <h4>Ainda não há clientes cadastrados. Clique <a href="novoCliente.jsp">aqui</a> para
                                cadastrar!</h4>
                            <br>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group dj-form__input">
                                <label for="buscar">Buscar clinte:</label>
                                <input class="form-control" id="buscar" name="buscar" autofocus="">
                                <div class="text-right">
                                    <button data-toggle="modal" data-target="#modal" class="btn btn-default btn-sm">Novo Cliente</button>
                                </div>
                                <%@include file="modalCliente.jsp" %>
                            </div>
                            <div class="text-left dj-table">
                                <h3>Clientes</h3>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Nome</th>
                                            <th>Email</th>
                                            <th>CPF</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${clientes}" var="cliente">
                                            <tr>
                                                <td>${cliente.nome}</td>
                                                <td>${cliente.email}</td>
                                                <td>${cliente.cpf}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <br><br>
                        </c:otherwise>
                    </c:choose>
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
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${games}" var="game">
                                    <tr>
                                        <td>${game.id}</td>
                                        <td>${game.nome}</td>
                                        <td>${game.genero}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <div class="text-right dj-button__submit">
                        <input type="submit" class="btn btn-primary btn-lg" value="Observar" <c:if test="${clientes == null}">disabled=""</c:if>><br><br>
                        </div>
                </c:otherwise>
            </c:choose>
            <br>
            <h4><a href="index.jsp">Voltar ao início</a></h4>
        </div>
    </body>
</html>
