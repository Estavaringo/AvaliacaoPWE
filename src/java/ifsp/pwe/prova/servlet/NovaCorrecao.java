/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Correcao;
import ifsp.pwe.prova.dao.CorrecaoDAO;

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
        correcao.setComentario(req.getParameter("titulo"));
        correcao.setIdAtividade(req.getParameter("atividade"));
        
        //armazena o id do usuario para aquela atividade
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        correcao.setIdUsuario(usuario.getId());
        
        new CorrecaoDAO().adiciona(correcao);

        req.setAttribute("novaCorrecao", correcao);

        return "/WEB-INF/Paginas/Atividades.jsp";
    }

    @Override
    public boolean verifica() {
        
    }
    
}
