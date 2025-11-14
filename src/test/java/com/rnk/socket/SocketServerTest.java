package com.rnk.socket;

import com.rnk.socket.client.GreetClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocketServerTest {

    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect(){
        GreetClient client = new GreetClient();
        client.startConnection("127.0.0.1", 12345);
        String res = client.sendMessage("hello server");
        assertEquals("hello client", res);
        client.stopConnection();
    }
}
