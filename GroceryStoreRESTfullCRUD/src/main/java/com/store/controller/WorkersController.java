package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Workers;
import com.store.service.WorkersService;

@RestController
@RequestMapping("/store/workers")
public class WorkersController {
	@Autowired
	private WorkersService workersService;

	/**
	 * Get workers by id from DB
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public Workers searchByIdWorker(@PathVariable("id") int id) {
		return workersService.searchByIdWorker(id);
	}

	/**
	 * Get all workers from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/information/{page}", method = RequestMethod.GET)
	public Collection<Workers> infoAboutWorker(@PathVariable("page") int page) {
		return workersService.infoAboutWorker(page);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addWorker(@RequestBody Workers worker) {
		workersService.addWorker(worker);
		return "SUCCESS: Worker was created!";
	}
}
