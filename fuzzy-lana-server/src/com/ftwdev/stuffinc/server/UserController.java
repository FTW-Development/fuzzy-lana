package com.ftwdev.stuffinc.server;

import java.util.Map;
import com.ftwdev.stuffinc.core.User;
import com.ftwdev.stuffinc.network.StuffedPacket;

public class UserController {

	
	private Map<String, User> users = null;
			
	public UserController(Map<String, User> users){
		this.users = users;
	}
	
	public StuffedPacket authenticate(User u){
		User authed = users.get(u.getUsername());
		if (authed.getPassword() == u.getPassword()){
			return new StuffedPacket("Success", "User", authed);
		}
		return new StuffedPacket("Failure!");
	}
	
}
