package com.ftwdev.stuffinc.core.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.ftwdev.stuffinc.core.Move;
import com.ftwdev.stuffinc.core.Stuff;
import com.ftwdev.stuffinc.core.StuffFactory;
import com.ftwdev.stuffinc.core.Thing;

public class StuffFactoryTest {

	@Test
	public void test() throws FileNotFoundException {
		Move.initMoveList(new FileInputStream("test/assets/moves.yml"));
		Stuff.initStuffList(new FileInputStream("test/assets/stuff.yml"));
		Thing starter = StuffFactory.getStarter();
		System.out.println(starter.getName() + " - lvl " + starter.getLevel());
		System.out.println("\tType: " + starter.getType());
		System.out.println("\tHealth: "+starter.getHealth());
		System.out.println("\tForce: "+starter.getForce());
		System.out.println("\tHardiness: "+starter.getHardiness());
		System.out.println("\tInsight: "+starter.getInsight());
		System.out.println("\tWillpower: "+starter.getWillpower());
		System.out.println("\tMobility: "+starter.getMobility());
		System.out.println("\tSoul: "+starter.getSoul());
		System.out.println("\tMoves:");
		for(Move m : starter.getMoveSet()) {
			System.out.println("\t\t" + m.getName());
		}
	}

}
