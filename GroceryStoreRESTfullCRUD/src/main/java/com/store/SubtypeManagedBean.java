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

import com.store.components.entity.Subtypes;
import com.store.service.SubtypesService;

@ManagedBean(name = "subtypeManagedBean")
@ViewScoped
@Controller
public class SubtypeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Subtypes> subtypesLazyModel;

	@Autowired
	private SubtypesService subtypesService;

	@Autowired
	private Subtypes subtype;
	
	@PostConstruct
	public void init() {
		List<Subtypes> subtypes = getSubtypesService().infoAboutAllSubtype();
		subtypesLazyModel = new SubtypeLazyList(subtypes);
	}

	public LazyDataModel<Subtypes> getAllSubtypes() {
		return subtypesLazyModel;
	}

	public SubtypesService getSubtypesService() {
		return subtypesService;
	}

	public void setSubtypesService(SubtypesService subtypesService) {
		this.subtypesService = subtypesService;
	}
	
	public void addSubtype(ActionEvent actionEvent) {
		subtypesService.addSubtype(subtype);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new subtype has passed successfully"));
	}
}
