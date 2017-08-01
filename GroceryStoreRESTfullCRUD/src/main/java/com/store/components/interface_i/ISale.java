package com.store.components.interface_i;

import java.util.List;
import com.store.components.entity.Sale;

public interface ISale {
	public Sale searchByIdSale(int id);

	List<Sale> infoAboutAllSale();

	public void addSale(Sale sale);
}
