package com.ftwdev.stuffinc.core.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Test;

import com.ftwdev.stuffinc.core.Move;
import com.ftwdev.stuffinc.core.Stuff;

public class StuffTest {

	@Test
	public void test() throws FileNotFoundException {
		Move.initMoveList(new FileInputStream("test/assets/moves.yml"));
		Stuff.initStuffList(new FileInputStream("test/assets/stuff.yml"));
		Map<String, Stuff> stuffs = Stuff.getStuffList();
		for(String key : stuffs.keySet()) {
			Stuff stuff = stuffs.get(key);
			System.out.println(stuff.getName());
			System.out.println("\tType: " + stuff.getType());
			for(String growthKey: stuff.getGrowthStats().keySet()) {
				int[] range = stuff.getGrowthStats().get(growthKey);
				System.out.println("\t"+growthKey+": "+range[0]+" to "+range[1]);
			}
			System.out.println("\tMoves:");
			for(int level: stuff.getMoveList().keySet()) {
				System.out.println("\t\t"+stuff.getMoveList().get(level).getName()+" at level "+level);
			}
		}
	}

}
