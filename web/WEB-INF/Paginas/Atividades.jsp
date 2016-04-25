
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
        <!-- Javascrip que abre um modal no carregamento da página -->
    </head>
    <body>
        <div class="row">
            <div class="col-md-7 col-md-offset-2 ">
                <br>
                <h1>Gerenciador de Atividades</h1>
                <br>
                <br>
                <c:if test="${not empty usuarioLogado}">

                    <h2>Logado como ${usuarioLogado.nome}</h2>
                    <br><br>
                    <form method="POST" action="Executa">
                        <div class="form-group">
                            <button type="submit" class="btn btn-default" value="Cadastrar Atividade">Cadastrar Atividade</button>
                            <input type="hidden" name="tarefa" value="CadastrarAtividade">
                        </div></form>
                    </c:if>

                <!-- Modal para Avisar que a atividade foi cadastrada com sucesso-->
                <c:if test="${not empty novaAtividade}">
                    <div class="modal hide fade" id="modalNovaAtividade">
                        <div class="modal-header">
                            <a class="close" data-dismiss="modal">×</a>
                            <h3>Nova Atividade</h3>
                        </div>
                        <div class="modal-body">
                            <p>A atividade <b>${novaAtividade.titulo}</b> foi cadastrada com sucesso!</p>
                        </div>
                        <div class="modal-footer">
                            <a href="#" class="btn">Fechar</a>
                        </div>
                    </div>
                </c:if>


                <br>
                <h3>Lista de Atividades</h3>
                <table class="table-striped table-hover table-bordered text-center ">
                    <thead class="thead-inverse">
                        <tr>
                            <td>Código</td>
                            <td>Título da Atividade</td>
                            <td>Descrição da Atividade</td>
                            <td>Data de Publicação</td>
                            <td>Inserido por</td>
                            <td>Correção</td>                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty listaDeAtividades}">
                            <c:forEach var="atividade" items="${listaDeAtividades}">
                                <tr>
                                    <th scope="row">${atividade.id}</th>
                                    <td> ${atividade.titulo}</td>
                                    <td> ${atividade.corpo} </td>
                                    <td> ${atividade.dataDeAdicao} </td>
                                    <td> ${atividade.usuario.nome}</td>
                                    <td> 
                                        <c:forEach var="correcao" items="${atividade.correcao}">
                                            <form method="GET" action="Executa">
                                                <button type="submit" class="btn btn-link" value="Incluir Correcao">${correcao.id}</button>
                                                <input type="hidden" name="tarefa" value="VisualizarCorrecao">
                                                <input type="hidden" name="correcao" value=${correcao.id}>
                                            </form>
                                        </c:forEach></td>
                                    
                                    
                                        <td> <form method="POST" action="Executa">
                                                <button type="submit" class="btn btn-default" value="Incluir Correcao">Incluir Correcao</button>
                                                <input type="hidden" name="tarefa" value="CadastroCorrecao">
                                                <input type="hidden" name="atividade" value=${atividade.id}>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                        </c:if>
                    </tbody>
                </table>

                </table>
                <br><br>

                <button type="button" onclick="window.location.href = '/AvaliacaoPWE'" class="btn btn-default">Voltar</button>

            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript">
                    $(window).load(function () {
                        $('#modalNovaAtividade').modal('show');
                    });
        </script>

    </body>
</html>
