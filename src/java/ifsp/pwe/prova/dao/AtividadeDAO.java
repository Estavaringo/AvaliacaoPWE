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
    
    //Declaração das DAOs de Usuario e Objeto
    UsuarioDAO usuarioDAO;
    CorrecaoDAO correcaoDAO;

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

    //Método que localiza todas as atividades pelo Titulo, implementando as regras de perfil de acesso
    public ArrayList<Atividade> buscaAtividadesPorTitulo(Usuario usuario, String titulo) throws SQLException {
        
        //Instância as DAO de Usuario e Correcao
        usuarioDAO = new UsuarioDAO();
        correcaoDAO = new CorrecaoDAO();
        
        try {
            ArrayList<Atividade> lista = new ArrayList<Atividade>() {
            };
            bd.conectar();
            String strSQL = ""
                    + "SELECT "
                    + " A.ATIV_ID, "
                    + " A.ATIV_TITU, "
                    + " A.ATIV_CORPO, "
                    + " A.ATIV_DT, "
                    + " A.USUARIO_USUA_ID "
                    + "FROM ATIVIDADE A "
                    + "WHERE A.ATIV_TITU LIKE ? ";
            
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setString(1, "%" + titulo + "%");
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                
                //Instância Atividade
                Atividade obj = new Atividade();
                
                //Atribui os dados de Atividade
                obj.setId(rs.getInt("ATIV_ID"));
                obj.setTitulo(rs.getString("ATIV_TITU"));
                obj.setCorpo(rs.getString("ATIV_CORPO"));
                obj.setDataDeAdicao(rs.getTimestamp("ATIV_DT"));
                
                //Através de UsuarioDAO, localiza os dados do Usuário
                obj.setUsuario(usuarioDAO.buscaPorID(rs.getInt("USUARIO_USUA_ID")));
                
                /*Se o Usuario estiver vazio, entende que ele está deslogado. Desta forma, 
                não será atribuido os dados das Correções*/
                if (usuario != null) {
                    //Verifica se o usuario possui o Perfil de Corretor de Atividades.
                    //Se o usuario for corretor, atribui todas as correções.
                    if (usuario.isCorretor()) {
                        //Através de CorrecaoDAO, localiza os dados da Correcao
                        obj.setCorrecao(correcaoDAO.buscaPorIDAtividade(obj.getId()));
                    } else {
                        /*Se o usuario não for corretor, atribui a Correcao, para as atividades 
                        que foram submetidas por ele*/
                        if (usuario.getId() == obj.getIdUsuario()) {
                            //Através de CorrecaoDAO, localiza os dados da Correcao
                            obj.setCorrecao(correcaoDAO.buscaPorIDAtividade(obj.getId()));
                        }
                    }
                }
                
                //Adiciona o objeto Atividade a lista de Atividades
                lista.add(obj);
            }
            p.close();
            bd.desconectar();
            return lista;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }finally{
            correcaoDAO = null;
            usuarioDAO = null;
        }
    }

    public ArrayList<Atividade> buscaAtividades(Usuario usuario) throws SQLException {
        
        //Instância as DAO de Usuario e Correcao
        usuarioDAO = new UsuarioDAO();
        correcaoDAO = new CorrecaoDAO();
        
        try {
            ArrayList<Atividade> lista = new ArrayList<Atividade>() {
            };
            bd.conectar();
            String strSQL = ""
                    + "SELECT "
                    + " A.ATIV_ID, "
                    + " A.ATIV_TITU, "
                    + " A.ATIV_CORPO, "
                    + " A.ATIV_DT, "
                    + " A.USUARIO_USUA_ID "
                    + "FROM ATIVIDADE A ";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                
                //Instância Atividade
                Atividade obj = new Atividade();
                
                //Atribui os dados de Atividade
                obj.setId(rs.getInt("ATIV_ID"));
                obj.setTitulo(rs.getString("ATIV_TITU"));
                obj.setCorpo(rs.getString("ATIV_CORPO"));
                obj.setDataDeAdicao(rs.getTimestamp("ATIV_DT"));
                
                //Através de UsuarioDAO, localiza os dados do Usuário
                obj.setUsuario(usuarioDAO.buscaPorID(rs.getInt("USUARIO_USUA_ID")));
                
                /*Se o Usuario estiver vazio, entende que ele está deslogado. Desta forma, 
                não será atribuido os dados das Correções*/
                if (usuario != null) {
                    //Verifica se o usuario possui o Perfil de Corretor de Atividades.
                    //Se o usuario for corretor, atribui todas as correções.
                    if (usuario.isCorretor()) {
                        //Através de CorrecaoDAO, localiza os dados da Correcao
                        obj.setCorrecao(correcaoDAO.buscaPorIDAtividade(obj.getId()));
                    } else {
                        /*Se o usuario não for corretor, atribui a Correcao, para as atividades 
                        que foram submetidas por ele*/
                        if (usuario.getId() == obj.getIdUsuario()) {
                            //Através de CorrecaoDAO, localiza os dados da Correcao
                            obj.setCorrecao(correcaoDAO.buscaPorIDAtividade(obj.getId()));
                        }
                    }
                }
                
                //Adiciona o objeto Atividade a lista de Atividades
                lista.add(obj);
            }
            p.close();
            bd.desconectar();
            return lista;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }finally{
            correcaoDAO = null;
            usuarioDAO = null;
        }
    }

}
