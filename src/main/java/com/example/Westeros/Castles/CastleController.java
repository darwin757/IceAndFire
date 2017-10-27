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

//TODO ALL LINKS MUST BE CHANGE WHEN I SET UP RELATIONSHIP BETWEEN CASTLE AND KINGDOM
@RestController
public class CastleController {

	@Autowired
	private CastleService castleService;
	
	@RequestMapping("/Westeros/Castles")
	public List<Castle> getAllCastles(){
		return castleService.getAllCastles();
	}
	
	@RequestMapping("/Westeros/Castles/{name}")
	public Castle getCastle(@PathVariable String name) {
		return castleService.getCastle(name);
	}
	
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
