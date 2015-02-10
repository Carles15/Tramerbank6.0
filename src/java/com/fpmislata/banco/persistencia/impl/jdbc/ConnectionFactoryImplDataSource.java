package com.fpmislata.banco.persistencia.impl.jdbc;

import com.fpmislata.banco.persistencia.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnectionFactoryImplDataSource implements ConnectionFactory{

    @Override
    public Connection getConnection() {
        try {
            InitialContext initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/tramerbank");
            
            Connection connection = dataSource.getConnection();
            
            return connection;
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
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
