package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Cheque;
import com.store.service.ChequeService;

@RestController
@RequestMapping("/store/cheque")
public class ChequeController {
	@Autowired
	private ChequeService chequeService;

	/**
	 * Get all cheque from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public Collection<Cheque> infoAboutAllCheque(@PathVariable("page") int page) {
		return chequeService.infoAboutAllCheque(page);
	}

	/**
	 * Get amount cheque from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view_amount/{id}", method = RequestMethod.GET)
	public Cheque infoAboutAmountOfOrderById(@PathVariable("id") int id) {
		return chequeService.infoAboutAmountOfOrderById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addCheque(@RequestBody Cheque cheque) {
		chequeService.addCheque(cheque);
		return "SUCCESS: Cheque was created!";
	}
}
