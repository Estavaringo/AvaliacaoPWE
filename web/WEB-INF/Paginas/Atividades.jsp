
<%@page import="java.util.Collection"%>
<%@page import="ifsp.pwe.prova.beans.Atividade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <table class="table">
                    <tr>
                        <td>Título da Atividade</td>
                        <td>Nome do Aluno</td>
                    </tr>
                    <c:forEach var="atividade" items="${buscaAtividade}">
                        <tr><td> ${atividade.titulo}</td>
                            <td> ${atividade.idUsuario} </td>
                        </tr>
                    </c:forEach>
                </table>

                </table>
                <br><br>
                <button type="button" onclick="window.location.href = 'http://localhost:8084/AvaliacaoPWE/'" class="btn btn-default">Voltar</a></button>

            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>        

    </body>
</html>
