package com.store.components.interface_i;

import java.util.Collection;
import com.store.components.entity.Customers;

public interface ICustomers {
	public Customers searchByIdCustomer(int id);

	Collection<Customers> infoAboutCustomer(int page);

	public void addCustomer(Customers customer);
}