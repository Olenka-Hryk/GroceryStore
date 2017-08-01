package com.store;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.store.components.entity.TimeTable;
import com.store.service.TimeTableService;

@ManagedBean(name = "timeTableManagedBean")
@ViewScoped
@Controller
public class TimeTableManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<TimeTable> timetableLazyModel;

	@Autowired
	private TimeTableService timetableService;

	@PostConstruct
	public void init() {
		List<TimeTable> timetable = getTimeTableService().infoAboutAllTimeTable();
		timetableLazyModel = new TimeTableLazyList(timetable);
	}

	public LazyDataModel<TimeTable> getAllTimeTable() {
		return timetableLazyModel;
	}

	public TimeTableService getTimeTableService() {
		return timetableService;
	}

	public void setTimeTableService(TimeTableService timetableService) {
		this.timetableService = timetableService;
	}
}
