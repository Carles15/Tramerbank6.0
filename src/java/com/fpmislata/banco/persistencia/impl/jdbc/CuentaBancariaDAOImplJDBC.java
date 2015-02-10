package com.fpmislata.banco.persistencia.impl.jdbc;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.MovimientoBancario;
import com.fpmislata.banco.persistencia.ConnectionFactory;
import com.fpmislata.banco.persistencia.CuentaBancariaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class CuentaBancariaDAOImplJDBC implements CuentaBancariaDAO{
    
    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public CuentaBancaria get(int id) {
        Connection connection = connectionFactory.getConnection();
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        String SQL = "SELECT * FROM cuentabancaria WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cuentaBancaria.setId(resultSet.getInt(1));
            cuentaBancaria.setSucursalPertenece(resultSet.getInt(2));
            cuentaBancaria.setNumCuenta(resultSet.getInt(3));
            cuentaBancaria.setDigitosControl(resultSet.getInt(4));
            cuentaBancaria.setSaldoCuenta(resultSet.getDouble(5));
            
            return cuentaBancaria;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public CuentaBancaria insert(CuentaBancaria cuentaBancaria) {
        Connection connection = connectionFactory.getConnection();
        String SQL = "INSERT INTO cuentabancaria VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, cuentaBancaria.getId());
            preparedStatement.setInt(2, cuentaBancaria.getSucursalPertenece());
            preparedStatement.setInt(3, cuentaBancaria.getNumCuenta());
            preparedStatement.setInt(4, cuentaBancaria.getDigitosControl());
            preparedStatement.setDouble(5, cuentaBancaria.getSaldoCuenta());
            
            preparedStatement.execute();
            
            return get(cuentaBancaria.getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = connectionFactory.getConnection();
        String SQL = "DELETE FROM cuentabancaria WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public CuentaBancaria update(CuentaBancaria cuentaBancaria) {
        Connection connection = connectionFactory.getConnection();
        String SQL = "UPDATE cuentabancaria SET sucursalPertenece = ?, numCuenta = ?, digitosControl= ?, saldoCuenta = ? WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, cuentaBancaria.getSucursalPertenece());
            preparedStatement.setInt(2, cuentaBancaria.getNumCuenta());
            preparedStatement.setInt(3, cuentaBancaria.getDigitosControl());
            preparedStatement.setDouble(4, cuentaBancaria.getSaldoCuenta());
            preparedStatement.setInt(5, cuentaBancaria.getId());
            
            preparedStatement.execute();
            
            return get(cuentaBancaria.getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CuentaBancariaDAOImplJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<CuentaBancaria> findAll() {
        Connection connection = connectionFactory.getConnection();
        List <CuentaBancaria> cuentasBancarias = new ArrayList <>();
        String SQL = "SELECT * FROM cuentabancaria";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                CuentaBancaria cuentaBancaria = new CuentaBancaria();
                
                cuentaBancaria.setId(resultSet.getInt(1));
                cuentaBancaria.setSucursalPertenece(resultSet.getInt(2));
                cuentaBancaria.setNumCuenta(resultSet.getInt(3));
                cuentaBancaria.setDigitosControl(resultSet.getInt(4));
                cuentaBancaria.setSaldoCuenta(resultSet.getDouble(5));
                
                cuentasBancarias.add(cuentaBancaria);
            }
            
            return cuentasBancarias;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<MovimientoBancario> getMovimientosBancarios(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public CuentaBancaria getNumeroCuenta(int numCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CuentaBancaria getCuentaPorNumero(int numCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
