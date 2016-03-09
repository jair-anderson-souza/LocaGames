<%-- 
    Document   : alugar
    Created on : 23/02/2016, 22:13:37
    Author     : Dijalma Silva <dijalmacz@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>

            $(function () {
                $(".busca").keyup(function () {
                    //pega o css da tabela 
                    var tabela = $(this).attr('alt');
                    if ($(this).val() !== "") {
                        $("." + tabela + " tbody > tr").hide();
                        $("." + tabela + " td:contains-ci('" + $(this).val() + "')").parent("tr").show();
                    } else {
                        $("." + tabela + " tbody>tr").show();
                    }
                });
            });
//            $.extend($.expr[":"], {
//                "contains-ci": function (elem, i, match, array) {
//                    return (elem.textContent || elem.innerText || $(elem).text() || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
//                }
//            });
        </script>
    </head>

    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>
        <div class="dj-modal__section">
            <c:if test="${sessionScope.user == null}">
                <button data-toggle="modal" data-target="#modal" class="btn btn-info btn-sm">Identifique o cliente</button>
                <div class="text-right">

                </div>
                <%--<%@include file="../modalCliente.jsp" %>--%> 
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
                                        <td><a href="front?action=locationGame&idGame=${game.idGame}">Alugar</a></td>
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
            <h4><a href="home.jsp">Voltar ao início</a></h4>
        </div>
    </body>
</html>
