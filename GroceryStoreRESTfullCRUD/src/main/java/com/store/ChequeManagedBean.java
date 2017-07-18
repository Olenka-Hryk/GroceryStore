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

import com.store.components.entity.Cheque;
import com.store.service.ChequeService;

@ManagedBean(name = "chequeManagedBean")
@ViewScoped
@Controller
public class ChequeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Cheque> chequeLazyModel;

	@Autowired
	private ChequeService chequeService;
	
	@Autowired
	private Cheque cheque;

	@PostConstruct
	public void init() {
		List<Cheque> cheque = getChequeService().infoAboutAllCheque(0);
		chequeLazyModel = new ChequeLazyList(cheque);
	}

	public LazyDataModel<Cheque> getAllCheque() {
		return chequeLazyModel;
	}

	public ChequeService getChequeService() {
		return chequeService;
	}

	public void setChequeService(ChequeService chequeService) {
		this.chequeService = chequeService;
	}
	
	public void addCheque(ActionEvent actionEvent) {
		chequeService.addCheque(cheque);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new cheque has passed successfully"));
	}
	
}