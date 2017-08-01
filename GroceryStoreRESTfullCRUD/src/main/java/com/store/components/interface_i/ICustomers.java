package com.store.components.interface_i;

import java.util.List;
import com.store.components.entity.Customers;

public interface ICustomers {
	public Customers searchByIdCustomer(int id);

	List<Customers> infoAboutAllCustomer();

	public void addCustomer(Customers customer);
}
