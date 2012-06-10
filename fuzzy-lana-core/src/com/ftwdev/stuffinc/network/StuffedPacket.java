package com.ftwdev.stuffinc.network;

import java.io.Serializable;

public class StuffedPacket implements Serializable{

	private static final long serialVersionUID = -1008945088441472424L;
	
	public String message = null;
	public String payloadType = null;
	public Object payload = null;

	public StuffedPacket(String message){
		this.message = message;
	}
	
	public StuffedPacket(String message, String payloadType, Object payload){
		this.message = message;
		this.payloadType = payloadType;
		this.payload = payload;
	}
	
	
	
}
