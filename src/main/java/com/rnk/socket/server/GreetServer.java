package com.rnk.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            // Accept clients in a new thread to avoid blocking
           /* new Thread(
                    () -> {*/
                        try {
                            clientSocket = serverSocket.accept();
                            out = new PrintWriter(clientSocket.getOutputStream(), true);
                            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            String greeting = in.readLine();
                            if("hello server".equals(greeting)){
                                out.println("hello client");
                            }else{
                                out.println("unrecognized greeting");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {
                            try {
                                stop();
                            } catch (IOException e){
                                throw new RuntimeException(e);
                            }
                        }
                    /*}
            ).start();*/
        } catch (Exception e) {
            throw new RuntimeException("Error starting server", e);
        }

    }

    public void stop() throws IOException {
        if(in != null) in.close();
        if(out != null) out.close();
        if(clientSocket != null) clientSocket.close();
        if(serverSocket != null) serverSocket.close();
    }

    public static void main(String[] args) {
        GreetServer server = new GreetServer();
        server.start(12345);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
