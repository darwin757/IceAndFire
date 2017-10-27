package com.example.Westeros.Castles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastleService {

	@Autowired
	private CastleRepository castleRepository;

	public List<Castle> getAllCastles() {
		
		List<Castle> castles = new ArrayList<Castle>();
		castleRepository.findAll().forEach(castles::add);
		return castles;
		
	}
	
	public Castle getCastle(String name) {
		return castleRepository.findByName(name);
	}
	
	public void addCastle(Castle castle) {
		castleRepository.save(castle);
	}
	
	public void deleteCastle(String name) {
		//the deleteCastle(String name) fails the test
		castleRepository.delete(getCastle(name));;
	}
	
	public void deletAll() {
		castleRepository.deleteAll();
	}

}
