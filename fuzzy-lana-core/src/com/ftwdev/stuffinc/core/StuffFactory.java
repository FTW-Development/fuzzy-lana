package com.ftwdev.stuffinc.core;

import java.util.Random;

public class StuffFactory {
	private static final Random random = new Random();
	private static final String[] STARTERS = {"Explosive Soda Can", "Playful Pogo", "Three-Legged Stepstool"};
	//private static final String[] STARTERS = {"stuff1", "stuff2"};
	public static Thing getStarter() {
		Stuff stuff = Stuff.getStuffList().get(STARTERS[random.nextInt(STARTERS.length)]);
		Thing starter = new Thing(stuff);
		starter.levelUp();
		setMoves(starter, stuff);
		return starter;
	}
	
	private static void setMoves(Thing thing, Stuff stuff) {
		for(int moveLevel : stuff.getMoveList().keySet()) {
			if(moveLevel <= thing.getLevel()) {
				if(thing.getMoveSet().size() >= 4) {
					if(random.nextBoolean()) {
						thing.getMoveSet().add(random.nextInt(4), stuff.getMoveList().get(moveLevel));
					}
				} else {
					thing.getMoveSet().add(stuff.getMoveList().get(moveLevel));
				}
			}
		}
	}
}
