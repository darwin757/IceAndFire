package com.example.Westeros.Kingdoms;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.Westeros.Castles.Castle;


@Entity
public class Kingdom {

	// TODO a Kingdom should have a king or a king should have a kingdom

	@Id
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
