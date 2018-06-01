package storageLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This connector class generates or returns our current connection.
 * @author super
 */
public class Connector {

    private static final String IP = "159.89.9.144";
    private static final String PORT = "3306";
    private static final String DATABASE = "sem2exam";
    private static final String USERNAME = "Fog";
    private static final String PASSWORD = "password123";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?autoReconnect=true&useSSL=false";
    private static Connection connection;

    private Connector() {
    }

    /**
     * This method sets the connection to the connection from parameter.
     * @param con The connetion. Should not be null.
     */
    public static void setConnection(Connection con) {
        connection = con;
    }

    /**
     * This method return a Connection object. 
     * It is made with a singleton that only initialze the connection if the connection are null.
     * @return Connection. This objects connetion.
     * @throws ClassNotFoundException if class isnt found throw ClassNotFoundException
     * @throws java.sql.SQLException if something goes wrong when trying yo make a new connection.
     */
    public static Connection connection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
