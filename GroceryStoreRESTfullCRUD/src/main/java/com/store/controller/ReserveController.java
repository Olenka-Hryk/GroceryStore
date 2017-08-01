package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Reserve;
import com.store.service.ReserveService;

@RestController
@RequestMapping("/store/reserve")
public class ReserveController {
	@Autowired
	private ReserveService reserveService;

	/**
	 * Get product by id from DB
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Reserve searchByIdProduct(@PathVariable("id") int id) {
		return reserveService.searchByIdProduct(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createProduct(@RequestBody Reserve product) {
		reserveService.addProduct(product);
		return "SUCCESS: Product was created!";
	}

	/**
	 * Delete product by id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteByIdProduct(@PathVariable("id") int id) {
		reserveService.deleteByIdProduct(id);
		return "Product " + id + " was delete!";
	}

	/**
	 * Update information about product in DB
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String updateProduct(@RequestBody Reserve product) {
		this.reserveService.updateProduct(product);
		return "SUCCESS: Product was update!";
	}
}
