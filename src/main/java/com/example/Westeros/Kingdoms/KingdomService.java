package com.example.Westeros.Kingdoms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

	
	@Autowired
	private KingdomRepository kingdomRepository;
	
	
	public List<Kingdom> getAllKingdoms() {
		List<Kingdom> kingdoms = new ArrayList<Kingdom>();
		kingdomRepository.findAll().forEach(kingdoms::add);
		return kingdoms;
	}

	public Kingdom getKingdom(String name) {
		return kingdomRepository.findByName(name);
	}

	public void addKingdom(Kingdom kingdom) {
		kingdomRepository.save(kingdom);
	}

	public void deleteKingdom(String name) {
		//I did it this way because the delete method that takes a String
		//as an argument is not working
		kingdomRepository.delete(getKingdom(name));
	}

	public void deleteAll() {
		kingdomRepository.deleteAll();
	}
}
