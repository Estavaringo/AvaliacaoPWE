/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.dao;

import ifsp.pwe.prova.beans.Atividade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class AtividadeDAO {

    BancoDados bd = new BancoDados();

    public void adiciona(Atividade atividade) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO ATIVIDADE (ATIV_TITU, ATIV_CORPO, ATIV_DT, USUARIO__USUA_ID) VALUES (?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, atividade.getTitulo());
            p.setString(2, atividade.getCorpo());
            
            
            //Verificar tipo do atributo. No banco está como DATETIME.
            p.setString(3, atividade.getData());
            
            
            p.setInt(4, atividade.getIdUsuario());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    public ArrayList<Atividade> buscaTitulo(String filtro) throws SQLException {
        try {
            ArrayList<Atividade> lista = new ArrayList<Atividade>() {};
            bd.conectar();
            String strSQL = "SELECT ATIV_ID, ATIV_TITU, ATIV_CORPO, ATIV_DT, USUARIO__USUA_ID FROM ATIVIDADE WHERE ATIV_TITU LIKE ? ";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setString(1, "%" + filtro + "%");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Atividade obj = new Atividade();
                obj.setId(rs.getInt("ATIV_ID"));
                obj.setTitulo(rs.getString("ATIV_TITU"));
                obj.setCorpo(rs.getString("ATIV_CORPO"));
              
                //Verificar tipo do atributo. No banco está como DATETIME.
                obj.setData(rs.getString("ATIV_DT"));
                
                obj.setIdUsuario(rs.getInt("USUARIO__USUA_ID"));
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
