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

import com.store.components.entity.Sale;
import com.store.service.SaleService;

@ManagedBean(name = "saleManagedBean")
@ViewScoped
@Controller
public class SaleManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Sale> saleLazyModel;

	@Autowired
	private SaleService saleService;
	
	@Autowired
	private Sale sale;

	@PostConstruct
	public void init() {
		List<Sale> sale = getSaleService().infoAboutSale(0);
		saleLazyModel = new SaleLazyList(sale);
	}

	public LazyDataModel<Sale> getAllSale() {
		return saleLazyModel;
	}

	public SaleService getSaleService() {
		return saleService;
	}

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	
	public void addSale(ActionEvent actionEvent) {
		saleService.addSale(sale);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new sale has passed successfully"));
	}
}