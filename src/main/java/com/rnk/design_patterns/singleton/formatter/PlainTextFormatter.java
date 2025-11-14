package com.rnk.design_patterns.singleton.formatter;

import com.rnk.design_patterns.singleton.LogLevel;

import java.time.Instant;

public class PlainTextFormatter implements Formatter{
    @Override
    public String format(LogLevel level, String message, Instant instant) {
        return String.format("[%s]-[%s] %s ", level, instant, message);
    }
}
