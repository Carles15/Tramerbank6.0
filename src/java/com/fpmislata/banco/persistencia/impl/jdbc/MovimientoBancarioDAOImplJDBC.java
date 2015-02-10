/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.persistencia.impl.jdbc;

import com.fpmislata.banco.dominio.MovimientoBancario;
import com.fpmislata.banco.persistencia.ConnectionFactory;
import com.fpmislata.banco.persistencia.MovimientoBancarioDAO;
import com.mysql.jdbc.ResultSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alumno
 */
public class MovimientoBancarioDAOImplJDBC implements MovimientoBancarioDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public MovimientoBancario get(int id) {
        MovimientoBancario movimientoBancario = new MovimientoBancario();
        Connection connection = connectionFactory.getConnection();
        String SQL = "SELECT * FROM movimientobancario WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            movimientoBancario.setId(resultSet.getInt("id"));
            movimientoBancario.setCuentaPertenece(resultSet.getInt("cuentapertenece"));
            movimientoBancario.setImporte(resultSet.getDouble("importe"));
            movimientoBancario.setFecha(resultSet.getDate("fecha"));
            movimientoBancario.setSaldoTotal(resultSet.getDouble("saldototal"));
           // movimientoBancario.setTipoMovimiento(resultSet.get("tipomovimiento"));
            movimientoBancario.setConcepto(resultSet.getString("concepto"));

            return movimientoBancario;
        } catch (Exception ex) {
            throw new RuntimeException("Error al hacer la consulta", ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public MovimientoBancario insert(MovimientoBancario movimientoBancario) {
        Connection connection = connectionFactory.getConnection();
        String SQL = "INSERT INTO movimientobancario (id, cuentapertenece,tipoMovimiento, importe, saldoTotal, concepto) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, movimientoBancario.getId());
            preparedStatement.setInt(2, movimientoBancario.getCuentaPertenece());
            //preparedStatement.setString(3, movimientoBancario.getTipoMovimiento());
            preparedStatement.setDouble(4, movimientoBancario.getImporte());
            preparedStatement.setDouble(5, movimientoBancario.getSaldoTotal());
            preparedStatement.setString(6, movimientoBancario.getConcepto());
            preparedStatement.executeUpdate();

            movimientoBancario.setFecha(new Date());
            return get(movimientoBancario.getId());
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar el movimientos", ex);
        } finally {
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
        String SQL = "DELETE FROM movimientobancario WHERE id= ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar", ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

     

    }

    @Override
    public MovimientoBancario update(MovimientoBancario movimientoBancario) {
         Connection connection = connectionFactory.getConnection();
        String SQL = "UPDATE movimientobancario SET cuentapertenece =? , importe = ? , saldoTotal = ? , tipoMovimiento = ?, concepto = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setInt(1, movimientoBancario.getCuentaPertenece());
            preparedStatement.setDouble(2, movimientoBancario.getImporte());
            preparedStatement.setDouble(3, movimientoBancario.getSaldoTotal());
            //preparedStatement.setString(4, movimientoBancario.getTipoMovimiento());
            preparedStatement.setString(5, movimientoBancario.getConcepto());
            preparedStatement.setInt(6, movimientoBancario.getId());
            preparedStatement.executeUpdate();            
            movimientoBancario.setFecha(new Date());
            return get(movimientoBancario.getId());
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar la entidad",ex);
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
       
       
    }

    @Override
    public List<MovimientoBancario> findAll() {
        List<MovimientoBancario> movimientosBancarios = new ArrayList<>();
       Connection connection = connectionFactory.getConnection();
       String SQL="SELECT * FROM movimientobancario ";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);      
            ResultSet resultSet = preparedStatement.executeQuery(); 
            while(resultSet.next()){
                MovimientoBancario movimientoBancario= new MovimientoBancario();
                movimientoBancario.setId(resultSet.getInt("id"));
                movimientoBancario.setCuentaPertenece(resultSet.getInt("cuentapertenece"));
                movimientoBancario.setImporte(resultSet.getDouble("importe"));
                movimientoBancario.setFecha(resultSet.getDate("fecha"));
                movimientoBancario.setSaldoTotal(resultSet.getDouble("saldototal"));
                //movimientoBancario.setTipoMovimiento(resultSet.getString("tipomovimiento"));
                movimientoBancario.setConcepto(resultSet.getString("concepto"));
                movimientosBancarios.add(movimientoBancario);
            }         
            
            return movimientosBancarios;
        } catch (Exception ex) {
            throw new RuntimeException("Error al hacer la consulta",ex);
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
