package com.ftwdev.stuffinc.core.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.ftwdev.stuffinc.core.Move;

public class MoveTest {

	@Test
	public void test() throws IOException {
		Move.initMoveList(new FileInputStream("test/assets/Moves.yml"));
		Map<String, Move> moves = Move.getMoveList();
		for(String key : moves.keySet()) {
			Move move = moves.get(key);
			System.out.println(move.getName());
			System.out.println("\tMove Type: " + move.getMoveType());
			System.out.println("\tStuff Type: " + move.getStuffType());
			System.out.println("\tSpeed: " + move.getSpeedModifier());
			System.out.println("\tPower: " + move.getPowerModifier());
		}
	}

}
