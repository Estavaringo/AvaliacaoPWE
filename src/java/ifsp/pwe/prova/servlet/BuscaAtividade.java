/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Atividade;
import ifsp.pwe.prova.beans.Usuario;
import ifsp.pwe.prova.dao.AtividadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Flávio e Gabriel
 */
public class BuscaAtividade implements Tarefa {

    //Declarações
    private ArrayList<Atividade> listaAtividade = null;
    private Usuario usuario = null;
    private String filtroDeTitulo = "";

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        try {
            
            //Localiza o usuario que está logado
            HttpSession session = req.getSession();
            usuario = (Usuario) session.getAttribute("usuarioLogado");

            //Recupera o texto que será utilizado como filtro no Titulo das Atividades
            filtroDeTitulo = req.getParameter("filtroDeTitulo");
            
            //Localiza todas a atividades através do Titulo
            listaAtividade = new AtividadeDAO().buscaAtividadesPorTitulo(usuario, filtroDeTitulo);
            
            //Atribui a lista de atividades preenchida como Atributo da Requisição 
            req.setAttribute("listaDeAtividades", listaAtividade);
            
        } catch (SQLException ex) {
            System.out.println("Erro ao Buscar Atividade. Detalhes: " + ex.getMessage());
            return "Erro.html";
        }
        
        //Retorna o endereço da Página Web (VIEW) responsável em mostrar todas as atividades
        return "/WEB-INF/Paginas/Atividades.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }

}
