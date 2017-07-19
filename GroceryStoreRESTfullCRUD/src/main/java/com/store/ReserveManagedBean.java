package com.store;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.store.components.entity.Reserve;
import com.store.service.ReserveService;

@ManagedBean(name = "reserveManagedBean")
@ViewScoped
@Controller
public class ReserveManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Reserve> reserveLazyModel;
	private Reserve selectedReserve;

	@Autowired
	private ReserveService reserveService;

	@Autowired
	private Reserve reserve;

	@PostConstruct
	public void init() {
		List<Reserve> reserve = getReserveService().infoAboutProduct(0);
		reserveLazyModel = new ReserveLazyList(reserve);
	}

	public LazyDataModel<Reserve> getAllReserve() {
		return reserveLazyModel;
	}

	public ReserveService getReserveService() {
		return reserveService;
	}

	public void setReserveService(ReserveService reserveService) {
		this.reserveService = reserveService;
	}

	public Reserve getSelectedReserve() {
		return selectedReserve;
	}

	public void setSelectedReserve(Reserve selectedReserve) {
		this.selectedReserve = selectedReserve;
	}

	public void addReserve(ActionEvent actionEvent) {
		reserveService.addProduct(reserve);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB",
				"Adding a new reserve has passed successfully"));
		init();
	}

	public void deleteReserve() {
		reserveService.deleteByIdProduct(selectedReserve.getIdProduct());
		selectedReserve = null;
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete from DB", "Delete reserve successfully!"));
		init();
	}
	
	public void onRowEdit(RowEditEvent event) {
		reserveService.updateProduct(selectedReserve);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Reserve Edited successfully!", String.valueOf((((Reserve) event.getObject()).getId())));
        FacesContext.getCurrentInstance().addMessage(null, msg);       
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Edit Cancelled!", String.valueOf((((Reserve) event.getObject()).getId())));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed!", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}