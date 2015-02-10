/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.persistencia.impl.jdbc;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.SucursalBancaria;
import com.fpmislata.banco.persistencia.ConnectionFactory;
import com.fpmislata.banco.persistencia.SucursalBancariaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alumno
 */
public class SucursalBancariaDAOImplJDBC implements SucursalBancariaDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public SucursalBancaria get(int id) {
        Connection connection = (Connection) connectionFactory.getConnection();
        SucursalBancaria sucursalBancaria = new SucursalBancaria();
        String SQL = "SELECT * FROM sucursalbancaria WHERE id = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sucursalBancaria.setId(resultSet.getInt(1));
                sucursalBancaria.setEntidadPertenece(resultSet.getInt(2));
                sucursalBancaria.setCodigoSucursal(resultSet.getString(3));
                sucursalBancaria.setNombreSucursal(resultSet.getString(4));
            }
            return sucursalBancaria;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public SucursalBancaria insert(SucursalBancaria sucursalBancaria) {
        Connection connection = connectionFactory.getConnection();

        String SQL = "INSERT INTO sucursalbancaria VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, sucursalBancaria.getId());
            preparedStatement.setInt(2, sucursalBancaria.getEntidadPertenece());
            preparedStatement.setString(3, sucursalBancaria.getCodigoSucursal());
            preparedStatement.setString(4, sucursalBancaria.getNombreSucursal());

            preparedStatement.execute();

            return get(sucursalBancaria.getId());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = connectionFactory.getConnection();

        String SQL = "DELETE FROM sucursalbancaria WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public SucursalBancaria update(SucursalBancaria sucursalBancaria) {
        Connection connection = connectionFactory.getConnection();

        String SQL = "UPDATE sucursalbancaria SET entidadPertenece=?, codigoSucursal=?, nombreSucursal=? WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, sucursalBancaria.getEntidadPertenece());
            preparedStatement.setString(2, sucursalBancaria.getCodigoSucursal());
            preparedStatement.setString(3, sucursalBancaria.getNombreSucursal());
            preparedStatement.setInt(4, sucursalBancaria.getId());

            preparedStatement.execute();

            return get(sucursalBancaria.getId());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<SucursalBancaria> findAll() {
        Connection connection = connectionFactory.getConnection();
        List<SucursalBancaria> sucursalesBancarias = new ArrayList<>();

        String SQL = "SELECT * FROM sucursalbancaria";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SucursalBancaria sucursalBancaria = new SucursalBancaria();
                sucursalBancaria.setId(resultSet.getInt(1));
                sucursalBancaria.setEntidadPertenece(resultSet.getInt(2));
                sucursalBancaria.setCodigoSucursal(resultSet.getString(3));
                sucursalBancaria.setNombreSucursal(resultSet.getString(4));

                sucursalesBancarias.add(sucursalBancaria);
            }

            return sucursalesBancarias;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public List<CuentaBancaria> getCuentasBancarias(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
