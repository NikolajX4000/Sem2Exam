package storageLayer;

import logicLayer.CustomException;
import logicLayer.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logicLayer.Log;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author super
 */
public class EmployeeMapper {

    /**
     * Add employee.
     * This method calls the database with a prepared statement to 
     * request an insert to ad an employee into the employees table.
     * 
     * @param name The username. Should not be null.
     * @param password The password. Should not be null.
     * @return A employee object with the name and password.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Employee addEmployee(String name, String password) throws CustomException {
        PreparedStatement ps = null;
        Employee e = new Employee(name, password);
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO employees ( name, password) VALUES (?, ?)";

            ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, password);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.first()) {
                e.setId(rs.getInt(1));
                return e;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente information" );
        } finally {
            closeStatement(ps);
        }
        throw new CustomException( "Kunne ikke hente information" );
    }

    /**
     * Login.
     * This method calls the database with a prepared statement to 
     * request an employee from the employees table, with the name and password.
     * 
     * @param name The username. Should not be null.
     * @param password The password. Should not be null.
     * @return A employee object with the name and password.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Employee login(String name, String password) throws CustomException {

        try {

            Connection con = Connector.connection();
            String SQL = "SELECT * FROM employees WHERE name = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                name = rs.getString("name");
                String hashedPW = rs.getString("password");
                int id = rs.getInt("employee_id");
                if (BCrypt.checkpw(password, hashedPW)) {
                    return new Employee(name, hashedPW, id);

                } else {
                    throw new CustomException("Forkert password");
                }

            }

            closeStatement(ps);

        } catch (ClassNotFoundException | SQLException ex) {
            Log.severe(ex);
            throw new CustomException("Formateringsfejl");
        }

        throw new CustomException("Forkert informatation");
    }

    /**
     * This method will close the prepared statement connection if it's
     * connection, the reason is to reduce as musch in- and outgoing trafic from
     * the server.
     *
     * @param ps PreparedStatement object, the SQL controller.
     */
    private static void closeStatement(PreparedStatement ps) throws CustomException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Log.severe(ex);
                throw new CustomException( "Kunne ikke få kontakt til databasen" );
            }
        }
    }
}
