package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Sale;
import com.store.service.SaleService;

@RestController
@RequestMapping("/store/sale")
public class SaleController {
	@Autowired
	private SaleService saleService;

	/**
	 * Get sale by id from DB
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Sale searchByIdSale(@PathVariable("id") int id) {
		return saleService.searchByIdSale(id);
	}

	/**
	 * Get all sale from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/information/all", method = RequestMethod.GET)
	public Collection<Sale> infoAboutAllSale() {
		return saleService.infoAboutAllSale();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addSale(@RequestBody Sale sale) {
		saleService.addSale(sale);
		return "SUCCESS: Sale was created!";
	}
}
