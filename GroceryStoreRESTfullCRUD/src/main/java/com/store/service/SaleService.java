package com.store.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Sale;
import com.store.components.interface_i.ISale;

@Service
public class SaleService {

	@Autowired
	private ISale iSale;

	public Sale searchByIdSale(int id) {
		return iSale.searchByIdSale(id);
	}

	public Collection<Sale> infoAboutSale(int page) {
		return iSale.infoAboutSale(page);
	}

	public void addSale(Sale sale) {
		iSale.addSale(sale);
	}
}