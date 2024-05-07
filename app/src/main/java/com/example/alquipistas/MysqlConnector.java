package com.example.alquipistas;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {
    private String ip  = "192.168.56.1";
    private String puerto  ="3306";
    private String url = "jdbc:mysql://"+ip+":"+puerto+"/ALQUIPISTAS";
    private String user = "root";
    private String password = "";
    private Connection conexion;

    public MysqlConnector() {
        Log.d("Constructor", "Entrando al constructor");
        conectar();
    }
    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();;
            conexion = DriverManager.getConnection(url, user, password);
            Log.d("Base de datos", "Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            String errorMessage = "Error al conectar con la base de datos: " + e.getMessage();
            Log.d("Error en la Base de datos", errorMessage, e);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                Log.d("Base de datos", "Desconexión exitosa de la base de datos.");
            }
        } catch (SQLException e) {
            String errorMessage = "Error al desconectar con la base de datos: " + e.getMessage();
            Log.d("Error en la Base de datos", errorMessage, e);
        }
    }
    public Connection getConnexion() {
        return conexion;
    }



}
