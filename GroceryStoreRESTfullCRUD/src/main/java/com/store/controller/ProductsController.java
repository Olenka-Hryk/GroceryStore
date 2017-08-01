package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Products;
import com.store.service.ProductsService;

@RestController
@RequestMapping("/store/products")
public class ProductsController {
	@Autowired
	private ProductsService productsService;

	/**
	 * Get products by id from DB
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Products searchByIdProducts(@PathVariable("id") int id) {
		return productsService.searchByIdProducts(id);
	}

	/**
	 * Get all products from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/information/all", method = RequestMethod.GET)
	public Collection<Products> infoAboutAllProducts() {
		return productsService.infoAboutAllProducts();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addProduct(@RequestBody Products product) {
		productsService.addProduct(product);
		return "SUCCESS: Product was created!";
	}
}
