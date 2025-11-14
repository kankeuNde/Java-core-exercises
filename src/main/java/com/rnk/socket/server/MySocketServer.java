package com.rnk.socket.server;

import java.net.ServerSocket;

public class MySocketServer {

    private int port;
    private ServerSocket serverSocket;

    public MySocketServer(int port){
        this.port = port;
        //new ServerSocket(port);
    }


}
