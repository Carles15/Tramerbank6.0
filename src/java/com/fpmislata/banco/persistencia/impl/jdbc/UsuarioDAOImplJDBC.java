package com.fpmislata.banco.persistencia.impl.jdbc;

import com.fpmislata.banco.dominio.Rol;
import com.fpmislata.banco.dominio.Usuario;
import com.fpmislata.banco.persistencia.ConnectionFactory;
import com.fpmislata.banco.persistencia.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioDAOImplJDBC implements UsuarioDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public Usuario get(int id) {
        Connection conexion = connectionFactory.getConnection();
        Usuario usuario = new Usuario();
        String SQL = "SELECT * FROM usuarios WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuario.setId(resultSet.getInt(1));
                usuario.setNombre(resultSet.getString(2));
                usuario.setApellidos(resultSet.getString(3));
                usuario.setEmail(resultSet.getString(4));
                usuario.setDireccion(resultSet.getString(5));
                usuario.setTelefono(resultSet.getString(6));
                usuario.setContrasenya(resultSet.getString(7));
                usuario.setRol(Rol.valueOf(resultSet.getString(8)));
                usuario.setIdCuentaBancaria(resultSet.getInt(9));
            }

            return usuario;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Usuario insert(Usuario usuario) {
        Connection conexion = connectionFactory.getConnection();
        String SQL = "INSERT INTO usuarios VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);

            preparedStatement.setInt(1, usuario.getId());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, usuario.getTelefono());
            preparedStatement.setString(7, usuario.getContrasenya());
            preparedStatement.setString(8, usuario.getRol().toString());
            preparedStatement.setInt(9, usuario.getIdCuentaBancaria());

            preparedStatement.execute();

            return get(usuario.getId());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public void delete(int id) {
        Connection conexion = connectionFactory.getConnection();
        String SQL = "DELETE FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Usuario update(Usuario usuario) {
        //CONFIRMAR CAMPO CONTRASEÑA DE LA BD
        Connection conexion = connectionFactory.getConnection();
        String SQL = "UPDATE usuarios SET nombre = ?, apellidos = ?, email = ?, direccion = ?, telefono = ?, contrasenya = ?, rol = ?, idCuentaBancaria = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getDireccion());
            preparedStatement.setString(5, usuario.getTelefono());
            preparedStatement.setString(6, usuario.getContrasenya());
            preparedStatement.setString(7, usuario.getRol().toString());
            preparedStatement.setInt(8, usuario.getIdCuentaBancaria());
            preparedStatement.setInt(9, usuario.getId());
            
            preparedStatement.execute();
          
            return get(usuario.getId());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<Usuario> findAll() {
        Connection conexion = connectionFactory.getConnection();
        List<Usuario> usuarios = new ArrayList <>();
        String SQL = "SELECT * FROM usuarios";
        
        try {
             PreparedStatement preparedStatement = conexion.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                
                usuario.setId(resultSet.getInt(1));
                usuario.setNombre(resultSet.getString(2));
                usuario.setApellidos(resultSet.getString(3));
                usuario.setEmail(resultSet.getString(4));
                usuario.setDireccion(resultSet.getString(5));
                usuario.setTelefono(resultSet.getString(6));
                usuario.setContrasenya(resultSet.getString(7));
                //usuario.setRol(resultSet.getString(8));
                usuario.setIdCuentaBancaria(resultSet.getInt(9));
                
                usuarios.add(usuario);
            }
            
            return usuarios;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Usuario findByCorreo(String email) {
        Connection conexion = connectionFactory.getConnection();
        Usuario usuario;
        String SQL = "SELECT id FROM usuarios WHERE email = ?";
        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(SQL);

            preparedStatement.setString(1, email);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            usuario = get(resultSet.getInt(1));
           
            return usuario;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
              throw new RuntimeException(ex);  
            }
        }
    }
}
