<%-- 
    Document   : novoCliente
    Created on : 23/02/2016, 21:53:04
    Author     : Dijalma Silva <dijalmacz@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>
        <div class="dj-modal__section">
            <h2 class="dj-titulo__left">Novo cliente</h2>
            <br>
            <form action="addCliente" method="post" class="form-group">
                
                <div class="form-group dj-form__input">
                    <label for="nome">Nome:</label>
                    <input class="dj-button form-control" id="nome" name="nome" autofocus="">
                </div>
                <br>
                <div class="form-group dj-form__input">
                    <label for="cpf">CPF:</label>
                    <input class="dj-button form-control" id="cpf" name="cpf">
                </div>
                <br>
                <div class="form-group dj-form__input">
                    <label for="email">Email:</label>
                    <input class="dj-button form-control" id="email" name="email">
                </div>
                <br>
                <div class="text-right dj-button__submit">
                    <input type="submit" class="btn btn-primary btn-lg" value="Cadastrar"><br><br>
                </div>
            </form>
            <br>
            <h4><a href="index.jsp">Voltar ao início</a></h4>
        </div>
    </body>
</html>
