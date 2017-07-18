package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Customers;

public class CustomersLazySorter implements Comparator<Customers> {

	private String sortField;
	private SortOrder sortOrder;

	public CustomersLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Customers customer1, Customers customer2) {
		try {
			Object value1 = Customers.class.getField(this.sortField).get(customer1);
			Object value2 = Customers.class.getField(this.sortField).get(customer2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}