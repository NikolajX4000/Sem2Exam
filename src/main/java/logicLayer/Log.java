package logicLayer;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static final String FILENAME = "sql.log";
    private static final String FILEPATH = "/home/daniel/carportlogs/";
    private static final String PATH = FILEPATH + FILENAME;
    private static Logger log = null;

    private Log() {
    }

    private static Logger initLogger() throws Exception {

        Logger tmplog = Logger.getLogger(Log.class.getName());
        FileHandler fh = new FileHandler(PATH, true);
        fh.setFormatter(new SimpleFormatter());
        tmplog.addHandler(fh);
        return tmplog;

    }

    private static void log(Level lvl, Throwable t) throws Exception {

        if (log == null) {
            log = initLogger();
        }

        log.log(lvl, t.getMessage());

    }

    public static void severe(Throwable t) {

        try {
            log(Level.SEVERE, t);
        } catch (Exception ex) {
        }
    }
}
