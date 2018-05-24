package logicLayer;

/**
 *
 * @author super
 */
public class Roof {

    private final int ID;
    private final String NAME;
    private final int TYPE;

    public Roof(int id, String name, int type) {
        this.ID = id;
        this.NAME = name;
        this.TYPE = type;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public int getTYPE() {
        return TYPE;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
