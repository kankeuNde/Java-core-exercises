package com.rnk.design_patterns.singleton;

import java.time.Instant;

public class Logger {
    private static volatile Logger logger;

    private Logger() {
        // Prevent instantiation via reflection
        if(logger != null){
            throw new IllegalStateException("Instance already created");
        }
    }

    public static Logger getInstance(){
        if(logger == null) {
            synchronized ("logger"){
                if(logger == null){
                    logger = new Logger();
                }
            }
        }
        return logger;
    }

    public void log(String message){
        System.out.println(String.format("%s - %s", Instant.now(), message));
    }
}
