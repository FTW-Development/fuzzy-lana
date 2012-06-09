package com.ftwdev.stuffinc.server;

import java.net.Socket;

public class StuffIncServer {
    public static void main(String[] args){

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
            System.ext(-1);
        }
    }
}
