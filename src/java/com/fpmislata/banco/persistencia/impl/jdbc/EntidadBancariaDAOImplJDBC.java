package com.fpmislata.banco.persistencia.impl.jdbc;

import com.fpmislata.banco.dominio.EntidadBancaria;
import com.fpmislata.banco.dominio.SucursalBancaria;
import com.fpmislata.banco.dominio.TipoEntidad;
import com.fpmislata.banco.persistencia.ConnectionFactory;
import com.fpmislata.banco.persistencia.EntidadBancariaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class EntidadBancariaDAOImplJDBC implements EntidadBancariaDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public EntidadBancaria get(int id) {
        Connection connection = connectionFactory.getConnection();
        EntidadBancaria entidadBancaria = new EntidadBancaria();
        String SQL = "Select * from entidadbancaria where id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            entidadBancaria.setId(resultSet.getInt(1));
            entidadBancaria.setCodigoEntidad(resultSet.getInt(2));
            entidadBancaria.setNombreEntidad(resultSet.getString(3));
            entidadBancaria.setCif(resultSet.getString(4));
            entidadBancaria.setTipoEntidad(TipoEntidad.valueOf(resultSet.getString(5)));
            return entidadBancaria;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public EntidadBancaria insert(EntidadBancaria entidadBancaria) {
        Connection connection = connectionFactory.getConnection();
        String SQL = "INSERT INTO entidadBancaria VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, entidadBancaria.getId());
            preparedStatement.setInt(2, entidadBancaria.getCodigoEntidad());
            preparedStatement.setString(3, entidadBancaria.getNombreEntidad());
            preparedStatement.setString(4, entidadBancaria.getCif());
            preparedStatement.setString(5, entidadBancaria.getTipoEntidad().toString());
            preparedStatement.execute();

            return get(entidadBancaria.getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
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
        Connection conexion = connectionFactory.getConnection();
        String SQL = "DELETE FROM entidadBancaria WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public EntidadBancaria update(EntidadBancaria entidadBancaria) {
        Connection conexion = connectionFactory.getConnection();
        try {

            String SQL = "UPDATE entidadBancaria SET codigoEntidad = ?, nombreEntidad = ?, cif= ?, tipoEntidad= ? WHERE id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);
            preparedStatement.setInt(1, entidadBancaria.getCodigoEntidad());
            preparedStatement.setString(2, entidadBancaria.getNombreEntidad());
            preparedStatement.setString(3, entidadBancaria.getCif());
            preparedStatement.setString(4, entidadBancaria.getTipoEntidad().toString());
            preparedStatement.setInt(5, entidadBancaria.getId());

            preparedStatement.execute();

            return get(entidadBancaria.getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<EntidadBancaria> findAll() {
        Connection conexion = connectionFactory.getConnection();
        List<EntidadBancaria> entidadesBancarias = new ArrayList<>();
        String SQL = "SELECT * FROM entidadbancaria";
        try {

            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EntidadBancaria entidadBancaria = new EntidadBancaria();

                entidadBancaria.setId(resultSet.getInt(1));
                entidadBancaria.setCodigoEntidad(resultSet.getInt(2));
                entidadBancaria.setNombreEntidad(resultSet.getString(3));
                entidadBancaria.setCif(resultSet.getString(4));
                entidadBancaria.setTipoEntidad(TipoEntidad.valueOf(resultSet.getString(5)));

                entidadesBancarias.add(entidadBancaria);
            }

            return entidadesBancarias;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<SucursalBancaria> getSucursalesBancarias(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
