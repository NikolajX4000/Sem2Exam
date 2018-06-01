package logicLayer;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logs when and where an exception occurred and saves in "/home/daniel/carportlogs/sql.log"
 * @author Hupra Laptop
 */
public class Log {

    private static final String FILENAME = "mysql.log";
    private static final String FILEPATH = "/home/daniel/carportlogs/";
    private static final String PATH = FILEPATH + FILENAME;

    private Log() {
    }

    private static void log(Level lvl, Throwable t) throws Exception {

        Logger logger = Logger.getLogger(Log.class.getName());
        
        FileHandler fh = new FileHandler(PATH, true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        
        logger.log(lvl, t.getMessage());
        
        fh.close();
        
    }

    /**
     *
     * @param t takes exception and saves the message to file 
     */
    public static void severe(Throwable t) {

        try {
            log(Level.SEVERE, t);
        } catch (Exception ex) {
        }
    }
}
