package interfazInformes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Rafael Peral Jimenez
 *
 */
public class Conexion {
	/**
	 * En el parametro url guardamos la url de conexion a la base de datos hsql.
	 */
    private static String url = "jdbc:hsqldb:hsql://localhost/testdb";  
    /**
     * En el parametro driverName guardamos la direccion del driver que usamos para conectar con la base de datos.
     */
    private static String driverName = "org.hsqldb.jdbcDriver";
    /**
     * En el parametro username guardamos el nombre de usuario para conectarnos a la base de datos.
     */
    private static String username = "sa";
    /**
     * En el parametro password guardamos la contraseña de usuario para conectarnos a la base de datos.
     */
    private static String password = "";
    /**
     * En el parametro con guardamos la conexion para luego devolverla con un metodo.
     */
    private static Connection con;
    /**
     * 
     * @return Devuelve una conexion de manera estatica para poder llamarla desde otra clase y establecer una conexion.
     */
    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
}
