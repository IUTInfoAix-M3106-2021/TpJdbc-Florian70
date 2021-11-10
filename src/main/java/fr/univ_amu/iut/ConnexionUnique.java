package fr.univ_amu.iut;

import java.sql.*;

public class ConnexionUnique{
    private Connection connection;
    private static ConnexionUnique instance;

    static private final String CONNECT_URL = "jdbc:mysql://localhost:3306/GestionPedaBD";
    static private final String LOGIN = "monUser";
    static private final String PASSWORD = "monPassword";

    private ConnexionUnique() throws SQLException {
        connection = DriverManager.getConnection(CONNECT_URL, LOGIN, PASSWORD);
    }

    public Connection getConnection(){
        return this.connection;
    }

    public static ConnexionUnique getInstance() throws SQLException{
        if (instance == null) {
           instance = new ConnexionUnique();  
        }
         return instance;
    }


}





