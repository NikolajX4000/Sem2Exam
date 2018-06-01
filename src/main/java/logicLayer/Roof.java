package logicLayer;

/**
 *
 * @author super
 */
public class Roof {

    private final int ID;
    private final String NAME;
    private final int TYPE;

    /**
     *
     * @param id
     * @param name
     * @param type
     */
    public Roof(int id, String name, int type) {
        this.ID = id;
        this.NAME = name;
        this.TYPE = type;
    }

    /**
     *
     * @return roof ID
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @return roof NAME
     */
    public String getNAME() {
        return NAME;
    }

    /**
     *
     * @return roof TYPE
     */
    public int getTYPE() {
        return TYPE;
    }

    /**
     *
     * @return name of the roof
     */
    @Override
    public String toString() {
        return NAME;
    }
}
