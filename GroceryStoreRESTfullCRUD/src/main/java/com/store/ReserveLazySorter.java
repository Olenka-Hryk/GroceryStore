package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Reserve;

public class ReserveLazySorter implements Comparator<Reserve> {

	private String sortField;
	private SortOrder sortOrder;

	public ReserveLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Reserve reserve1, Reserve reserve2) {
		try {
			Object value1 = Reserve.class.getField(this.sortField).get(reserve1);
			Object value2 = Reserve.class.getField(this.sortField).get(reserve2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}