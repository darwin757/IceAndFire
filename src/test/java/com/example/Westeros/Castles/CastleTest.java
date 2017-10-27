package com.example.Westeros.Castles;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CastleTest {
	@Test
	public void TestConstructer() {
		Castle castle = new Castle("The Riverlands");
		Assert.assertEquals("The Riverlands", castle.getName());
	}

	@Test
	public void setNameGetNameTest() {
		Castle castle = new Castle();
		castle.setName("The Eyrie");
		Assert.assertEquals("The Eyrie", castle.getName());
	}
}
