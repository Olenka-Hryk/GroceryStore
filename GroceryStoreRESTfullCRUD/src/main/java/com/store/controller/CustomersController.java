package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Customers;
import com.store.service.CustomersService;

@RestController
@RequestMapping("/store/customers")
public class CustomersController {
	@Autowired
	private CustomersService customersService;

	/**
	 * Get customers by id from DB
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Customers searchByIdCustomer(@PathVariable("id") int id) {
		return customersService.searchByIdCustomer(id);
	}

	/**
	 * Get all customers from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/information/all", method = RequestMethod.GET)
	public Collection<Customers> infoAboutAllCustomer() {
		return customersService.infoAboutAllCustomer();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addCustomer(@RequestBody Customers customer) {
		customersService.addCustomer(customer);
		return "SUCCESS: Customer was created!";
	}
}
