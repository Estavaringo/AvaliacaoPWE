/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.dao;

import ifsp.pwe.prova.beans.Atividade;
import ifsp.pwe.prova.beans.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class AtividadeDAO {
    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
    BancoDados bd = new BancoDados();

    public void adiciona(Atividade atividade) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO ATIVIDADE ("
                    + " ATIV_TITU, "
                    + " ATIV_CORPO, "
                    + " ATIV_DT, "
                    + " USUARIO_USUA_ID) "
                    + "VALUES ("
                    + " ?,"
                    + " ?,"
                    + " ?,"
                    + " ?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, atividade.getTitulo());
            p.setString(2, atividade.getCorpo());
            
            
            p.setTimestamp(3, date);
            
            
            p.setInt(4, atividade.getIdUsuario());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }
    //Método que localiza todas as atividades que possuem um Titulo
    public ArrayList<Atividade> buscaPorTitulo(String filtro) throws SQLException {
        try {
            ArrayList<Atividade> lista = new ArrayList<Atividade>() {};
            bd.conectar();
            String strSQL = ""
                    + "SELECT "
                    + " ATIV_ID, "
                    + " ATIV_TITU, "
                    + " ATIV_CORPO, "
                    + " ATIV_DT, "
                    + " USUARIO_USUA_ID "
                    + "FROM ATIVIDADE A "
                    + "LEFT JOIN USUARIO B "
                    + "ON A.USUARIO_USUA_ID = B.USUA_ID "
                    + "WHERE ATIV_TITU LIKE ? ";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setString(1, "%" + filtro + "%");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Atividade obj = new Atividade();
                obj.setId(rs.getInt("ATIV_ID"));
                obj.setTitulo(rs.getString("ATIV_TITU"));
                obj.setCorpo(rs.getString("ATIV_CORPO"));
              
                //Verificar tipo do atributo. No banco está como DATETIME.
                obj.setDate(rs.getTimestamp("ATIV_DT"));
                
                obj.setIdUsuario(rs.getInt("USUARIO_USUA_ID"));
                lista.add(obj);
            }
            p.close();
            bd.desconectar();
            return lista;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }

    }
    
    public ArrayList<Atividade> buscaPorUsuario(Usuario usuario) throws SQLException {
        try {
            ArrayList<Atividade> lista = new ArrayList<Atividade>() {};
            bd.conectar();
            String strSQL = ""
                    + "SELECT "
                    + " ATIV_ID, "
                    + " ATIV_TITU, "
                    + " ATIV_CORPO, "
                    + " ATIV_DT, "
                    + " USUARIO_USUA_ID "
                    + "FROM ATIVIDADE A "
                    + "LEFT JOIN USUARIO B "
                    + "ON A.USUARIO_USUA_ID = B.USUA_ID "
                    + "WHERE B.USUA_ID = ? "
                    + " AND B.USUA_CORRETOR = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, usuario.getId());
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Atividade obj = new Atividade();
                obj.setId(rs.getInt("ATIV_ID"));
                obj.setTitulo(rs.getString("ATIV_TITU"));
                obj.setCorpo(rs.getString("ATIV_CORPO"));
              
                //Verificar tipo do atributo. No banco está como DATETIME.
                obj.setDate(rs.getTimestamp("ATIV_DT"));
                
                obj.setIdUsuario(rs.getInt("USUARIO_USUA_ID"));
                lista.add(obj);
            }
            p.close();
            bd.desconectar();
            return lista;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }

    }

}
