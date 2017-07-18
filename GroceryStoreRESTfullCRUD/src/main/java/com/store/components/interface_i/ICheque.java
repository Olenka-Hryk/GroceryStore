package com.store.components.interface_i;

import java.util.Collection;
import com.store.components.entity.Cheque;

public interface ICheque {
	Collection<Cheque> infoAboutAllCheque(int page);

	public Cheque infoAboutAmountOfOrderById(int id);
	public void addCheque(Cheque cheque);
}
