package com.rnk.design_patterns.singleton;

import com.rnk.design_patterns.singleton.formatter.Formatter;

import java.time.Instant;

public class Logger {
    private static volatile Logger logger;
    private LogLevel logLevel;
    private Formatter formatter;

    private Logger(LogLevel level, Formatter formatter) {
        // Prevent instantiation via reflection
        if(logger != null){
            throw new IllegalStateException("Instance already created");
        }
        this.logLevel = level;
        this.formatter = formatter;
    }

    public static Logger getInstance(LogLevel level, Formatter formatter){
        if(logger == null) {
            synchronized (Logger.class){
                if(logger == null){
                    logger = new Logger(level, formatter);
                }
            }
        }
        return logger;
    }

    public String log(String message){
        String formatted = formatter.format(this.logLevel, message, Instant.now());
        return formatted;
    }

    public void setLoggerLevel(LogLevel level){
        if(level == null)
            throw new IllegalArgumentException("Log level cannot be null");
        this.logLevel = level;
    }

    public LogLevel getLoggerLevel() {
        return logLevel;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        if(formatter == null)
            throw new IllegalArgumentException(" Formatter cannot be null");
        this.formatter = formatter;
    }
}
