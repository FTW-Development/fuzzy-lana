package com.ftwdev.stuffinc.core;

<<<<<<< HEAD
import java.io.File;
=======
>>>>>>> branch 'master' of https://github.com/FTW-Development/fuzzy-lana.git
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class StuffFactory {
	static 
	{		
		try {
			Move.initMoveList(new FileInputStream("test/assets/Moves.yml"));
			Stuff.initStuffList(new FileInputStream("test/assets/Stuff.yml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String[] STARTERS = {"Explosive Soda Can", "Playful Pogo", "Three-Legged Stepstool"};
	private static final String[] BASE = {"Dual Daisies", "Timed-out Meter", "Desk Chair", "Three-Legged Stepstool", "Explosive Soda Can", "Cutthroat Cutlery", "Playful Pogo", "Old Tenspeed", "Hammer", "Rusty Rake", "Silly Spade", "Rex Lunchbox"};

	//private static final String[] STARTERS = {"stuff1", "stuff2"};
	private static final Random random = new Random();
	
	public static Thing getStarter() {
		Random random = new Random();
		Stuff stuff = Stuff.getStuffList().get(STARTERS[random.nextInt(STARTERS.length)]);
		Thing starter = new Thing(stuff);
		starter.levelUp();
		setMoves(starter, stuff);
		return starter;
	}
	
	public static Thing getWild(int rarity, int level) {
		Random random = new Random();
		Stuff stuff = Stuff.getStuffList().get(BASE[random.nextInt(BASE.length)]);
		
		//level up
		Thing wild = new Thing(stuff);
		for(int i=0; i<level; i++){
			wild.levelUp();
			if (wild.getEvolveLevel() <= wild.getLevel()){
				wild.evolve();
			}
		}
		
		setMoves(wild, stuff);
		return wild;
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
