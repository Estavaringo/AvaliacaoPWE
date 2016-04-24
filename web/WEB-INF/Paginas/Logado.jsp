<%-- 
    Document   : Logado
    Created on : 06/03/2016, 17:05:16
    Author     : gabri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gerenciador de Empresas!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="css/bootstrap-theme.min.css" >
        <!-- Latest compiled and minified JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-4 col-md-offset-4 ">
                <br>
                <h2>Logado como ${usuarioLogado.email} </h2>
                <br><br>
                <button type="button" onclick="window.location.href='http://localhost:8084/AvaliacaoPWE/'" class="btn btn-default">Voltar</a></button>

            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </body>
</html>
