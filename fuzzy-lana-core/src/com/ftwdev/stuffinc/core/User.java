package com.ftwdev.stuffinc.core;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable{
	private static final long serialVersionUID = -2081527497691517991L;
	
	private String username;
	private byte[] password;
	private Map<Integer, Thing> myStuff = new HashMap<Integer, Thing>();
	
	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public void setPassword(byte[] password) {
		this.password = password;
	}
	
	public byte[] getPassword() {
		return password;
	}

	public byte[] hashPassword(String password) {
		try {
			return MessageDigest.getInstance("MD5").digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			//should not fail
			return null;
		}
	}
}
