package logicLayer;

/**
 * Employee class containing needed information of set employee
 * @author super
 */
public class Employee {

    private int id;
    private final String name;
    private final String password;

    /**
     *
     * @param name of employee
     * @param password of employee
     */
    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     *
     * @param name of employee
     * @param password of employee
     * @param id of employee
     */
    public Employee(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    /**
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the password of the employee
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return the id of the employee
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id set the employees ID
     * @return this
     */
    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return employee information as a concatenated string
     */
    @Override
    public String toString()
    {
        return "Employee{" + "id=" + id + ", name=" + name + ", password=" + password + '}';
    }
    
}
