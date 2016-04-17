/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Usuario;
import ifsp.pwe.prova.dao.UsuarioDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
public class Login implements Tarefa {

    Usuario usuario;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            
            usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
            
            if (usuario == null) {
                return "Invalido.html";
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", usuario);
                return "index.jsp";
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao consultar usuário no banco de dados. Detalhes: " + ex.getMessage());
            return "Erro.html";
        }
    }

    @Override
    public boolean verifica() {
        return false;
    }

}
