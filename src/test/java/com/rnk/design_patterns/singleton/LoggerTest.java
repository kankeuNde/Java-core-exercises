package com.rnk.design_patterns.singleton;

import com.rnk.design_patterns.singleton.formatter.Formatter;
import com.rnk.design_patterns.singleton.formatter.JsonFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    //private final static Formatter formatter = new PlainTextFormatter();
    private final static Formatter formatter = new JsonFormatter();
    private final static LogLevel level = LogLevel.INFO;


    private Logger logger;
    private static Logger logger1;

    @BeforeAll
    public static void globalSetup(){
        logger1 = Logger.getInstance(level, formatter);
    }

    @BeforeEach
    public void setUp(){
        logger = Logger.getInstance(level, formatter);
    }

    @Test
    public void testgetInstanceLogger_should_return_same_object(){
        int id = logger.hashCode();
        logger = logger.getInstance(level, formatter);
        int id2 = logger.hashCode();
        assertEquals(id, id2);
    }

    @Test
    public void testGetInstanceLoggerMultiThreaded_ShouldReturnSameObject() throws InterruptedException {
        final int threadCount = 10;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        final Logger[] instances = new Logger[threadCount];

        for(int i = 0; i<threadCount; i++){
            final int index = i;
            new Thread(
                    () -> {
                        instances[index] = Logger.getInstance(level, formatter);
                        latch.countDown();
                    }
            ).start();
        }

        // Wait for all threads to finish
        latch.await();

        // Assert all instances are same as the first instance
        for(int i=1; i<threadCount; i++){
            assertSame(instances[i-1], instances[i], "Instances should be the same");
        }
    }

    @Test
    public void testLog_shouldContainTheLoggedMessage(){
        Logger logger = Logger.getInstance(level, formatter);
        String message = "Test message";
        String regex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        String output = logger.log(message);
        Matcher matcher = pattern.matcher(output);
        // Check output contains the message
        assertTrue(output.contains(message), "Log output should contain the original message");
        // Check output contains a timestamp-like pattern
        assertTrue(matcher.find());
    }

    @Test
    public void testReflectionAttackPrevention_ThrowsException() throws Exception {
        Logger instance1 = Logger.getInstance(level, formatter);
        Constructor<Logger> constructor = Logger.class.getDeclaredConstructor(LogLevel.class, Formatter.class);
        constructor.setAccessible(true); // bypass private constructor

        //The second creation attempt via reflection should throw IllegalStateException
        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance(level, formatter);
        });

        //Assert that the real cause is IllegalStateException
        Throwable cause = thrown.getCause();
        assert(cause instanceof IllegalStateException);
    }

    @Test
    public void testSetFormatterOrLoggerLevelWithNullLogger_ShouldReturnException(){
        Logger logger = null;
        assertThrows(NullPointerException.class, ()->{
            logger.setLoggerLevel(LogLevel.INFO);
        });
    }


}
