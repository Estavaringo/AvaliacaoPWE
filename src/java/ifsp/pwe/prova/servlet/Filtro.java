/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel e Flávio
 */
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        String usuario = getUsuario(req);
        System.out.println("O usuario " + usuario + " está acessando a URL " + url);
        chain.doFilter(request, response);
    }
    
    public String getUsuario(HttpServletRequest req){
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        
        //Verifica se a sessão existe. Se não existir informa que o usuário está deslogado.
        if(usuario == null){
            return "<deslogado>";
        }
        
        return usuario.getNome();
    }

    @Override
    public void destroy() {
    }
    
    
}