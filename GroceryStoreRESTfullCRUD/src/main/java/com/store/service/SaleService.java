package com.store.service;

import java.util.List;

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

	public List<Sale> infoAboutAllSale() {
		return iSale.infoAboutAllSale();
	}

	public void addSale(Sale sale) {
		iSale.addSale(sale);
	}
}
