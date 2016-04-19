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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            listaAtividade = new AtividadeDAO().buscaTitulo(filtro);
            req.setAttribute("buscaAtividade", listaAtividade);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/WEB-INF/Paginas/BuscaAtividade.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }

}
