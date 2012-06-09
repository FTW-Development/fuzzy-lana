package com.ftwdev.stuffinc.server;

import com.ftwdev.stuffinc.core.User;
import com.ftwdev.stuffinc.network.StuffedPacket;

public class RetardedRouter {
    private static final int WAITING = 0;
    
    
    private int state = WAITING; 
    
    
    //controllers
    UserController uc = null;
    
    
    public RetardedRouter(UserController uc){
    	this.uc = uc;
    }
    
    public StuffedPacket processInput(StuffedPacket in) {
    	StuffedPacket out = null;
    	
        if (state == WAITING) {
        	if ((in.message).equals("AUTHENTICATING")){
        		out = uc.authenticate((User) in.payload);
        	}
        } 
        return out;
    }
}
