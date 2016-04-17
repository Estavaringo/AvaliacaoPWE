/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Usuario;
import ifsp.pwe.prova.dao.UsuarioDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
public class Login implements Tarefa{

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        Usuario usuario = null;
            usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
        if(usuario==null){
            return "Invalido.html";
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);
            return "index.jsp";
        }
    }

    @Override
    public boolean verifica() {
        return false;
    }
    
}
