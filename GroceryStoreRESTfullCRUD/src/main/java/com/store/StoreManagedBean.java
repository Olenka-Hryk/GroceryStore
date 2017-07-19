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

import com.store.components.entity.Store;
import com.store.service.StoreService;

@ManagedBean(name = "storeManagedBean")
@ViewScoped
@Controller
public class StoreManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Store> storeLazyModel;

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private Store store;

	@PostConstruct
	public void init() {
		List<Store> store = getStoreService().infoAboutStore(0);
		storeLazyModel = new StoreLazyList(store);
	}

	public LazyDataModel<Store> getAllStore() {
		return storeLazyModel;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
	public void addStore(ActionEvent actionEvent) {
		storeService.addStore(store);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new store has passed successfully"));
	}
}