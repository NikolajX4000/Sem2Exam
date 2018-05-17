package storageLayer;

import functionLayer.CustomException;
import functionLayer.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author super
 */
public class EmployeeMapper
{

    public static Employee addEmployee(String name, String password) throws CustomException
    {
        PreparedStatement ps = null;
        Employee e = new Employee(name, password);
        try
        {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO employees ( name, password) VALUES (?, ?)";

            ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            try
            {
                ps.setString(1, name);
                ps.setString(2, password);

            } catch (SQLException ex)
            {
                throw new CustomException("Formateringsfejl");
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.first())
            {
                e.setId(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new CustomException(ex.getMessage());
        } finally
        {
            closeConnection(ps);
        }
        return e;
    }

    public static Employee login(String name, String password) throws CustomException
    {

        try
        {

            Connection con = Connector.connection();
            String SQL = "SELECT * FROM employees WHERE name = ?";
            
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            

            ResultSet rs = ps.executeQuery();

            if (rs.first())
            {
                if (BCrypt.checkpw(password, rs.getString("password")))
                {
                    return new Employee(rs.getString("name"), rs.getString("password"), rs.getInt("employee_id"));

                } else
                {
                    throw new CustomException("Forkert password");
                }

            }

            closeConnection(ps);

        } catch (Exception e)
        {
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
    private static void closeConnection(PreparedStatement ps)
    {
        if (ps != null)
        {
            try
            {
                ps.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(OrderMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
