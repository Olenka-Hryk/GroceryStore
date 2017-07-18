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

import com.store.components.entity.Orders;
import com.store.service.OrdersService;

@ManagedBean(name = "ordersManagedBean")
@ViewScoped
@Controller
public class OrdersManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<Orders> ordersLazyModel;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private Orders order;
	
	@PostConstruct
	public void init() {
		List<Orders> orders = getOrdersService().infoAboutAllOrder(0);
		ordersLazyModel = new OrdersLazyList(orders);
	}

	public LazyDataModel<Orders> getAllOrders() {
		return ordersLazyModel;
	}

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	public void addOrder(ActionEvent actionEvent) {
		ordersService.addOrder(order);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save into DB", "Adding a new order has passed successfully"));
	}
}