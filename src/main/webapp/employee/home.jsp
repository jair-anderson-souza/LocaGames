<%-- 
    Document   : home
    Created on : 20-Feb-2016, 20:18:10
    Author     : Anderson Souza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head lang="pt-br">
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1">

    </head>
    <body>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Locadora de Games</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Game</a></li>
                    <li><a href="#">Alugar</a></li>
                    <li><a href="#">Devolver</a></li>
                    <li><a href="#">Observar</a></li>
                    <li><a href="#"></a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <c:forEach items="${sessionScope.listGames}" var="game">
                    <div class="col-sm-1 col-md-4">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>${game.name}</h3>
                                <p>${game.gender}</p>
                                <p><a href="../front?command=render&idGame=${game.id}" class="btn btn-success" role="button">Alugar</a> <a href="../front?command=return" class="btn btn-success">Devolver</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div> 
    </div>
</body>
</html>
