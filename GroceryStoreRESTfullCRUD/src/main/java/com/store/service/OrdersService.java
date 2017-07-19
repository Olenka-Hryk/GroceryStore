package com.store.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Orders;
import com.store.components.interface_i.IOrders;

@Service
public class OrdersService {
	@Autowired
	private IOrders iOrders;

	public Collection<Orders> infoAboutAllOrder(int page) {
		return iOrders.infoAboutAllOrder(page);
	}

	public Orders infoAboutSumOfOrderById(int id) {
		return iOrders.infoAboutSumOfOrderById(id);
	}
	
	public void addOrder(Orders order) {
		iOrders.addOrder(order);
	}
}
