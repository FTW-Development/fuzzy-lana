package com.ftwdev.stuffinc.core;

import java.util.Random;

public class StuffFactory {

	private static final String[] STARTERS = {"Explosive Soda Can", "Playful Pogo", "Three-Legged Stepstool"};
	//private static final String[] STARTERS = {"stuff1", "stuff2"};
	private static final Random random = new Random();
	
	public static Thing getStarter() {
		Random random = new Random();
		Stuff stuff = Stuff.getStuffList().get(STARTERS[random.nextInt(STARTERS.length)]);
		Thing starter = new Thing(stuff);
		starter.levelUp();
		for(int moveLevel : stuff.getMoveList().keySet()) {
			if(moveLevel <= starter.getLevel()) {
				if(starter.getMoveSet().size() >= 4) {
					starter.getMoveSet().add(random.nextInt(4), stuff.getMoveList().get(moveLevel));
				} else {
					starter.getMoveSet().add(stuff.getMoveList().get(moveLevel));
				}
			}
		}
		return starter;
	}
	
	public static Thing getWild(int rarity, int level) {
		Random random = new Random();
		Stuff stuff = Stuff.getStuffList().get();
		
		//level up
		Thing wild = new Thing(stuff);
		for(int i; i<level; i++){
			wild.levelUp();
		}
		
		
		//get moves
		for(int moveLevel : stuff.getMoveList().keySet()) {
			if(moveLevel <= wild.getLevel()) {
				if(wild.getMoveSet().size() >= 4) {
					wild.getMoveSet().add(random.nextInt(4), stuff.getMoveList().get(moveLevel));
				} else {
					wild.getMoveSet().add(stuff.getMoveList().get(moveLevel));
				}
			}
		}
		return wild;
	}
}
