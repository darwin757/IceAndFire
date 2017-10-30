package com.example.Westeros.Kingdoms;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.example.Westeros.Castles.Castle;


@NodeEntity
public class Kingdom {

	// TODO a Kingdom should have a king or a king should have a kingdom
	//TODO a Kingdom should also have a capital
	@GraphId private Long id;
	
	private String name;

	@Relationship(type = "Has a")
	private List<Castle> castles = new ArrayList<Castle>();
	
	public Kingdom() {}
	
	public Kingdom(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Castle> getCastles() {
		return castles;
	}
	
	public void addCastle(Castle castle) {
		this.castles.add(castle);
	}

}
