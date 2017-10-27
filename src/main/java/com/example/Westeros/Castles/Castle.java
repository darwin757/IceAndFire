package com.example.Westeros.Castles;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Castle {

	@GraphId
	private Long id;

	private String name;

	public Castle() {
	}

	public Castle(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
