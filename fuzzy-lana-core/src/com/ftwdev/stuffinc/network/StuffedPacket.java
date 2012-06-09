package com.ftwdev.stuffinc.network;

public class StuffedPacket {

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
