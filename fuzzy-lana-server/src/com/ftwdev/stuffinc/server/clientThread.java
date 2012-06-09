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
    	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));
 
        String inputLine, outputLine;
        stuffProtocol conversation = new stuffProtocol();
        outputLine = conversation.processInput(null);
        out.println(outputLine);
 
        while ((inputLine = in.readLine()) != null) {
        outputLine = conversation.processInput(inputLine);
        out.println(outputLine);
        if (outputLine.equals("Bye"))
            break;
        }
        out.close();
        in.close();
        socket.close();

    }
}