package com.ftwdev.stuffinc.server;


import java.net.Socket;
import java.io.*;

import com.ftwdev.stuffinc.network.StuffedPacket;

public class clientThread extends Thread {
    private Socket socket = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private RetardedRouter rr = null;
    
    public clientThread(Socket socket, RetardedRouter myRouter){
    	super("clientThread");
    	this.socket = socket;
    	this.rr = myRouter;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            //out = new ObjectOutputStream(socket.getOutputStream());
           } catch(Exception e1) {
               try {
                  socket.close();
               }catch(Exception e) {
                 System.out.println(e.getMessage());
               }
               return;
           }
           this.start();
    }
    
    public void run() {
    	System.out.println("Accepted connection from: " + this.socket.getInetAddress());
  
        //process messages
		//while (true) {
			try {
				StuffedPacket response = rr.processInput((StuffedPacket) in.readObject());
				//out.writeObject(response);
				//if(response.message.equals("Bye"))
					//break;
				
			} catch (IOException e) {
				System.out.println("we weren't able to read or write something");
				e.printStackTrace();
			}  catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
        
        //shut down
        try {
            out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
            System.out.println("Cleaned up messily:");
			e.printStackTrace();
		}
    }
}