package com.ftwdev.stuffinc.server;

import java.io.IOException;
import java.net.ServerSocket;

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
        
        while(listening)
			try {
				new clientThread(stuffServer.accept()).start();
			} catch (IOException e1) {
	            System.out.println("Could not accept client connection... dumping:");
				e1.printStackTrace();
			}

        try {
			stuffServer.close();
		} catch (IOException e) {
            System.out.println("Did not shutdown cleanly... dumping:");
			e.printStackTrace();
			System.exit(-1);
		}
    }
}
