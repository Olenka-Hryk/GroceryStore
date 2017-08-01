package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.TimeTable;
import com.store.service.TimeTableService;

@RestController
@RequestMapping("/store/timetable")
public class TimeTableController {
	@Autowired
	private TimeTableService timetableService;

	/**
	 * Get timetable by id from DB
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public TimeTable searchByIdWorker(@PathVariable("id") int id) {
		return timetableService.searchByIdWorker(id);
	}

	/**
	 * Get all timetable from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/information/{page}", method = RequestMethod.GET)
	public Collection<TimeTable> infoAboutAllTimeTable() {
		return timetableService.infoAboutAllTimeTable();
	}
}

