<%-- 
    Document   : index
    Created on : 07/03/2016, 20:11:10
    Author     : gabri
--%>

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
            <div class="col-md-5 col-md-offset-2 ">
                <br>
                <h1>Gerenciador de Atividades</h1>
                <br><br>
                <c:if test="${not empty usuarioLogado}">

                    <h4>Logado como ${usuarioLogado.nome}</h4>
                    <br><br>
                    <form method="POST" action="Executa">
                        <div class="form-group">
                            <button type="submit" class="btn btn-default" value="Cadastrar Atividade">Cadastrar Atividade</button>
                            <input type="hidden" name="tarefa" value="CadastrarAtividade">
                        </div></form>
                    </c:if>

                <c:if test="${empty usuarioLogado}">  

                    <form method="POST" action="Executa">
                        <div class="form-group">
                            <label>Email:</label> 
                            <input type="text" class="form-control" placeholder="Digite aqui o seu email" name="email" value="" />
                        </div>

                        <div class="form-group">
                            <label>Senha:</label> 
                            <input type="password" class="form-control" placeholder="Digite aqui a sua senha" name="senha" value="" />
                        </div>
                        <input type="hidden" name="tarefa" value="Login">
                        <button type="submit" class="btn btn-default" value="Login">Login</button>
                    </form>

                    <br>
                    <button type="button" onclick="window.location.href = 'http://localhost:8084/AvaliacaoPWE/CadastroUsuario.html'" class="btn btn-default">Cadastrar</a></button>
                    <br>

                </c:if>
                <br>
                <form action="Executa" method="GET">
                    <div class="form-group">
                        <label>Buscar atividade:</label> 
                        <input type="text" class="form-control" placeholder="Digite aqui o titulo da atividade" name="filtroDeTitulo">
                        <br>
                        <button type="submit" class="btn btn-default" value="Buscar Atividade">Buscar Atividade</button>
                        <input type="hidden" name="tarefa" value="BuscaAtividade">
                        <input type="hidden" name="busca" value="buscaPorTitulo">
                        </form>



                        <c:if test="${not empty usuarioLogado}">
                            <br><br>
                            <form action="Executa" method="POST">
                                <button type="submit" class="btn btn-default" value="Deslogar">Deslogar</button>
                                <input type="hidden" name="tarefa" value="Logout"><br><br>
                            </form>
                        </c:if>
                    </div>
            </div>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </body>
</html>