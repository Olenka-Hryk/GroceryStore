package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.TimeTable;

public class TimeTableLazySorter implements Comparator<TimeTable> {

	private String sortField;
	private SortOrder sortOrder;

	public TimeTableLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(TimeTable timetable1, TimeTable timetable2) {
		try {
			Object value1 = TimeTable.class.getField(this.sortField).get(timetable1);
			Object value2 = TimeTable.class.getField(this.sortField).get(timetable2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}