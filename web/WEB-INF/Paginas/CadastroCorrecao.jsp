
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
   
        <div class="row">
            <div class="col-md-4 col-md-offset-4 ">
                <br>
                <h2>Cadastro de Correcao</h2>
                <br><br>


                <form method="POST" action="Executa">
                    
                    <div class="form-group">
                        <label>Correção:</label> 
                        <input type="text" class="form-control" placeholder="Digite aqui o comentário sobre a atividade" name="comentario" value="" />
                    </div>
                    <input type="hidden" name="tarefa" value="NovaCorrecao">
                    <input type="hidden" name="atividade" value=${atividade.id}>
                    <button type="submit" class="btn btn-default btn-xs" value="Incluir">Incluir Correcao</button>
                </form>
            </div>
        </div>

            <br><br>
            <button type="button" onclick="window.location.href = 'http://localhost:8084/AvaliacaoPWE/'" class="btn btn-default">Voltar</a></button>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </body>
</html>