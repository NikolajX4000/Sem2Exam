package logicLayer;

/**
 * A roof has information about a specific roof. id, name, type.
 *
 * @author super
 */
public class Roof {

    private final int ID;
    private final String NAME;
    private final int TYPE;

    /**
     *
     * @param id the id from the database
     * @param name the name of the roof
     * @param type 0 if for carport with flat roof and 1 if for carport with
     * raised roof
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
