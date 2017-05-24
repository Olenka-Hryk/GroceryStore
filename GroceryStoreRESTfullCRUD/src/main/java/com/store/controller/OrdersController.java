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
@RequestMapping("/flower/order")
public class OrdersController {
	@Autowired
	private OrdersService orderService;

	/**
	 * Get all orders from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public Collection<Orders> infoAboutAllOrder(@PathVariable("page") int page) {
		return orderService.infoAboutAllOrder(page);
	}

	/**
	 * Get totalsum orders from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view_summa/{id}", method = RequestMethod.GET)
	public Orders infoAboutSumOfOrderById(@PathVariable("page") int page) {
		return orderService.infoAboutSumOfOrderById(page);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addOrders(@RequestBody Orders order) {
		orderService.addOrder(order);
		return "SUCCESS: Order was created!";
	}
}
