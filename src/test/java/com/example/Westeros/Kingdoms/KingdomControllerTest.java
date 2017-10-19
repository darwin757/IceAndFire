package com.example.Westeros.Kingdoms;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

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

	@Ignore
	@Test
	public void getAllKingdomsTest() throws Exception {

		/* setup mock */
		Kingdom theNorth = new Kingdom("TheNorth");
		Kingdom theRiverlands = new Kingdom("TheRiverlands");
		List<Kingdom> kingdoms = new ArrayList<Kingdom>();
		kingdoms.add(theNorth);
		kingdoms.add(theRiverlands);
		kingdomServiceMock.addKingdom(theNorth);
		kingdomServiceMock.addKingdom(theRiverlands);

		when(kingdomServiceMock.getAllKingdoms()).thenReturn(kingdoms);

		mockMvc.perform(get("/Westeros").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("The North"));
	}

	@Test
	public void getKingdomTest() throws Exception {
		Kingdom theNorth = new Kingdom("TheNorth");
		kingdomServiceMock.addKingdom(theNorth);
		when(kingdomServiceMock.getKingdom("TheNorth")).thenReturn(theNorth);

		mockMvc.perform(get("/Westeros/TheNorth")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("TheNorth"));
	}

	@Test
	public void addKingdomTest() throws Exception {

		mockMvc.perform(post("/Westeros")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\": \"Dorne\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());
		
		mockMvc.perform(get("/Westeros")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Dorne"));
	}

	@Test
	public void updateKingdomTest() {

	}

	@Test
	public void deleteKingdomTest() {
	}

	// FIXME refer to the KingdomController class, the method should be moved to
	// another class
	// more suited to it's purpose
	@Test
	public void deleteAlltest() {
	}
}
