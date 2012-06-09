package com.ftwdev.stuffinc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StuffIncServer {
    public static void main(String[] args){

        ServerSocket stuffServer = null;
		//sockets for accepting connections
        try {
            stuffServer = new ServerSocket(12345);
        }
        catch (IOException e){
            System.out.println("Could not bind to port 12345");
            System.exit(-1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = stuffServer.accept();
        }
        catch (IOException e){
            System.out.println("Could not accept client connections");
            System.exit(-1);
        }
    }
}
