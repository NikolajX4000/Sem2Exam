package logicLayer;

/**
 *
 * @author super
 */
public class Employee {

    private int id;
    private String name;
    private String password;

    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Employee(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString()
    {
        return "Employee{" + "id=" + id + ", name=" + name + ", password=" + password + '}';
    }
    
}
