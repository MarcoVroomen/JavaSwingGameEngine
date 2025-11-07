package Engine;

public class Logger {
    public enum LogLevel {
        ERROR(0),
        WARNING(1),
        LOG(2),
        DETAIL(3);

        private final int level;
        LogLevel(int level) {
            this.level = level;
        }
        public int getLevel() {
            return level;
        }
    }

    private static LogLevel currentLevel = LogLevel.LOG;

    // Setzt das aktive Loglevel
    public static void setLogLevel(LogLevel level) {
        currentLevel = level;
    }

    // Gibt das aktuelle Loglevel zurück
    public static LogLevel getLogLevel() {
        return currentLevel;
    }

    // Loggt eine Nachricht mit dem angegebenen Loglevel
    public static void log(LogLevel level, String message) {
        if (level.getLevel() <= currentLevel.getLevel()) {
            String prefix;
            switch (level) {
                case ERROR -> prefix = "[ERROR] ";
                case WARNING -> prefix = "[WARNING] ";
                case DETAIL -> prefix = "[DETAIL] ";
                default -> prefix = "[LOG] ";
            }
            System.out.println(prefix + message);
        }
    }

    // Komfort-Methoden für direkte Aufrufe
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public static void info(String message) {
        log(LogLevel.LOG, message);
    }

    public static void detail(String message) {
        log(LogLevel.DETAIL, message);
    }
}