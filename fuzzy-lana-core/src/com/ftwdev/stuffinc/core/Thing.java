package com.ftwdev.stuffinc.core;

public class Thing {
	private Move[] moveSet;
	private Stuff stuff;
	private int level;
	private int exp;
	private int health;
	/* physical strength */
	private int force;
	/* physical defense */
	private int hardiness;
	/* emotional strength */
	private int insight;
	/* emotional defense */
	private int willpower;
	/* speed */
	private int mobility;
	/* awesome attribute */
	private int soul;
	private int id;
	
	public Thing() {
		this.moveSet = new Move[4];
	}
	
	public Thing(int level) {
		
	}
}
