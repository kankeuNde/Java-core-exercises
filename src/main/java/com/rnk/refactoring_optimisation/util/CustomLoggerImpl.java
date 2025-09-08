package com.rnk.refactoring_optimisation.util;

import java.util.logging.Logger;

public class CustomLoggerImpl implements CustomLogger{
    private final Logger logger;

    public CustomLoggerImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String msg){
        logger.info(msg);
    }

    @Override
    public void warn(String msg){
        logger.warning(msg);
    }

    @Override
    public void error(String msg, Throwable t){
        logger.severe(msg + ": " +t);
    }
}
