package com.example.Westeros.Castles;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Castle {

	@Id
	private String name;
	public Castle() {}
	
}
