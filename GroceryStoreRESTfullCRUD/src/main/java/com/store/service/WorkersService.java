package com.store.service;

import java.util.Collection;

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

	public Collection<Workers> infoAboutWorker(int page) {
		return iWorkers.infoAboutWorker(page);
	}

	public void addWorker(Workers worker) {
		iWorkers.addWorker(worker);
	}
}