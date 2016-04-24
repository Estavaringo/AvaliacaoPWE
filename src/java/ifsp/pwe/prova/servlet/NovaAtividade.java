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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
public class NovaAtividade implements Tarefa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        //instancia uma nova atividade
        Atividade atividade = new Atividade();

        //grava as informações da atividade no objeto
        atividade.setTitulo(req.getParameter("titulo"));
        atividade.setCorpo(req.getParameter("corpo"));

        //armazena o id do usuario para aquela atividade
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        atividade.setIdUsuario(usuario.getId());

        try {
            new AtividadeDAO().adiciona(atividade);
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir atividade no banco de dados. Detalhes: " + ex.getMessage());
            return "Erro.html";
        }

        req.setAttribute("novaAtividade", atividade);

        return "/WEB-INF/Paginas/Atividades.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }

}
