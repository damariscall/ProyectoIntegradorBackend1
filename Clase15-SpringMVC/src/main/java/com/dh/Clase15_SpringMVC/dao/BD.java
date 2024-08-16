package com.dh.Clase15_SpringMVC.dao;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

    private static final Logger LOGGER = Logger.getLogger(BD.class);

    private static final String DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS PACIENTES; CREATE TABLE " +
            "PACIENTES (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(100) NOT NULL," +
            "APELLIDO VARCHAR(100) NOT NULL," +
            "DOMICILIO VARCHAR(100) NOT NULL," +
            "DNI VARCHAR(100) NOT NULL," +
            "FECHA_ALTA DATE NOT NULL)";

    private static final String DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE " +
            "ODONTOLOGOS (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(100) NOT NULL," +
            "APELLIDO VARCHAR(100) NOT NULL," +
            "MATRICULA VARCHAR(100) NOT NULL)";

    private static final String SQL_INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) " +
            "VALUES ('Daniela', 'Toro', '123')";

    private static final String SQL_INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DOMICILIO, DNI, FECHA_ALTA) " +
            "VALUES ('MARIA', 'PEREZ', 'UNION_53', 'PEM1995', '1990-01-01')";

    public static void createTable() {
        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            LOGGER.info("Estamos creando la tabla pacientes");
            statement.execute(DROP_CREATE_PACIENTES);
            LOGGER.info("Estamos creando la tabla odontologos");
            statement.execute(DROP_CREATE_ODONTOLOGOS);

            LOGGER.info("Estamos insertando un odontologo en la tabla odontologos");
            statement.execute(SQL_INSERT_ODONTOLOGO);
            LOGGER.info("Estamos insertando un paciente en la tabla pacientes");
            statement.execute(SQL_INSERT_PACIENTES);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./20247", "sa", "sa");
    }
}
