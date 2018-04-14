package storageLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author super
 */
public class Connector {

    private static final String IP = "";
    private static final String PORT = "3306";
    private static final String DATABASE = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?autoReconnect=true&useSSL=false";
    private static Connection connection;

    /**
     *
     * @param con
     */
    public static void setConnection(Connection con) {
        connection = con;
    }

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection connection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
