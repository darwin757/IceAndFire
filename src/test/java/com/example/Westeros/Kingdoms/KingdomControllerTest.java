package com.example.Westeros.Kingdoms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

//TODO Major refactor required to clean up this class and consider the testing strategy 
@RunWith(SpringRunner.class)
@SpringBootTest
public class KingdomControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@MockBean
	private KingdomService kingdomServiceMock;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void getAllKingdomsTest() throws Exception {

		List<Kingdom> kingdoms = setUpAListOfKingdoms();

		when(kingdomServiceMock.getAllKingdoms()).thenReturn(kingdoms);

		mockMvc.perform(get("/Westeros").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name").value("TheNorth"))
				.andExpect(jsonPath("$[1].name").value("TheRiverlands"));
	}

	@Test
	public void getKingdomTest() throws Exception {

		Kingdom theNorth = setUpAKingdom("TheNorth");
		kingdomServiceMock.addKingdom(theNorth);

		when(kingdomServiceMock.getKingdom("TheNorth")).thenReturn(theNorth);

		mockMvc.perform(get("/Westeros/TheNorth")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("TheNorth"));
	}

	@Test
	public void addKingdomTest() throws Exception {

		mockMvc.perform(post("/Westeros").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"name\":\"Dorne\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

	}

	@Test
	public void updateKingdomTest() throws Exception {

		mockMvc.perform(post("/Westeros").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"name\":\"Dorne\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

		mockMvc.perform(put("/Westeros/Dorne").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\":\"theReach\"}").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());

	}

	@Test
	public void deleteKingdomTest() throws Exception {

		mockMvc.perform(post("/Westeros").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"name\":\"theVale\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

		mockMvc.perform(delete("Westeros/theVale").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	// FIXME refer to the KingdomController class, the method should be moved to
	// another class more suited to it's purpose
	@Test
	public void deleteAlltest() {
	}

	private List<Kingdom> setUpAListOfKingdoms() {

		Kingdom theNorth = setUpAKingdom("TheNorth");
		Kingdom theRiverlands = setUpAKingdom("TheRiverlands");

		List<Kingdom> kingdoms = new ArrayList<Kingdom>();

		kingdoms.add(theNorth);
		kingdoms.add(theRiverlands);

		// FIXME wrong place for this code but I can't find another
		kingdomServiceMock.addKingdom(theNorth);
		kingdomServiceMock.addKingdom(theRiverlands);

		return kingdoms;

	}

	private Kingdom setUpAKingdom(String name) {

		Kingdom kingdom = new Kingdom(name);
		return kingdom;
	}
}
