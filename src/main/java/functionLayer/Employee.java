package functionLayer;

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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
