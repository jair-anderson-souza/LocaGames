<%-- 
    Document   : index
    Created on : 23-Feb-2016, 17:52:14
    Author     : Anderson Souza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Pagina Inicial</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    </head>
    <body>

        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <!--<img src="img/Firefox_wallpaper.png" class="img-rounded" style="width: 1200px;height: 100px;"/>-->
                </div>
            </div>
        </div>
        <h5>${sessionScope.error}</h5>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <form role="form" action="front?command=registerUser" method="post">
                        <div class="form-group">
                            <label for="name">Nome</label>
                            <input type="text" name="name" class="form-control" id="name">
                        </div>
                        <div class="form-group">
                            <label for="cpf">CPF</label>
                            <input type="text" name="cpf" class="form-control" id="cpf">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" class="form-control" id="email">
                        </div>
                        <button type="submit" class="btn btn-success">Cadastrar Usuario</button>
                    </form>
                </div>

                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
