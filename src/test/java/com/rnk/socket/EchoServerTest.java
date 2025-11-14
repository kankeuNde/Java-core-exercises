package com.rnk.socket;

import com.rnk.socket.client.EchoClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoServerTest {
    private static EchoClient client;

    @BeforeAll
    public static void setup(){
        client = new EchoClient();
        client.startConnection("127.0.0.1", 4444);
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        String resp3 = client.sendMessage("!");
        String resp4 = client.sendMessage(".");

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("!", resp3);
        assertEquals("good bye", resp4);
    }



    @AfterAll
    public static void tearDown(){
        client.stopConnection();
    }



}
