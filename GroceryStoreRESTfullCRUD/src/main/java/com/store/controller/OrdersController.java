package com.store.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.components.entity.Orders;
import com.store.service.OrdersService;

@RestController
@RequestMapping("/store/order")
public class OrdersController {
	@Autowired
	private OrdersService orderService;

	/**
	 * Get all orders from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Collection<Orders> infoAboutAllOrder() {
		return orderService.infoAboutAllOrder();
	}

	/**
	 * Get totalsum orders from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view_summa/{id}", method = RequestMethod.GET)
	public Orders infoAboutSumOfOrderById(@PathVariable("id") int id) {
		return orderService.infoAboutSumOfOrderById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addOrders(@RequestBody Orders order) {
		orderService.addOrder(order);
		return "SUCCESS: Order was created!";
	}
}
