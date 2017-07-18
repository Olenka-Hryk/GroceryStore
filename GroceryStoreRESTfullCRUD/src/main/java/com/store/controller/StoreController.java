package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Store;
import com.store.service.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {
	@Autowired
	private StoreService storeService;

	/**
	 * Get store by id from DB
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Store searchByIdStore(@PathVariable("id") int id) {
		return storeService.searchByIdStore(id);
	}

	/**
	 * Get all stores from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/information/{page}", method = RequestMethod.GET)
	public Collection<Store> infoAboutStore(@PathVariable("page") int page) {
		return storeService.infoAboutStore(page);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addStore(@RequestBody Store store) {
		storeService.addStore(store);
		return "SUCCESS: Store was created!";
	}
}
