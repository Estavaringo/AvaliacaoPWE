/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Atividade;
import ifsp.pwe.prova.dao.AtividadeDAO;
import java.util.Collection;
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
        Collection<Atividade> listaAtividade;

        listaAtividade = new AtividadeDAO().buscaSimilar(filtro);
        
        req.setAttribute("buscaAtividade", listaAtividade);
        
        return "/WEB-INF/Paginas/BuscaAtividade.jsp";
    }

    @Override
    public boolean verifica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
