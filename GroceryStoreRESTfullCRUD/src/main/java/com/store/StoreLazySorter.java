package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Store;

public class StoreLazySorter implements Comparator<Store> {

	private String sortField;
	private SortOrder sortOrder;

	public StoreLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Store store1, Store store2) {
		try {
			Object value1 = Store.class.getField(this.sortField).get(store1);
			Object value2 = Store.class.getField(this.sortField).get(store2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}