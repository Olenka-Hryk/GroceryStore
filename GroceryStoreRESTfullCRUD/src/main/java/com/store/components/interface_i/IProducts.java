package com.store.components.interface_i;

import java.util.List;

import com.store.components.entity.Products;

public interface IProducts {
	public Products searchByIdProducts(int id);

	List<Products> infoAboutAllProducts();
	public void addProduct(Products product);
}
