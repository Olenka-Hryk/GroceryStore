package com.store.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.TimeTable;
import com.store.components.interface_i.ITimeTable;

@Service
public class TimeTableService {

	@Autowired
	private ITimeTable iTimeTable;

	public TimeTable searchByIdWorker(int worker) {
		return iTimeTable.searchByIdWorker(worker);
	}

	public Collection<TimeTable> infoAboutTimeTable(int page) {
		return iTimeTable.infoAboutTimeTable(page);
	}
}