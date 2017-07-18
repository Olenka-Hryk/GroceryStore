package com.store;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Products;

public class ProductsLazySorter implements Comparator<Products> {

	private String sortField;
	private SortOrder sortOrder;

	public ProductsLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Products product1, Products product2) {
		try {
			Object value1 = Products.class.getField(this.sortField).get(product1);
			Object value2 = Products.class.getField(this.sortField).get(product2);

			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}