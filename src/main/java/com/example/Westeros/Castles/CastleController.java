package com.example.Westeros.Castles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Westeros.Kingdoms.KingdomService;

@RestController
public class CastleController {

	@Autowired
	private CastleService castleService;
	
	@Autowired
	private KingdomService kingdomService;
	
	@RequestMapping("/Westeros/Castles")
	public List<Castle> getAllCastles(){
		return castleService.getAllCastles();
	}
	
	//TODO fix test for this method
	@RequestMapping("/Westeros/{kingdomsName}/Castles")
	public List<Castle> getAllCastlesOfAKingdom(@PathVariable String kingdomsName){
		return kingdomService.getKingdomsCastles(kingdomsName);
	}
	
	@RequestMapping("/Westeros/Castles/{name}")
	public Castle getCastle(@PathVariable String name) {
		return castleService.getCastle(name);
	}
	
	//TODO Reconsider this
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value="/Westeros/Castles")
	public void addCastle(@RequestBody Castle castle) {
		castleService.addCastle(castle);
	}
	
	@RequestMapping(method= RequestMethod.PUT, value= "/Westeros/Castles/{name}")
	public void updateCastle(@RequestBody Castle castle, @PathVariable String name) {
		castleService.addCastle(castle);
	}

	@RequestMapping(method = RequestMethod.DELETE,value= "/Westeros/Castles/{name}")
	public void deleteCastle(@PathVariable String name) {
		castleService.deleteCastle(name);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value= "/Westeros/Castles")
	public void deleteAll() {
		castleService.deletAll();
	}
}
