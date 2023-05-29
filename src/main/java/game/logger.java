package game;

import java.util.logging.*;

public class logger {
    private static final Logger logger = Logger.getLogger(logger.class.getName());
    private static final Level DEFAULT_LOG_LEVEL;

    static {
        DEFAULT_LOG_LEVEL=  game.deafaultLoger;
        // Set up the logger configuration
        logger.setLevel(DEFAULT_LOG_LEVEL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(DEFAULT_LOG_LEVEL);
        logger.addHandler(consoleHandler);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }

    public static void logError(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
    public static void logFine(String message) {
        logger.fine(message);
    }
    public static void logFiner(String message) {
        logger.finer(message);
    }
}
