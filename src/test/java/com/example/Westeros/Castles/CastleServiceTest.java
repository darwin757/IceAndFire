package com.example.Westeros.Castles;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.After;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CastleServiceTest {
	
	@Autowired
	private CastleService castleService;
	
	private Castle Winterfell;
	private Castle Riverrun;
	private Castle TheEyrie;
	private Castle CasterlyRock;
	private Castle Pyke;
	private Castle HighGarden;
	private Castle SunSpear;
	
	@Before 
	public void SetUp() {
		Winterfell = new Castle("Winterfell");
		Riverrun = new Castle("Riverrun");
		TheEyrie = new Castle("The Eyrie");
		CasterlyRock = new Castle("Casterly Rock");
		Pyke = new Castle("Pyke");
		HighGarden = new Castle("High Garden");
		SunSpear = new Castle("Sun Spear");
	}
	
	@Test
	public void getAllCastlesTest() {
		List<Castle> castles = setUpArrayListForSevenCastles();
		addCastlesToCastleServiceVariable();
		
		for (int i =0; i<castles.size();i++) {
			Assert.assertEquals(castles.get(i).getName(),castleService.getAllCastles().get(i).getName());
		}
		
	}

	@Test
	public void addAndGetCastleTest() {
		castleService.addCastle(HighGarden);
		Assert.assertEquals("High Garden",castleService.getCastle("High Garden").getName());
	}

	@Test
	public void deleteCastleTest() {
		castleService.addCastle(Winterfell);
		Assert.assertEquals("Winterfell", castleService.getCastle("Winterfell").getName());
		castleService.deleteCastle("Winterfell");
		Assert.assertNull(castleService.getCastle("Winterfell"));
	}
	
	@Test
	public void deleteAllTest() {
		addCastlesToCastleServiceVariable();
		Assert.assertNotNull(castleService.getAllCastles());
		castleService.deletAll();
		List<Castle> emptyArrayListForTesting = new ArrayList<Castle>();
		Assert.assertEquals(emptyArrayListForTesting, castleService.getAllCastles());
	}
	
	
	private List<Castle> setUpArrayListForSevenCastles(){
		List<Castle> castles = new ArrayList<Castle>();
		Castle[] castleArray = setAndGetCastleArray();
		
		for(int i = 0; i < castleArray.length; i++) {
			castles.add(castleArray[i]);
		}
		return castles;
	}
	
	private void addCastlesToCastleServiceVariable() {
		Castle[] castleArray = setAndGetCastleArray();
		
		for(int i= 0; i < castleArray.length; i++) {
			castleService.addCastle(castleArray[i]);
		}
	}
	
	private Castle[] setAndGetCastleArray() {
		Castle[] castleArray = {Winterfell,Riverrun,TheEyrie,CasterlyRock,Pyke,HighGarden,SunSpear};
		return castleArray;
	}
	@After
	public void CleanUp() {
		castleService.deletAll();
	}
}
