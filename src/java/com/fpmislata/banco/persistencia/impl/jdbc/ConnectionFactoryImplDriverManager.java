package com.fpmislata.banco.persistencia.impl.jdbc;

import com.fpmislata.banco.persistencia.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactoryImplDriverManager implements ConnectionFactory{

    @Override
    public Connection getConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/banco";
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection(URL, "root", "root");
            return connection;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException (ex);
        } catch (SQLException ex) {
            throw new RuntimeException (ex);
        }
    }

    @Override
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
