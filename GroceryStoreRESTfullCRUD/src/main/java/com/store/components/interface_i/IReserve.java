package com.store.components.interface_i;

import java.util.List;

import com.store.components.entity.Reserve;

public interface IReserve {
	public Reserve searchByIdProduct(int id);
	
	List<Reserve> infoAboutAllProduct();

	public void addProduct(Reserve product);
	
	public void deleteByIdProduct(int id);

	public void updateProduct(Reserve product);
}
