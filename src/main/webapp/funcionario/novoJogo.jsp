<%-- 
    Document   : novoJogo
    Created on : 24/02/2016, 11:10:52
    Author     : Dijalma Silva <dijalmacz@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>
        <div class="dj-modal__section">
            <h2 class="dj-titulo__left">Novo jogo</h2>
            <br>
            <form action="addJogo" method="post" class="form-group">
                <div class="dj-form__input">
                    <label for="nome">Nome:</label>
                    <input class="form-control" name="nome" id="nome" autofocus="">
                </div><br>
                <div class="dj-form__input">
                    <label for="genero">Gênero:</label>
                    <input class="form-control" name="genero" id="genero">
                </div>
                <div class="text-right dj-button__submit">
                    <input type="submit" class="btn btn-primary btn-lg" value="Cadastrar">
                </div>
            </form>
            <br>
            <h4><a href="index.jsp">Voltar ao início</a></h4>
        </div>
    </body>
</html>
