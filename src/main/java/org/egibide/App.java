package org.egibide;

import org.egibide.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        // Intentamos conectar a la BD
        DatabaseConnection dbc = DatabaseConnection.getInstance();
        Connection conexion = dbc.getConnection();

        System.out.println(conexion);
    }
}
