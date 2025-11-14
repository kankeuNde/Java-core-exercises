package com.rnk.design_patterns.singleton.formatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rnk.design_patterns.singleton.LogLevel;

import java.time.Instant;

public class LogEntry {
    private LogLevel level;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant timeStamp;
    private String message;


    public LogEntry(LogLevel level, Instant timeStamp, String message) {
        this.level = level;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}
