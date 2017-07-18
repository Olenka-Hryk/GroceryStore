package com.JUnit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.store.GroceryStore.GroceryStoreResTfullCrudApplication;
import com.store.components.entity.Customers;
import com.store.components.service.CustomersDao;
import com.store.service.CustomersService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { GroceryStoreResTfullCrudApplication.class })
public class CustomersJUnitTests {

	@Autowired
	protected CustomersDao customersDao;

	@Autowired
	protected CustomersService service;

	@Test
	public void testGetCustomerById_success() throws Exception {
		Customers customer = customersDao.searchByIdCustomer(20001);
		assertNotNull(customer);
	}

	@Test
	public void testAddCustomer_success() {
		System.out.println("Creating a new customer");

		Customers newCustomer = new Customers();
		newCustomer.setSurName("Super");
		newCustomer.setName("Girl");
		newCustomer.setMiddleName("Star");
		newCustomer.setDateBirth("02-04-1997");
		newCustomer.setPhoneNumber("+380686294838");
		newCustomer.setNumberCard("34345567678");
		newCustomer.setDiscountSale(10);

		System.out.println("ID new customer: " + newCustomer.getId());
		assertNotNull(newCustomer);

		service.addCustomer(newCustomer);
		Customers findCustomer = customersDao.searchByIdCustomer(20002);
		assertEquals("Super", findCustomer.getSurName());
	}

	@Test
	public void testFindAndReadExistingCustomersById_success() throws Exception {
		Customers ksenia = new Customers();
		ksenia = customersDao.searchByIdCustomer(20001);

		assertEquals("Gutsulka", ksenia.getSurName());
		assertEquals("Ksenia", ksenia.getName());
		assertEquals("2500998772234", ksenia.getNumberCard());
	}

}
