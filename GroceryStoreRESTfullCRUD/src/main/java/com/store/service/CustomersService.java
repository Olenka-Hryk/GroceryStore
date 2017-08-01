package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Customers;
import com.store.components.interface_i.ICustomers;

@Service
public class CustomersService {

	@Autowired
	private ICustomers iCustomers;

	public Customers searchByIdCustomer(int id) {
		return iCustomers.searchByIdCustomer(id);
	}

	public List<Customers> infoAboutAllCustomer() {
		return iCustomers.infoAboutAllCustomer();
	}

	public void addCustomer(Customers customer) {
		iCustomers.addCustomer(customer);
	}
}
