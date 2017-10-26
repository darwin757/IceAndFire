package com.example.Westeros.Kingdoms;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.Westeros.WesterosApplication;
import com.example.Westeros.Kingdoms.Kingdom;

import org.junit.Assert;
import org.junit.Test;

@RunWith(SpringRunner.class)
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

	//TODO write this test
	@Test
	public void setCastleGetCastleTest() {
	}
}
