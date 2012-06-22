package com.ftwdev.stuffinc.core;

import java.util.Random;

public class Battle {
	private Collector collectorOne;
	private Collector collectorTwo;
	private Thing activeThingOne;
	private Thing activeThingTwo;

	private Thing winner;
	
	//round
	//battle
	//match

	public Battle(Collector collectorOne, Collector collectorTwo) {
		this.collectorOne = collectorOne;
		this.collectorTwo = collectorTwo;
		activeThingOne = collectorOne.getPocket().get(0);
		activeThingTwo = collectorTwo.getPocket().get(0);
	}

	public Battle(Thing activeThingOne, Thing activeThingTwo) {
		this.activeThingOne = activeThingOne;
		this.activeThingTwo = activeThingTwo;
	}

	public boolean round(Move activeThingOneAttack) {
		Move activeThingTwoAttack = generateAiAttack();
		return round(activeThingOneAttack, activeThingTwoAttack);
	}

	private Move generateAiAttack() {
		Move activeThingTwoAttack = activeThingTwo.getMoveSet().get(0);
		return activeThingTwoAttack;
	}

	/** returns true if Thing is successfully set active **/
	public boolean setActiveThingOne(Thing thing){
		activeThingOne = thing;
		return true;
	}
	
	public boolean round(Move activeThingOneAttack, Move activeThingTwoAttack) {
		int damageToPlayerTwo = attack(activeThingOne, activeThingTwo, activeThingOneAttack,
				activeThingTwoAttack);
		int damageToPlayerOne = attack(activeThingTwo, activeThingOne, activeThingTwoAttack,
				activeThingOneAttack);
		activeThingTwo.setCurrentHealth(activeThingTwo.getCurrentHealth()
				- damageToPlayerTwo);
		activeThingOne.setCurrentHealth(activeThingOne.getCurrentHealth()
				- damageToPlayerOne);
		if (isComplete()) {
			setWinner();
			return true;
		}
		return false;
	}

	private void setWinner() {
		if (activeThingOne.getCurrentHealth() > 0) {
			winner = activeThingOne;
		} else if (activeThingTwo.getCurrentHealth() > 0) {
			winner = activeThingTwo;
		} else {
			// handle condition for draw
		}
	}

	public Thing getWinner() {
		return winner;
	}

	public boolean isComplete() {
		if (activeThingOne.getCurrentHealth() <= 0
				|| activeThingTwo.getCurrentHealth() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int getBaseDmg(Thing thing, Thing thing2, Move activeThingOneAttack) {
		switch (activeThingOneAttack.getMoveType()) {
		case PHYSICAL:
			return (int) ((thing.getForce() - thing2.getHardiness() / 2) * activeThingOneAttack
					.getPowerModifier());
		case EMOTIONAL:
			return (int) ((thing.getInsight() - thing2.getWillpower() / 2) * activeThingOneAttack
					.getPowerModifier());
		}
		return -1;
	}

	/**
	 * calculates how many attacks a Thing gets in with a particular move
	 * 
	 * @param activeThingOne
	 *            - first Thing who's fighting
	 * @param activeThingTwo
	 *            - second Thing fighting
	 * @param activeThingOneAttack
	 *            - first Thing's move
	 * @param activeThingTwoAttack
	 *            - second Thing's move
	 * @return 0 < 1 < n (number of moves)
	 */
	public static float getNumAttacks(Thing activeThingOne, Thing activeThingTwo,
			Move activeThingOneAttack, Move activeThingTwoAttack) {
		return (activeThingOne.getMobility() * activeThingOneAttack.getSpeedModifier())
				/ (activeThingTwo.getMobility() * activeThingTwoAttack.getSpeedModifier());
	}

	/**
	 * returns negative if you go second, positive if you go fist
	 * 
	 * @param activeThingOne
	 * @param activeThingTwo
	 * @param activeThingOneAttack
	 * @param activeThingTwoAttack
	 * @return
	 */
	public static int attack(Thing activeThingOne, Thing activeThingTwo,
			Move activeThingOneAttack, Move activeThingTwoAttack) {
		// int total = 0;

		// Random rand = new Random();

		int dmg = getBaseDmg(activeThingOne, activeThingTwo, activeThingOneAttack);
		return dmg;
		/*
		 * float speediness = getNumAttacks(activeThingOne, activeThingTwo,
		 * activeThingOneAttack, activeThingTwoAttack); int num_moves = 1; if((int)
		 * speediness != 0) num_moves = (int) speediness; for (int i=0;
		 * i<num_moves; i++){ if (rand.nextInt(10) == 1) total+= dmg*2; else
		 * total+= dmg; } if(speediness < 1) return total*-1; else return total;
		 */
	}
}
