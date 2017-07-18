package com.store.components.interface_i;

import java.util.Collection;

import com.store.components.entity.Products;

public interface IProducts {
	public Products searchByIdProducts(int id);

	Collection<Products> infoAboutProducts(int page);
	public void addProduct(Products product);
}
