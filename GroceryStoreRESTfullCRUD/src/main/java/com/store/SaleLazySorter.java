package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Sale;

public class SaleLazySorter implements Comparator<Sale> {

	private String sortField;
	private SortOrder sortOrder;

	public SaleLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Sale sale1, Sale sale2) {
		try {
			Object value1 = Sale.class.getField(this.sortField).get(sale1);
			Object value2 = Sale.class.getField(this.sortField).get(sale2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}