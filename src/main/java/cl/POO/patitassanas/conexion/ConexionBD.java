package cl.POO.patitassanas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {
    private static final String URL = "";
    private static final String USUARIO = "";
    private static final String CLAVE = "";

    private ConexionBD() {

    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,CLAVE);
    }
}
