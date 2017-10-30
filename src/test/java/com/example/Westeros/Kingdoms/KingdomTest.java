package com.example.Westeros.Kingdoms;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Westeros.Castles.Castle;
import com.example.Westeros.Kingdoms.Kingdom;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KingdomTest {
	
	@Test
	public void TestConstructer() {
		Kingdom kingdom = new Kingdom("The Riverlands");
		Assert.assertEquals("The Riverlands", kingdom.getName());
	}

	@Test
	public void setNameGetNameTest() {
		Kingdom kingdom = new Kingdom();
		kingdom.setName("The Vale");
		Assert.assertEquals("The Vale", kingdom.getName());
	}

	@Test
	public void setCastleGetCastleTest() {
		Kingdom theNorth = new Kingdom("The North");
		
		Castle winterfell= new Castle("Winterfell");
		Castle theDreadfort= new Castle("The Dreadfort");
		Castle whiteHarbor= new Castle("White Harbor");
		
		theNorth.addCastle(winterfell);
		theNorth.addCastle(theDreadfort);
		theNorth.addCastle(whiteHarbor);
		
		Assert.assertEquals("Winterfell",theNorth.getCastles().get(0).getName());
		Assert.assertEquals("The Dreadfort",theNorth.getCastles().get(1).getName());
		Assert.assertEquals("White Harbor",theNorth.getCastles().get(2).getName());
		
	}
}
