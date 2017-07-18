package com.integration.tests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.store.components.entity.Customers;
import com.store.components.service.CustomersDao;
import com.store.service.CustomersService;

import com.store.GroceryStore.GroceryStoreResTfullCrudApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { GroceryStoreResTfullCrudApplication.class })
public class CustomersControllerIT {

	private MockMvc mockMvc;

	@Autowired
	protected CustomersService customersService;

	@Autowired
	protected CustomersDao customersDao;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void testVerifyCustomerById_success() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/store/customers/view/20001").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.surName").value("Gutsulka"))
				.andExpect(jsonPath("$.name").value("Ksenia"))
				.andExpect(jsonPath("$.middleName").value("Volodumirivna"))
				.andExpect(jsonPath("$.dateBirth").value("02-09-1994"))
				.andExpect(jsonPath("$.phoneNumber").value("+38065432291"))
				.andExpect(jsonPath("$.numberCard").value("2500998772234"))
				.andExpect(jsonPath("$.discountSale").value(5)).andDo(print());
	}

	@Test
	public void testAddCustomer_success() throws Exception {
		Customers customer = new Customers(20019, "Ania", "Chi", "Bey", "05-06-1945", "+38963875563", "3435465787533",3);
		customersService.addCustomer(customer);
		verify(customersDao, times(10)).addCustomer(customer);
	}

	@Test
	public void testDeleteByIdWhenCustomertsIsNotFound() throws Exception {
		mockMvc.perform(delete("/store/customers/view/{id}", 20000)).andExpect(status().isNotFound());
	}

}