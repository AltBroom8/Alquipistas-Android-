package com.example.alquipistas;

import java.sql.Connection;

public class Consultor {

    MysqlConnector conector;
    Connection conexion;
    public Consultor(){
        conector = new MysqlConnector();
        conexion = conector.getConnexion();
    }
}
