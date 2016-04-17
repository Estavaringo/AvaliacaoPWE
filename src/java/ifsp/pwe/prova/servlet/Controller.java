/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tarefa = req.getParameter("tarefa");

        if (tarefa == null) {
            throw new IllegalArgumentException("Você esqueceu de passar a tarefa!");
        }

        tarefa = "ifsp.pwe.prova.servlet." + tarefa;

        try {
            Class<?> tipo = Class.forName(tarefa);
            Tarefa instancia = (Tarefa) tipo.newInstance();

            if (!VerificaPermissao.executa(req, resp, instancia)) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("Negado.html");
                requestDispatcher.forward(req, resp);
            } else {

                String pagina = instancia.executa(req, resp);

                RequestDispatcher requestDispatcher = req.getRequestDispatcher(pagina);
                requestDispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);
        } catch (InstantiationException ex) {
            throw new ServletException(ex);
        } catch (IllegalAccessException ex) {
            throw new ServletException(ex);
        }
    }

}
