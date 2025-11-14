package com.rnk.design_patterns.singleton.formatter;

import com.rnk.design_patterns.singleton.LogLevel;

import java.time.Instant;

public interface Formatter {
    String format(LogLevel level, String message, Instant instant);
}
