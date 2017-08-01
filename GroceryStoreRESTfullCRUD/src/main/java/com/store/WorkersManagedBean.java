package com.store;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.store.components.entity.Workers;
import com.store.service.WorkersService;


@ManagedBean(name = "workersManagedBean")
@ViewScoped
@Controller
public class WorkersManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Workers> workersLazyModel;

	@Autowired
	private WorkersService workersService;
	
	@Autowired
	private Workers worker;

	@PostConstruct
	public void init() {
		List<Workers> workers = getWorkersService().infoAboutAllWorker();
		workersLazyModel = new WorkersLazyList(workers);
	}

	public LazyDataModel<Workers> getAllWorkers() {
		return workersLazyModel;
	}

	public WorkersService getWorkersService() {
		return workersService;
	}

	public void setWorkersService(WorkersService workersService) {
		this.workersService = workersService;
	}
	
	public void addWorker(ActionEvent actionEvent) {
		workersService.addWorker(worker);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new worker has passed successfully"));
	}
}
