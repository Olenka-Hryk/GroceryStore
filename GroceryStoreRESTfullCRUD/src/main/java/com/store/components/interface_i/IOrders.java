package com.store.components.interface_i;

import java.util.List;
import com.store.components.entity.Orders;

public interface IOrders {
	List<Orders> infoAboutAllOrder();

	public Orders infoAboutSumOfOrderById(int id);
	public void addOrder(Orders order);
}
