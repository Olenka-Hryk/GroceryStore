package com.store.components.interface_i;

import java.util.List;

import com.store.components.entity.Cheque;

public interface ICheque {
	List<Cheque> infoAboutAllCheque();

	public Cheque infoAboutAmountOfOrderById(int id);
	public void addCheque(Cheque cheque);
}
