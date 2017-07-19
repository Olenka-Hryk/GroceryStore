package com.store.components.interface_i;

import java.util.Collection;

import com.store.components.entity.Reserve;

public interface IReserve {
	public Reserve searchByIdProduct(int id);
	
	Collection<Reserve> infoAboutProduct(int page);

	public void addProduct(Reserve product);
	
	public void deleteByIdProduct(int id);

	public void updateProduct(Reserve product);
}
