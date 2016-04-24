/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.dao;

import ifsp.pwe.prova.beans.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Flávio e Gabriel
 */
public class UsuarioDAO {
    
    BancoDados bd = new BancoDados();
    
    public void adiciona(Usuario usuario) throws SQLException {
        try {
            bd.conectar();
            int i = 0;
            if(usuario.isCorretor()){
                i = 1;
            }
            String strSql
                    = "INSERT INTO USUARIO (USUA_NM, USUA_EMAIL, USUA_SENHA, USUA_DT_NASC, USUA_ENDE, USUA_CORRETOR) VALUES (?,?,?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, usuario.getNome());
            p.setString(2, usuario.getEmail());
            p.setString(3, usuario.getSenha());
            
            
            //Verificar tipo do atributo. No banco está como DATETIME.
            p.setString(4, usuario.getDataNascimento());
            
            
            p.setString(5, usuario.getEndereco());
            p.setInt(6, i);
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    public Usuario buscaPorEmail(String email) throws SQLException{
        try {
            Usuario obj = null;
            bd.conectar();
            String strSQL = "SELECT USUA_ID, USUA_NM, USUA_EMAIL, USUA_SENHA, USUA_DT_NASC, USUA_ENDE, USUA_CORRETOR FROM USUARIO WHERE USUA_EMAIL = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setString(1, email);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Usuario();
                obj.setId(rs.getInt("USUA_ID"));
                obj.setNome(rs.getString("USUA_NM"));
                obj.setSenha(rs.getString("USUA_SENHA"));
                obj.setEmail(rs.getString("USUA_EMAIL"));
                obj.setDataNascimento(rs.getString("USUA_DT_NASC"));
                obj.setEndereco(rs.getString("USUA_ENDE"));
                if(rs.getInt("USUA_CORRETOR") == 1){
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

public Usuario buscaPorID(int id) throws SQLException{
        try {
            Usuario obj = null;
            bd.conectar();
            String strSQL = "SELECT USUA_ID, USUA_NM, USUA_EMAIL, USUA_SENHA, USUA_DT_NASC, USUA_ENDE, USUA_CORRETOR FROM USUARIO WHERE USUA_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Usuario();
                obj.setId(rs.getInt("USUA_ID"));
                obj.setNome(rs.getString("USUA_NM"));
                obj.setSenha(rs.getString("USUA_SENHA"));
                obj.setEmail(rs.getString("USUA_EMAIL"));
                obj.setDataNascimento(rs.getString("USUA_DT_NASC"));
                obj.setEndereco(rs.getString("USUA_ENDE"));
                if(rs.getInt("USUA_CORRETOR") == 1){
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
