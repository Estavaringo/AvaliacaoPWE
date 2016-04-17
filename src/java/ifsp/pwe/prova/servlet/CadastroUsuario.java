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

/**
 *
 * @author gabri
 */
public class CadastroUsuario implements Tarefa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        Usuario usuario = new Usuario();
        usuario.setNome(req.getParameter("nome"));
        usuario.setEmail(req.getParameter("email"));
        usuario.setSenha(req.getParameter("senha"));
        usuario.setEndereco(req.getParameter("endereco"));
        usuario.setDataNascimento(req.getParameter("data"));

        new UsuarioDAO().adiciona(usuario);

        req.setAttribute("novoUsuario", usuario);

        return "/WEB-INF/Paginas/Cadastro.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }
}
