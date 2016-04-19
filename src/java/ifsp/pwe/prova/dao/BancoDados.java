/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class BancoDados {

    public Connection connection = null;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String LOCAL = "provapwe";
    
    /*Conexão com o banco de dados na AWS da Amazon*/
    private final String URL = "jdbc:mysql://" + "dbinstancemysql.czzc5ptkwvqs.us-west-2.rds.amazonaws.com:3306/" + LOCAL;
    private final String LOGIN = "admin";
    private final String SENHA = "password";
    
    
    public void conectar() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.
                    getConnection(URL, LOGIN, SENHA);

        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    
}
