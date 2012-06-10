package com.ftwdev.stuffinc.core;

import java.util.ArrayList;
import java.util.Random;

public class Thing {
	private ArrayList<Move> moveSet;
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
	
	public Thing(Stuff stuff) {
		this.stuff = stuff;
		this.moveSet = new ArrayList<Move>();
		this.level = 1;
		this.exp = 0;
		this.health = this.stuff.getStartStats().get("sHealth");
		this.force = this.stuff.getStartStats().get("sForce");
		this.hardiness = this.stuff.getStartStats().get("sHardiness");
		this.insight = this.stuff.getStartStats().get("sInsight");
		this.willpower = this.stuff.getStartStats().get("sWillpower");
		this.mobility = this.stuff.getStartStats().get("sMobility");
		this.soul = this.stuff.getStartStats().get("sSoul");
	}
	
	public ArrayList<Move> getMoveSet() {
		return moveSet;
	}
	
	public String getName() {
		return this.stuff.getName();
	}
	
	public StuffType getType() {
		return this.stuff.getType();
	}

	public int getLevel() {
		return level;
	}

	public int getExp() {
		return exp;
	}

	public int getHealth() {
		return health;
	}

	public int getForce() {
		return force;
	}

	public int getHardiness() {
		return hardiness;
	}

	public int getInsight() {
		return insight;
	}

	public int getWillpower() {
		return willpower;
	}

	public int getMobility() {
		return mobility;
	}

	public int getSoul() {
		return soul;
	}

	public Thing(int level) {
		super();
		for(int i = 0; i < level; i++) {
			levelUp();
		}
	}
	
	public void levelUp() {
		this.level++;
		//TODO need to subtract 1 level worth of exp
		this.health += levelUpStat(this.stuff.getGrowthStats().get("health"));
		this.force += levelUpStat(this.stuff.getGrowthStats().get("force"));
		this.hardiness += levelUpStat(this.stuff.getGrowthStats().get("hardiness"));
		this.insight += levelUpStat(this.stuff.getGrowthStats().get("insight"));
		this.willpower += levelUpStat(this.stuff.getGrowthStats().get("willpower"));
		this.mobility += levelUpStat(this.stuff.getGrowthStats().get("mobility"));
		this.soul += levelUpStat(this.stuff.getGrowthStats().get("soul"));
	}
	
	private int levelUpStat(int[] range) {
		Random random = new Random();
		return random.nextInt(range[1] - range[0] + 1) + range[0];
	}
}
