package com.integration.tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import com.store.components.entity.Reserve;
import com.store.components.service.ReserveDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.GroceryStore.GroceryStoreResTfullCrudApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { GroceryStoreResTfullCrudApplication.class })
public class ReserveIntegrationTests {

	private MockMvc mockMvc;

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	protected ReserveDao reserveDao;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGetProductDetailsApi() throws JsonProcessingException {
		Reserve reserve = new Reserve(11002, 1, 10102, "20-10-2018", 50, 58, "6789008654679", 150);
		reserveDao.addProduct(reserve);

		Reserve apiResponse = restTemplate.getForObject("http://localhost:8080/store/reserve/view/{id}", Reserve.class,
				reserve.getIdProduct());

		assertNotNull(apiResponse);
		assertEquals(reserve.getIdProduct(), apiResponse.getIdProduct());
		assertEquals(reserve.getIdStore(), apiResponse.getIdStore());
		assertEquals(reserve.getExpirationDate(), apiResponse.getExpirationDate());
		assertEquals(reserve.getBarCode(), apiResponse.getBarCode());

		reserveDao.deleteByIdProduct(reserve.getIdProduct());
	}

	@Test
	public void testCreateProductApi_success() throws JsonProcessingException {
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("idStore", 1);
		requestBody.put("idProduct", 10103);
		requestBody.put("expirationDate", "20-07-2018");
		requestBody.put("quantity", 28);
		requestBody.put("price", 56);
		requestBody.put("barCode", "599934232228");
		requestBody.put("productSize", 250);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
				requestHeaders);
		Map<String, Object> apiResponse = restTemplate.postForObject("http://localhost:8080/store/reserve/create",
				httpEntity, Map.class, Collections.EMPTY_MAP);

		assertNotNull(apiResponse);
		String message = apiResponse.get("message").toString();
		assertEquals("SUCCESS: Product was created!", message);

		Reserve reserveFromDb = reserveDao.searchByIdProduct(10103);
		assertEquals(10103, reserveFromDb.getIdProduct());
		assertEquals(1, reserveFromDb.getIdStore());
		assertEquals("20-07-2018", reserveFromDb.getExpirationDate());
		assertTrue("599934232228" == reserveFromDb.getBarCode());

		reserveDao.deleteByIdProduct(11002);
	}

	@Test
	public void testUpdateProductDetails() throws JsonProcessingException {
		Reserve reserve = new Reserve(11003, 1, 10104, "20-10-2018", 7, 58, "6789008654679", 250);
		reserveDao.addProduct(reserve);

		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("idStore", 2);
		requestBody.put("idProduct", 10104);
		requestBody.put("expirationDate", "20-10-2018");
		requestBody.put("quantity", 7);
		requestBody.put("price", 58);
		requestBody.put("barCode", "6789008654679");
		requestBody.put("productSize", 250);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
				requestHeaders);

		Map<String, Object> apiResponse = (Map) restTemplate.exchange("http://localhost:8080/store/reserve/update",
				HttpMethod.PUT, httpEntity, Map.class, Collections.EMPTY_MAP).getBody();

		assertNotNull(apiResponse);
		assertTrue(!apiResponse.isEmpty());

		String message = apiResponse.get("message").toString();
		assertEquals("SUCCESS: Product was update!", message);

		Reserve reserveFromDb = reserveDao.searchByIdProduct(reserve.getIdProduct());
		assertEquals(requestBody.get("idStore"), reserveFromDb.getIdStore());
		assertEquals(requestBody.get("idProduct"), reserveFromDb.getIdProduct());
		assertEquals(requestBody.get("expirationDate"), reserveFromDb.getExpirationDate());
		assertTrue(Integer.parseInt(requestBody.get("quantity").toString()) == reserveFromDb.getQuantity());
		assertEquals(requestBody.get("barCode"), reserveFromDb.getBarCode());

		reserveDao.deleteByIdProduct(reserveFromDb.getIdProduct());

	}

	@Test
	public void testDeleteProductApi() {
		Reserve reserve = new Reserve(11004, 1, 10105, "06-11-2018", 107, 456, "6789056468654679", 150);
		reserveDao.addProduct(reserve);

		restTemplate.delete("http://localhost:8080/store/reserve/delete/{id}", reserve.getIdProduct());

		Reserve reserveFromDb = reserveDao.searchByIdProduct(reserve.getIdProduct());
		assertNull(reserveFromDb);
	}

	@Test
	public void testLoadInfoReserveByIdProduct() throws Exception {
		String uri = "/store/reserve/view/10101";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		System.out.println("Status is :" + status);
		System.out.println("content is :" + content);
		assertTrue(status == 200);
		assertTrue(content.trim().length() > 0);

	}

	@Test
	public void testEditReserveById() throws Exception {
		String uri = "/store/reserve/update";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri))
				.andExpect(MockMvcResultMatchers.view().name("/update")).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		System.out.println("Status is :" + status);
		System.out.println("content is :" + content);
		assertTrue(status == 200);
		assertTrue(content.trim().length() > 0);
	}

	@Test
	public void testDeleteByIdProduct() throws Exception {
		String uri = "/store/reserve/delete/10101";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		System.out.println("Status is :" + status);
		System.out.println("content is :" + content);
		assertTrue(status == 200);
		assertTrue(content.trim().length() == 0);
	}

}