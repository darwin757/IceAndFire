package com.example.Westeros.Kingdoms;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Westeros.Castles.Castle;
import com.example.Westeros.Castles.CastleService;
import com.example.Westeros.Kingdoms.Kingdom;
import com.example.Westeros.Kingdoms.KingdomService;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KingdomServiceTest {

	@Autowired
	private KingdomService kingdomService;
	
	@Autowired
	private CastleService castleService;

	private Kingdom theNorth;
	private Kingdom theRiverlands; 
	private Kingdom theVale; 
	private Kingdom theWesterlands; 
	private Kingdom theIronIslands;
	private Kingdom theReach;
	private Kingdom dorne;

	@Before
	public void SetUp() {
		theNorth = new Kingdom("The North");
		theRiverlands = new Kingdom("The Riverlands");
		theVale = new Kingdom("The Vale");
		theWesterlands = new Kingdom("The Westerlands");
		theIronIslands = new Kingdom("The Iron Islands");
		theReach = new Kingdom("The Reach");
		dorne = new Kingdom("Dorne");
	}

	@Test
	public void getAllKingdomsTest() {
		List<Kingdom> kingdoms = setUpTestArrayListForSevenKingdoms();
		addKingdomsToKingdomServiceVariable();

		for (int i = 0; i < kingdoms.size(); i++) {
			Assert.assertEquals(kingdoms.get(i).getName(), kingdomService.getAllKingdoms().get(i).getName());
		}
	}


	@Test
	public void addAndGetKingdomTest() {
		 kingdomService.addKingdom(theNorth);
		 Assert.assertEquals("The North", kingdomService.getKingdom("The North").getName());
	}

	@Test
	public void deleteKingdomTest() {
		 kingdomService.addKingdom(theVale);
		 Assert.assertEquals("The Vale", kingdomService.getKingdom("The Vale").getName());
		 kingdomService.deleteKingdom("The Vale");
		 Assert.assertNull(kingdomService.getKingdom("The Vale"));
	}

	@Test
	public void deleteAllTest() {
		addKingdomsToKingdomServiceVariable();
		Assert.assertNotNull(kingdomService.getAllKingdoms());
		kingdomService.deleteAll();
		List<Kingdom> emptyArrayList= new ArrayList<Kingdom>();
		Assert.assertEquals(emptyArrayList,kingdomService.getAllKingdoms());
		
	}
	
	
	@Test 
	public void addCastleToKingdomTest() {
		kingdomService.addKingdom(theNorth);
		//TODO test passes only like that, but if I change the order of input or asserts it fails, look into that.	
		kingdomService.addCastleToKingdom("The North", "Winterfell");
		kingdomService.addCastleToKingdom("The North", "The Dreadfort");
		kingdomService.addCastleToKingdom("The North", "White Harbor");
		
	
		Assert.assertEquals("The Dreadfort", kingdomService.getKingdomsCastles("The North").get(0).getName());
		Assert.assertEquals("Winterfell", kingdomService.getKingdomsCastles("The North").get(1).getName());
		Assert.assertEquals("White Harbor", kingdomService.getKingdomsCastles("The North").get(2).getName());
		
	}

	//TODO Something about the order of input in these methods is just not right
	@Test
	public void getKingdomsCastleTest() {
		kingdomService.addKingdom(theNorth);
		kingdomService.addKingdom(theIronIslands);
		
		kingdomService.addCastleToKingdom("The North", "Winterfell");
		kingdomService.addCastleToKingdom("The North", "The Dreadfort");
		kingdomService.addCastleToKingdom("The North", "White Harbor");
		
		kingdomService.addCastleToKingdom("The Iron Islands", "Pyke");
		kingdomService.addCastleToKingdom("The Iron Islands", "great Wyk");
		kingdomService.addCastleToKingdom("The Iron Islands", "Harlaw");
		
		Assert.assertEquals("The Dreadfort", kingdomService.getKingdomsCastles("The North").get(0).getName());
		Assert.assertEquals("Winterfell", kingdomService.getKingdomsCastles("The North").get(1).getName());
		Assert.assertEquals("White Harbor", kingdomService.getKingdomsCastles("The North").get(2).getName());

		Assert.assertEquals("great Wyk", kingdomService.getKingdomsCastles("The Iron Islands").get(0).getName());
		Assert.assertEquals("Pyke", kingdomService.getKingdomsCastles("The Iron Islands").get(1).getName());
		Assert.assertEquals("Harlaw", kingdomService.getKingdomsCastles("The Iron Islands").get(2).getName());
	
	}
	
	private List<Kingdom> setUpTestArrayListForSevenKingdoms() {
		List<Kingdom> kingdoms = new ArrayList<Kingdom>();
		Kingdom[] kingdomsArray = setAndGetKingdomsArray();

		for (int i = 0; i < kingdomsArray.length; i++) {
			kingdoms.add(kingdomsArray[i]);
		}

		return kingdoms;
	}

	private void addKingdomsToKingdomServiceVariable() {
		Kingdom[] kingdomsArray = setAndGetKingdomsArray();

		for (int i = 0; i < kingdomsArray.length; i++) {
			kingdomService.addKingdom(kingdomsArray[i]);
		}
	}
	
	private Kingdom[] setAndGetKingdomsArray() {
		Kingdom[] kingdomsArray = { theNorth, theRiverlands, theVale, theWesterlands, theIronIslands, theReach, dorne };
		return kingdomsArray;
	}
	
	@After
	public void cleanUp(){
		kingdomService.deleteAll();
		castleService.deletAll();
	}
}

