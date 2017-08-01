package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Subtypes;
import com.store.service.SubtypesService;

@RestController
@RequestMapping("/store/subtypes")
public class SubtypesController {
	@Autowired
	private SubtypesService subtypesService;

	/**
	 * Get subtype by id from DB
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Subtypes searchByIdSubtype(@PathVariable("id") int id) {
		return subtypesService.searchByIdSubtype(id);
	}

	/**
	 * Get all subtype from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/information/all", method = RequestMethod.GET)
	public Collection<Subtypes> infoAboutAllSubtype() {
		return subtypesService.infoAboutAllSubtype();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addSubtype(@RequestBody Subtypes subtype) {
		subtypesService.addSubtype(subtype);
		return "SUCCESS: Subtype was created!";
	}
}

