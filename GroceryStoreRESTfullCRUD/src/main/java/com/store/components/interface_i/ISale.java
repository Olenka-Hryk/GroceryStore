package com.store.components.interface_i;

import java.util.Collection;
import com.store.components.entity.Sale;

public interface ISale {
	public Sale searchByIdSale(int id);

	Collection<Sale> infoAboutSale(int page);

	public void addSale(Sale sale);
}
