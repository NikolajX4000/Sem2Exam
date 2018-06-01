package logicLayer;

/**
 * Thrown whenever we catch another exception. CustomException has the message we wan't to have displayed to the user
 * @author super
 */
public class CustomException extends Exception {

    /**
     *
     * @param msg you want displayed to the user
     */
    public CustomException(String msg) {
        super(msg);
    }
}
