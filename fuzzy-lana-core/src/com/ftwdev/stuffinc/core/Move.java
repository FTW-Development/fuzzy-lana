package com.ftwdev.stuffinc.core;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Move {
	private String name;
	private MoveType moveType;
	private StuffType stuffType;
	private float speedModifier;
	private float powerModifier;
	private static Map<String, Move> moveList = null;
	
	public Move(Object name, Object moveType, Object stuffType, Object speed, Object power) throws MalformedDataException {
		try {
			this.name = (String) name;
			this.moveType = MoveType.valueOf(((String) moveType).toUpperCase());
			this.stuffType = StuffType.valueOf(((String) stuffType).toUpperCase());
			this.speedModifier = Float.parseFloat(speed.toString());
			this.powerModifier = Float.parseFloat(power.toString());
		} catch(Exception e) {
			throw new MalformedDataException();
		}
	}
	
	public static void initMoveList(InputStream f) throws FileNotFoundException {
		if (Move.moveList == null) {
			Move.moveList = new HashMap<String, Move>();
			Yaml yaml = new Yaml();
			Map<String, Object> map = (Map<String, Object>) yaml.load(f);
			ArrayList moves = (ArrayList) map.get("moves");
			for (Object content : moves) {
				Map mapping = (Map) content;
				try {
					Move move = new Move(mapping.get("name"), mapping.get("moveType"), 
							mapping.get("stuffType"), mapping.get("speed"), mapping.get("power"));
					Move.moveList.put(move.getName(), move);
				} catch (MalformedDataException e) {
					System.out.println("Malformed move data... skipping.");
				}
			}
		}
	}


	public String getName() {
		return name;
	}


	public MoveType getMoveType() {
		return moveType;
	}


	public StuffType getStuffType() {
		return stuffType;
	}


	public float getSpeedModifier() {
		return speedModifier;
	}


	public float getPowerModifier() {
		return powerModifier;
	}


	public static Map<String, Move> getMoveList() {
		return moveList;
	}
}
