package com.ftwdev.stuffinc.core;

import java.util.HashMap;

public class Stuff {
	private static HashMap<String, Stuff> stuffList;
	private String name;
	private StuffType type;
	
	public Stuff() {
		this.name = "hi";
		this.type = StuffType.FURNITURE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StuffType getType() {
		return type;
	}

	public void setType(StuffType type) {
		this.type = type;
	}

	public static HashMap<String, Stuff> getStuffList() {
		return stuffList;
	}
}
