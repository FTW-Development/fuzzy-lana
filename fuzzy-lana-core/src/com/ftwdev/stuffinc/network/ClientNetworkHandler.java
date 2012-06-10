package com.ftwdev.stuffinc.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ftwdev.stuffinc.core.User;

public class ClientNetworkHandler {
	private String host;
	private int port;
	
	public ClientNetworkHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void authenticate(User user) {
    	User login_user = user;
    	StuffedPacket auth = new StuffedPacket("AUTHENTICATING", "User", login_user);
    	try {
			Socket socket = new Socket("localhost", 12345);

//	    	ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

	    	ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

	    	os.writeObject(auth);
//	    	try {
//				StuffedPacket return_packet = (StuffedPacket) is.readObject();
//				System.out.println(return_packet.message);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		ClientNetworkHandler cnh = new ClientNetworkHandler("localhost", 12345);
		User user = new User("user");
		user.setPassword(user.hashPassword("password"));
		cnh.authenticate(user);
	}
}
