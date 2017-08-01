package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Subtypes;

public class SubtypeLazySorter implements Comparator<Subtypes> {

	private String sortField;
	private SortOrder sortOrder;

	public SubtypeLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Subtypes subtype1, Subtypes subtype2) {
		try {
			Object value1 = Subtypes.class.getField(this.sortField).get(subtype1);
			Object value2 = Subtypes.class.getField(this.sortField).get(subtype2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}