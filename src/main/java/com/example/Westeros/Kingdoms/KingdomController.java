package com.example.Westeros.Kingdoms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {

	@Autowired
	private KingdomService kingdomService;
	
	@RequestMapping("/Westeros")
	public List<Kingdom> getAllKingdoms(){
		return kingdomService.getAllKingdoms();
	}
	
	@RequestMapping("/Westeros/{name}")
	public Kingdom getKingdom(@PathVariable String name) {
		return kingdomService.getKingdom(name);
		}
	
	//FIXME Maybe ResponseStatus doesn't belong here, rather in it's own controllerAdvice class
	//TODO read more about controllerAdvice in Spring
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value ="/Westeros")
	public void addKingdom(@RequestBody Kingdom kingdom) {
		kingdomService.addKingdom(kingdom);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/Westeros/{name}")
	public void updateKingdom(@RequestBody Kingdom kingdom, @PathVariable String name) {
		kingdomService.addKingdom(kingdom);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/Westeros/{name}")
	public void deleteKingdom(@PathVariable String name) {
		kingdomService.deleteKingdom(name);
	}
	
	//FIXME This method does not belong here, it's only here for testing purposes 
	@RequestMapping(method =RequestMethod.DELETE, value="/Westeros/TheLongNight")
	public void deleteAll() {
		kingdomService.deleteAll();
	}
}
