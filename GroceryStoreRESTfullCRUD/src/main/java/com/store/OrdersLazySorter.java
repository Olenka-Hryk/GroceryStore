package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Orders;

public class OrdersLazySorter implements Comparator<Orders> {

	private String sortField;
	private SortOrder sortOrder;

	public OrdersLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Orders order1, Orders order2) {
		try {
			Object value1 = Orders.class.getField(this.sortField).get(order1);
			Object value2 = Orders.class.getField(this.sortField).get(order2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}