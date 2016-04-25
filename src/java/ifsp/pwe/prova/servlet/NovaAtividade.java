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
 * @author gabri
 */
public class NovaAtividade implements Tarefa {

    //Declarações
    private ArrayList<Atividade> listaAtividade = null;
    private Atividade atividade = null;
    private Usuario usuario = null;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        try {

            //instancia uma nova atividade
            atividade = new Atividade();
            
            //Localiza o usuario que está logado
            HttpSession session = req.getSession();
            usuario = (Usuario) session.getAttribute("usuarioLogado");

            //Atribui as informações da atividade no objeto
            atividade.setTitulo(req.getParameter("titulo"));
            atividade.setCorpo(req.getParameter("corpo"));
            atividade.setUsuario(usuario);            

            //Grava um nova atividade no banco de dados
            new AtividadeDAO().adiciona(atividade);
            
            //Atribui a ultima atividade como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("novaAtividade", atividade);

            //Busca todas as atividades que estão vinculadas a este usuário
            listaAtividade = new AtividadeDAO().buscaAtividades(usuario);

            //Atribui a lista de atividades preenchida como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaDeAtividades", listaAtividade);

        } catch (SQLException ex) {
            System.err.println("Erro ao inserir atividade no banco de dados. Detalhes: " + ex.getMessage());
            return "Erro.html";
        }

        return "/WEB-INF/Paginas/Atividades.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }

}
