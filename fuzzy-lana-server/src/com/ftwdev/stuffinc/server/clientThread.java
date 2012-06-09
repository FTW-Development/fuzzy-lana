package com.ftwdev.stuffinc.server;


import java.net.*;
import java.io.*;

public class clientThread extends Thread {
    private Socket socket = null;
    
    public clientThread(Socket socket){
    	super("clientThread");
    	this.socket = socket;
    }
    
    public void run() {
    	//readers and writers
    	PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e1) {
            System.out.println("Could not write... dumping:");
			e1.printStackTrace();
		}  	
        BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
            System.out.println("Could not get input... dumping:");
			e.printStackTrace();
		}
 
		
		//load protocol
        String inputLine, outputLine;
        WeakSauceProtocol wsp = new WeakSauceProtocol();
        outputLine = wsp.processInput(null);
        out.println(outputLine);
 
        //process messages
        try {
			while ((inputLine = in.readLine()) != null) {
			outputLine = wsp.processInput(inputLine);
			out.println(outputLine);
			if (outputLine.equals("Bye"))
			    break;
			}
		} catch (IOException e) {
            System.out.println("Could not read input... dumping:");
			e.printStackTrace();
		}
       
        
        
        //shut down
        out.close();
        try {
			in.close();
		} catch (IOException e) {
            System.out.println("Cleaned up messily:");
			e.printStackTrace();
		}
        try {
			socket.close();
		} catch (IOException e) {
            System.out.println("Sockets couldn't close:");
			e.printStackTrace();
		}

    }
}