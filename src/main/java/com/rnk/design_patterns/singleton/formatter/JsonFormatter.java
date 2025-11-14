package com.rnk.design_patterns.singleton.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rnk.design_patterns.singleton.LogLevel;

import java.time.Instant;

public class JsonFormatter implements Formatter{

    @Override
    public String format(LogLevel level, String message, Instant timeStamp) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        LogEntry logEntry = new LogEntry(level, timeStamp, message);
        String json;
        try {
            json = mapper.writeValueAsString(logEntry);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
