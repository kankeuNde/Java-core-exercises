package com.rnk.refactoring_optimisation.util;

public interface CustomLogger {
    void info(String msg);
    void warn(String msg);
    void error(String msg, Throwable t);
}
