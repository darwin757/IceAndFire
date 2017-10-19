package com.example.Westeros.Kingdoms;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import com.example.Westeros.Castles.Castle;


@NodeEntity
public class Kingdom {

	// TODO a Kingdom should have a king or a king should have a kingdom

	@GraphId private Long id;
	
	private String name;

	// TODO This has to be a list of Castles
	private Castle castle;

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

	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

}
