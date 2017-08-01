package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Workers;
import com.store.components.interface_i.IWorkers;

@Service
public class WorkersService {

	@Autowired
	private IWorkers iWorkers;

	public Workers searchByIdWorker(int id) {
		return iWorkers.searchByIdWorker(id);
	}

	public List<Workers> infoAboutAllWorker() {
		return iWorkers.infoAboutAllWorker();
	}

	public void addWorker(Workers worker) {
		iWorkers.addWorker(worker);
	}
}
