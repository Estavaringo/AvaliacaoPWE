/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Atividade;
import ifsp.pwe.prova.dao.AtividadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class BuscaAtividade implements Tarefa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        String filtro = req.getParameter("filtro");
        ArrayList<Atividade> listaAtividade;

        try {
            listaAtividade = new AtividadeDAO().buscaPorTitulo(filtro);
            req.setAttribute("listaDeAtividades", listaAtividade);
        } catch (SQLException ex) {
            System.out.println("Erro ao Buscar Atividade. Detalhes: " + ex.getMessage());
            return "Erro.html";
        }

        return "/WEB-INF/Paginas/Atividades.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }

}
