<%-- 
    Document   : index
    Created on : Mar 2, 2016, 5:45:33 PM
    Author     : jairanderson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="head.jsp" %>
    </head>
    <body>
        
        <%@include file="header.jsp" %>
        
        <!--<button data-toggle="modal" data-target="#modal" class="btn btn-info btn-sm">Identifique o cliente</button>-->
        
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <c:if test="${sessionScope.error != null}">
            <h2>${sessionScope.error}</h2>
        </c:if>
            <form action="front?command=loginUser" method="post" class="form-group">
                    <div class="form-group dj-form__input">
                        <label for="cpf">CPF:</label>
                        <input class="dj-button form-control" id="cpf" name="cpf">
                    </div>
                    <br>
                    <div class="form-group dj-form__input">
                        <label for="email">Email:</label>
                        <input class="dj-button form-control" id="email" name="email">
                    </div>
                    <br><br>
                    <div class="text-center dj-button__submit">
                        <input type="submit" class="btn btn-primary btn-lg" value="Login">
                    </div>
                </form>
        
        
    </body>
</html>
