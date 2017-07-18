package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Cheque;

public class ChequeLazySorter implements Comparator<Cheque> {

	private String sortField;
	private SortOrder sortOrder;

	public ChequeLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Cheque cheque1, Cheque cheque2) {
		try {
			Object value1 = Cheque.class.getField(this.sortField).get(cheque1);
			Object value2 = Cheque.class.getField(this.sortField).get(cheque2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}