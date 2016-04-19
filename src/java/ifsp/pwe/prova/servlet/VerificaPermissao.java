/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Usuario;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
public class VerificaPermissao extends HttpServlet {

    static boolean executa(HttpServletRequest req, HttpServletResponse resp, Tarefa instancia) {
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        boolean acesso = instancia.verifica();
        System.out.println(acesso);
        
        
        if(usuario == null){
            if(acesso == false){
                return true;
            }else{
                return false;
            }
        }else{
            if(acesso == false){
                return true;
            }else if(usuario.isCorretor()){
                return true;                
            }else{
                return false;
            }
        }
    }
}
