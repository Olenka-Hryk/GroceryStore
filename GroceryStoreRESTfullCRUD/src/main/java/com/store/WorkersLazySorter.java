package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Workers;

public class WorkersLazySorter implements Comparator<Workers> {

	private String sortField;
	private SortOrder sortOrder;

	public WorkersLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Workers worker1, Workers worker2) {
		try {
			Object value1 = Workers.class.getField(this.sortField).get(worker1);
			Object value2 = Workers.class.getField(this.sortField).get(worker2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}