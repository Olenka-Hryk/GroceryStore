package com.store.components.interface_i;

import java.util.Collection;
import com.store.components.entity.Orders;

public interface IOrders {
	Collection<Orders> infoAboutAllOrder(int page);

	public Orders infoAboutCountOfProductById(int id);
	public void addOrder(Orders order);
}
