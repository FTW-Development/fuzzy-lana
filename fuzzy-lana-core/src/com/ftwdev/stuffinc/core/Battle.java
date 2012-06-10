package com.ftwdev.stuffinc.core;

import java.util.Random;

public class Battle {

	public static int getBaseDmg(Thing thing, Thing thing2, Move falconPunch){
		switch(falconPunch.getMoveType()){
		case PHYSICAL:
			return (int) ((thing.getForce()-thing2.getHardiness()/2)*falconPunch.getPowerModifier());
		case EMOTIONAL:
			return (int) ((thing.getInsight()-thing2.getWillpower()/2)*falconPunch.getPowerModifier());
		}
		return -1;
	}
	
	/**
	 * calculates how many attacks a Thing gets in with a particular move
	 * @param falcon - first Thing who's fighting
	 * @param dancer - second Thing fighting
	 * @param falconPunch - first Thing's move
	 * @param electricSlide - second Thing's move
	 * @return 0 < 1 < n (number of moves)
	 */
	public static float getNumAttacks(Thing falcon, Thing dancer, Move falconPunch, Move electricSlide){
		return (falcon.getMobility()*falconPunch.getSpeedModifier())/(dancer.getMobility()*electricSlide.getSpeedModifier());
	}	
	
	/**
	 * returns negative if you go second, positive if you go fist
	 * @param falcon
	 * @param dancer
	 * @param falconPunch
	 * @param electricSlide
	 * @return
	 */
	public static int attack(Thing falcon, Thing dancer, Move falconPunch, Move electricSlide){
		int total = 0;
		
		Random rand = new Random();
		
		int dmg = getBaseDmg(falcon, dancer, falconPunch);
		float speediness = getNumAttacks(falcon, dancer, falconPunch, electricSlide);
		int num_moves = 1;
		if((int) speediness != 0)
			num_moves = (int) speediness;
		for (int i=0; i<num_moves; i++){
			if (rand.nextInt(10) == 1)
				total+= dmg*2;
			else
				total+= dmg;
		}
		if(speediness < 1)
			return total*-1;
		else
			return total;
	}
}
