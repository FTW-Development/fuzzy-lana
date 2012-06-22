package com.ftwdev.stuffinc.core;

import java.util.ArrayList;
import java.util.List;

public class Collector {
	private List<Thing> collection;
	private List<Thing> pocket;
	
	final int MAX_POCKET_SIZE = 6;
	final int MAX_COLLECTION_SIZE = 200;
	
	public Collector(Thing starter){
		collection = new ArrayList<Thing>();
		collection.add(starter);
		pocket = new ArrayList<Thing>();
		pocket.add(starter);
	}

	public boolean addThingToCollection(Thing thingToAdd){
		if (collection.size() < MAX_COLLECTION_SIZE){
			collection.add(thingToAdd);
			return true;
		} else
			return false;
	}
	
	/** Returns true if add was successful
	 * false if pocket is full **/
	public boolean addThingToPocket(Thing thingToAdd){
		if (pocket.size() < MAX_POCKET_SIZE){
			pocket.add(thingToAdd);
			return true;
		} else
			return false;
	}
	
	/** returns true if swap was successful **/
	public boolean swapThingToPocket(Thing thingToAdd, Thing thingToRemove){
		return false;
	}
	
	public List<Thing> getPocket(){
		return pocket;
	}
	
	public List<Thing> getStuff(){
		return collection;
	}
}