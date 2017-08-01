package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Products;
import com.store.components.interface_i.IProducts;

@Service
public class ProductsService {

	@Autowired
	private IProducts iProducts;

	public Products searchByIdProducts(int id) {
		return iProducts.searchByIdProducts(id);
	}

	public List<Products> infoAboutAllProducts() {
		return iProducts.infoAboutAllProducts();
	}

	public void addProduct(Products product) {
		iProducts.addProduct(product);
	}
}
