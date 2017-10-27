package com.example.Westeros.Castles;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CastleControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@MockBean
	private CastleService castleServiceMock;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void getAllCastlesTest() throws Exception {
		List<Castle> castles = setUpAListOfCastles();

		when(castleServiceMock.getAllCastles()).thenReturn(castles);

		mockMvc.perform(get("/Westeros/Castles").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name").value("Winterfell"))
				.andExpect(jsonPath("$[1].name").value("Riverrun"));
	}

	@Test
	public void getCastleTest() throws Exception {

		Castle TheEyrie = setUpACastle("TheEyrie");
		castleServiceMock.addCastle(TheEyrie);

		when(castleServiceMock.getCastle("TheEyrie")).thenReturn(TheEyrie);

		mockMvc.perform(get("/Westeros/Castles/TheEyrie").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("TheEyrie"));
	}

	@Test
	public void addCastle() throws Exception {

		mockMvc.perform(post("/Westeros/Castles").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\":\"The Red Keep\"}").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateCastle() throws Exception {
		mockMvc.perform(post("/Westeros/Castles").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\":\"Stonebridge\"}").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());

		mockMvc.perform(put("/Westeros/Castles/Stonebridge").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\":\"Bitterbridge\"}").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteCastleTest() throws Exception {
		
		mockMvc.perform(post("/Westeros/Castles").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"name\":\"SummerHall\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

		mockMvc.perform(delete("Westeros/Castles/SummerHall").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	private List<Castle> setUpAListOfCastles() {
		Castle winterfell = setUpACastle("Winterfell");
		Castle riverrun = setUpACastle("Riverrun");

		List<Castle> castles = new ArrayList<Castle>();

		castles.add(winterfell);
		castles.add(riverrun);

		// FIXME Wrong place for this code
		castleServiceMock.addCastle(winterfell);
		castleServiceMock.addCastle(riverrun);
		return castles;
	}

	private Castle setUpACastle(String name) {
		Castle castle = new Castle(name);
		return castle;
	}
}
