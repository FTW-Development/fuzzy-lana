package com.ftwdev.stuffinc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

import com.ftwdev.stuffinc.core.Thing;
import com.ftwdev.stuffinc.core.User;


public class StuffIncServer {
    public static void main(String[] args){
    	
    	//data
    	Map<String, User> users = new HashMap<String, User>();
    	User myUser = new User("testuser");
    	myUser.setPassword(myUser.hashPassword("testing"));
    	users.put(myUser.getUsername(), myUser);
    	
    	Map<Integer, Thing> alldathings = new HashMap<Integer, Thing>();
    	
    	
    	//controllers
    	UserController uc = new UserController(users, alldathings);
    	
    	//router
    	RetardedRouter rr = new RetardedRouter(uc);
    
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
				new clientThread(stuffServer.accept(), rr).start();
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
