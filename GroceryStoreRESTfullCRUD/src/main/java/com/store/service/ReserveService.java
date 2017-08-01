package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Reserve;
import com.store.components.interface_i.IReserve;

@Service
public class ReserveService {
	@Autowired
	public IReserve iReserve;

	public Reserve searchByIdProduct(int id) {
		return iReserve.searchByIdProduct(id);
	}
	public List<Reserve> infoAboutAllProduct() {
		return iReserve.infoAboutAllProduct();
	}
	public void addProduct(Reserve product) {
		iReserve.addProduct(product);
	}

	public void deleteByIdProduct(int id) {
		iReserve.deleteByIdProduct(id);
	}

	public void updateProduct(Reserve product) {
		iReserve.updateProduct(product);
	}
}
