/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Correcao;
import ifsp.pwe.prova.beans.Usuario;
import ifsp.pwe.prova.dao.CorrecaoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
public class NovaCorrecao implements Tarefa{

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        
        //instancia uma nova atividade
        Correcao correcao = new Correcao();
        
        //grava as informações da atividade no objeto
        correcao.setComentario(req.getParameter("comentario"));
        correcao.setIdAtividade(Integer.parseInt(req.getParameter("atividade")));
        
        //armazena o id do usuario para aquela atividade
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        correcao.setIdUsuario(usuario.getId());
        
        try {
            new CorrecaoDAO().adiciona(correcao);
        } catch (SQLException ex) {
            Logger.getLogger(NovaCorrecao.class.getName()).log(Level.SEVERE, null, ex);
        }

        req.setAttribute("novaCorrecao", correcao);

        return "/WEB-INF/Paginas/Atividades.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
    
}
