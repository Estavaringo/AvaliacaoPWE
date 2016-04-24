/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.dao;

import ifsp.pwe.prova.beans.Correcao;
import ifsp.pwe.prova.beans.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class CorrecaoDAO {

    BancoDados bd = new BancoDados();
    
    public void adiciona(Correcao correcao) throws SQLException{
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
        }
    }
    
    

    public Usuario buscaPorEmailESenha(String email, String senha) throws SQLException{
        try {
            Usuario obj = null;
            bd.conectar();
            String strSQL = "SELECT USUA_NM, USUA_EMAIL, USUA_DT, USUA_ENDE, USUA_CORRETOR FROM USUARIO WHERE USUA_EMAIL = ? AND USUA_SENHA = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setString(1, email);
            p.setString(2, senha);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Usuario();
                obj.setNome(rs.getString("USUA_NM"));
                obj.setEmail(rs.getString("USUA_EMAIL"));
                obj.setNome(rs.getString("USUA_DT"));
                obj.setNome(rs.getString("USUA_ENDE"));
                if(rs.getString("USUA_COR").equals("1")){
                    obj.setCorretor(true);
                }else{
                    obj.setCorretor(false);
                }
                
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
    
}
