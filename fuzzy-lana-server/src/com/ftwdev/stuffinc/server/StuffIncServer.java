package com.ftwdev.stuffinc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StuffIncServer {
    public static void main(String[] args){
    
        ServerSocket stuffServer = null;
        boolean listening = true;
    	//sockets for accepting connections
        try {
            stuffServer = new ServerSocket(12345);
        }  catch (IOException e){
            System.out.println("Could not bind to port 12345");
            System.exit(-1);
        }
        Socket clientSocket = null;
        while(listening)
        new clientThread(serverSocket.accept()).start();

        serverSocket.close();
    }
}
