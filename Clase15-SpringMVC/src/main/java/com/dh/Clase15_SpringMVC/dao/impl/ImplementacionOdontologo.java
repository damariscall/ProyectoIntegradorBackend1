package com.dh.Clase15_SpringMVC.dao.impl;

import com.dh.Clase15_SpringMVC.dao.BD;
import com.dh.Clase15_SpringMVC.dao.IDAO;
import com.dh.Clase15_SpringMVC.modelo.Odontologo;
import com.dh.Clase15_SpringMVC.modelo.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionOdontologo implements IDAO<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(ImplementacionPaciente.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;

        try {
            LOGGER.info("Estamos guardando un odontologo");

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ODONTOLOGOS (" +
                            "NOMBRE, APELLIDO, MATRICULA) VALUES " +
                            "(?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());

            preparedStatement.execute();

            //guardé el odontologo y se generó el id

            ResultSet rs = preparedStatement.getGeneratedKeys();


            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
                System.out.println("Se guardó el odontologo con nombre " +
                        odontologo.getNombre());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        LOGGER.info("Guardamos el odontologo con nombre " + odontologo.getNombre());
        return odontologo;

    }

    @Override
    public List<Odontologo> listarTodos() {

        Connection connection = null;

        List<Odontologo> odontologoList = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ODONTOLOGOS"
            );

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));

                odontologoList.add(odontologo);

                System.out.println(odontologo.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return odontologoList;
    }

    @Override
    public Odontologo consultarPorId(Integer id) {

        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = BD.getConnection();

            PreparedStatement psBuscarPorId = connection.prepareStatement(
                            "SELECT * FROM ODONTOLOGOS WHERE ID=?"
                    );
            psBuscarPorId.setInt(1, id);
            ResultSet rs = psBuscarPorId.executeQuery();

            while (rs.next()) {
                odontologo = new Odontologo();
                odontologo.setId(rs.getInt(1));
                odontologo.setNombre(rs.getString(2));
                odontologo.setApellido(rs.getString(3));
                odontologo.setMatricula(rs.getString(4));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return odontologo;

    }

    @Override
    public void eliminarPorId(Integer id) {
        Connection connection = null;


        try {
            connection = BD.getConnection();

            PreparedStatement psEliminarPorId = connection.prepareStatement(
                    "DELET * FROM PACIENTES WHERE ID=?");
            psEliminarPorId.setInt(1, id);

            psEliminarPorId.executeLargeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }



    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=?," +
                            "WHERE ID=?"
            );

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.setInt(6, odontologo.getId());

            preparedStatement.execute();

            System.out.println("Este es el nuevo nombre del odontologo" + odontologo.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return odontologo;

    }
}
