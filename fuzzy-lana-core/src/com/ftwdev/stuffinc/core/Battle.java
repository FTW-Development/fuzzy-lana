package com.ftwdev.stuffinc.core;

import java.util.Random;

public class Battle {
	private Thing playerOne;
	private Thing playerTwo;
	private Thing winner;
	
	public Battle(Thing playerOne, Thing playerTwo){
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public boolean round(Move playerOneAttack, Move playerTwoAttack){
		int damageToPlayerTwo = attack(playerOne, playerTwo, playerOneAttack, playerTwoAttack);
		int damageToPlayerOne = attack(playerTwo, playerOne, playerTwoAttack, playerOneAttack);
		playerTwo.setCurrentHealth(playerTwo.getCurrentHealth() - damageToPlayerTwo);
		playerOne.setCurrentHealth(playerOne.getCurrentHealth() - damageToPlayerOne);
		if (isComplete()){
			setWinner();
			return true;
		}
		return false;
	}
	

	private void setWinner() {
		if (playerOne.getCurrentHealth() > 0){
			winner = playerOne;
		} else if (playerTwo.getCurrentHealth() > 0) {
			winner = playerTwo;
		} else {
			//handle condition for draw
		}		
	}
	
	public Thing getWinner() {
		return winner;
	}

	public boolean isComplete() {
		if (playerOne.getCurrentHealth() <= 0 || playerTwo.getCurrentHealth() <= 0){
			return true;
		} else{
			return false;
		}
	}

	public static int getBaseDmg(Thing thing, Thing thing2, Move playerOneAttack){
		switch(playerOneAttack.getMoveType()){
		case PHYSICAL:
			return (int) ((thing.getForce()-thing2.getHardiness()/2)*playerOneAttack.getPowerModifier());
		case EMOTIONAL:
			return (int) ((thing.getInsight()-thing2.getWillpower()/2)*playerOneAttack.getPowerModifier());
		}
		return -1;
	}
	
	/**
	 * calculates how many attacks a Thing gets in with a particular move
	 * @param playerOne - first Thing who's fighting
	 * @param playerTwo - second Thing fighting
	 * @param playerOneAttack - first Thing's move
	 * @param playerTwoAttack - second Thing's move
	 * @return 0 < 1 < n (number of moves)
	 */
	public static float getNumAttacks(Thing playerOne, Thing playerTwo, Move playerOneAttack, Move playerTwoAttack){
		return (playerOne.getMobility()*playerOneAttack.getSpeedModifier())/(playerTwo.getMobility()*playerTwoAttack.getSpeedModifier());
	}	
	
	/**
	 * returns negative if you go second, positive if you go fist
	 * @param playerOne
	 * @param playerTwo
	 * @param playerOneAttack
	 * @param playerTwoAttack
	 * @return
	 */
	public static int attack(Thing playerOne, Thing playerTwo, Move playerOneAttack, Move playerTwoAttack){
		//int total = 0;
		
		//Random rand = new Random();
		
		int dmg = getBaseDmg(playerOne, playerTwo, playerOneAttack);
		return dmg;
		/* float speediness = getNumAttacks(playerOne, playerTwo, playerOneAttack, playerTwoAttack);
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
			return total; */
	}
}
