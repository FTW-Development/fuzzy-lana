package com.ftwdev.stuffinc.core;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Stuff {
	private static HashMap<String, Stuff> stuffList;
	private String name;
	private StuffType type;
	private int evolveLevel;
	private String evolveTo;
	private Map<String, int[]> startStats;
	private Map<String, int[]> growthStats;
	private Map<Integer, Move> moveList;
	
	public Stuff(Object name, Object type, Object evolveAt, Object evolveTo, Map<String, Object> growths, 
			Map<String, Object> startStats, Object moves) throws MalformedDataException {
		
		try {
			this.name = (String) name;
			this.type = StuffType.valueOf(((String) type).toUpperCase());
			if(evolveAt != null && evolveTo != null) {
				this.evolveLevel = Integer.parseInt(evolveAt.toString());
				this.evolveTo = (String) evolveTo;
			}
			this.startStats = new HashMap<String, int[]>();
			this.growthStats = new HashMap<String, int[]>();
			this.moveList = new HashMap<Integer, Move>();
			for(String growthType : growths.keySet()) {
				int[] range = new int[2];
				String[] split = ((String) growths.get(growthType)).split("-");
				range[0] = Integer.parseInt(split[0]);
				range[1] = Integer.parseInt(split[1]);
				this.growthStats.put(growthType, range);
			}
			for(String startStat : startStats.keySet()) {
				//System.out.println(startStat + " " + startStats.get(startStat));
				int[] range = new int[2];
				String[] split = ((String) startStats.get(startStat)).split("-");
				range[0] = Integer.parseInt(split[0]);
				range[1] = Integer.parseInt(split[1]);
				//int value = Integer.parseInt(startStats.get(startStat).toString());
				this.startStats.put(startStat, range);
			}
			ArrayList moveProgression = (ArrayList) moves;
			Map<String, Move> allMoves = Move.getMoveList();
			for(Object move : moveProgression) {
				String[] split = ((String) move).split("-");
				if(allMoves.keySet().contains(split[0])) {
					this.moveList.put(Integer.parseInt(split[1]), allMoves.get(split[0]));
				}
			}
		} catch(Exception e) {
			throw new MalformedDataException();
		}
	}

	public static void initStuffList(FileInputStream f) {
		if (stuffList == null) {
			stuffList = new HashMap<String, Stuff>();
			Yaml yaml = new Yaml();
			Map<String, Object> map = (Map<String, Object>) yaml.load(f);			
			ArrayList stuffs = (ArrayList) map.get("stuff");
			for(Object stuff : stuffs) {
				Map mapping = (Map) stuff;
				Map<String, Object> growths = new HashMap<String, Object>();
				growths.put("health", mapping.get("health"));
				growths.put("force", mapping.get("force"));
				growths.put("hardiness", mapping.get("hardiness"));
				growths.put("insight", mapping.get("insight"));
				growths.put("willpower", mapping.get("willpower"));
				growths.put("mobility", mapping.get("mobility"));
				growths.put("soul", mapping.get("soul"));
				Map<String, Object> startStats = new HashMap<String, Object>();
				startStats.put("sHealth", mapping.get("sHealth"));
				startStats.put("sForce", mapping.get("sForce"));
				startStats.put("sHardiness", mapping.get("sHardiness"));
				startStats.put("sInsight", mapping.get("sInsight"));
				startStats.put("sWillpower", mapping.get("sWillpower"));
				startStats.put("sMobility", mapping.get("sMobility"));
				startStats.put("sSoul", mapping.get("sSoul"));
				try {
					Stuff thing = new Stuff(mapping.get("name"), mapping.get("type"), mapping.get("evolveAt"), 
							mapping.get("evolveTo"), growths, startStats, mapping.get("moves"));
					Stuff.stuffList.put(thing.name, thing);
				} catch (MalformedDataException e) {
					System.out.println("Malformed move data... skipping.");
				}
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StuffType getType() {
		return type;
	}

	public void setType(StuffType type) {
		this.type = type;
	}
	
	public int getEvolveLevel() {
		return this.evolveLevel;
	}
	
	public String getEvolveTo() {
		return this.evolveTo;
	}

	public static HashMap<String, Stuff> getStuffList() {
		return stuffList;
	}
	
	public Map<String, int[]> getStartStats() {
		return startStats;
	}
	
	public Map<String, int[]> getGrowthStats() {
		return growthStats;
	}

	public Map<Integer, Move> getMoveList() {
		return moveList;
	}
}
