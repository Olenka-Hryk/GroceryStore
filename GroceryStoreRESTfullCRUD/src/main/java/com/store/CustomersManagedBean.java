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

import com.store.components.entity.Customers;
import com.store.service.CustomersService;

@ManagedBean(name = "customersManagedBean")
@ViewScoped
@Controller
public class CustomersManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Customers> customersLazyModel;

	@Autowired
	private CustomersService customersService;
	
	@Autowired
	private Customers customer;

	@PostConstruct
	public void init() {
		List<Customers> customers = getCustomersService().infoAboutCustomer(0);
		customersLazyModel = new CustomersLazyList(customers);
	}

	public LazyDataModel<Customers> getAllCustomers() {
		return customersLazyModel;
	}

	public CustomersService getCustomersService() {
		return customersService;
	}

	public void setCustomersService(CustomersService customersService) {
		this.customersService = customersService;
	}
	
	public void addCustomer(ActionEvent actionEvent) {
		customersService.addCustomer(customer);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new customer has passed successfully"));
	}
}