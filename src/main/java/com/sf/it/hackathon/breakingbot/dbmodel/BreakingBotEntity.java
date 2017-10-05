package com.sf.it.hackathon.breakingbot.dbmodel;

public class BreakingBotEntity {

	private final int id;
	private final String name;
	
	public BreakingBotEntity(int id, String name){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
