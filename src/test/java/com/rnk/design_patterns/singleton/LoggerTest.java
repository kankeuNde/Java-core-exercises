package com.rnk.design_patterns.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    private  final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Logger logger;
    private static Logger logger1;

    @BeforeAll
    public static void globalSetup(){
        logger1 = Logger.getInstance();
    }

    @BeforeEach
    public void setUp(){
        logger = Logger.getInstance();
        // Redirect system.out to outContent to capture output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams(){
        // Restore original System.out after test
        System.setOut(originalOut);
    }

    @Test
    public void testgetInstanceLogger_should_return_same_object(){
        int id = logger.hashCode();
        logger = logger.getInstance();
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
                        instances[index] = Logger.getInstance();
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
        Logger logger = Logger.getInstance();
        String message = "Test message";

        logger.log(message);
        String output = outContent.toString().trim();

        // Check output contains the message
        assertTrue(output.contains(message), "Log output should contain the original message");
        // Check output contains a timestamp-like pattern (ISO-INSTANT format)
        assertTrue(output.matches("^\\d{4}-\\d{2}-\\d{2}T.*"), "Log output should start with a timestamp");
    }

    @Test
    public void testReflectionAttackPrevention_ThrowsException() throws Exception {
        Logger instance1 = Logger.getInstance();
        Constructor<Logger> constructor = Logger.class.getDeclaredConstructor();
        constructor.setAccessible(true); // bypass private constructor

        //The second creation attempt via reflection should throw IllegalStateException
        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        //Assert that the real cause is IllegalStateException
        Throwable cause = thrown.getCause();
        assert(cause instanceof IllegalStateException);
    }
}
