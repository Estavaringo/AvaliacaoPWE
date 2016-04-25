/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.dao;

import ifsp.pwe.prova.beans.Correcao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class CorrecaoDAO {

    BancoDados bd = new BancoDados();
    ArrayList<Correcao> listaCorrecao;

    public void adiciona(Correcao correcao) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO CORRECAO (CORR_ID, CORR_COMENTARIO, ATIVIDADE_ATIV_ID, USUARIO_USUA_ID) VALUES (?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setInt(1, correcao.getId());
            p.setString(2, correcao.getComentario());
            p.setInt(3, correcao.getIdAtividade());
            p.setInt(4, correcao.getIdUsuario());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }finally{
            bd.desconectar();
        }
    }

    public Correcao buscaPorTituloAtividade(String titulo) throws SQLException {
        try {
            Correcao obj = null;
            bd.conectar();
            String strSQL = "SELECT CORR_ID, CORR_COMENTARIO, ATIVIDADE_ATIV_ID, USUARIO_USUA_ID FROM CORRECAO WHERE ATIVIDADE_ATIV_ID = (SELECT ATIV_ID FROM ATIVIDADE WHERE ATIV_TITU = ?);";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setString(1, titulo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Correcao();
                obj.setId(rs.getInt("CORR_ID"));
                obj.setComentario(rs.getString("CORR_COMENTARIO"));
                obj.setIdAtividade(rs.getInt("ATIVIDADE_ATIV_ID"));
                obj.setIdUsuario(rs.getInt("USUARIO_USUA_ID"));
                p.close();
                bd.desconectar();
                return obj;
            }
            p.close();
            bd.desconectar();
            return obj;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    public ArrayList<Correcao> buscaPorIDAtividade(int id) throws SQLException {
        try {
            Correcao obj = null;
            listaCorrecao = new ArrayList<Correcao>();
            bd.conectar();
            String strSQL = "SELECT CORR_ID, CORR_COMENTARIO, ATIVIDADE_ATIV_ID, USUARIO_USUA_ID FROM CORRECAO WHERE ATIVIDADE_ATIV_ID = ?;";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                obj = new Correcao();
                obj.setId(rs.getInt("CORR_ID"));
                obj.setComentario(rs.getString("CORR_COMENTARIO"));
                obj.setIdAtividade(rs.getInt("ATIVIDADE_ATIV_ID"));
                obj.setIdUsuario(rs.getInt("USUARIO_USUA_ID"));
                p.close();
                bd.desconectar();
                listaCorrecao.add(obj);
            }
            p.close();
            bd.desconectar();
            rs.close();
            return listaCorrecao;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

}
