package com.store.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Cheque;
import com.store.components.interface_i.ICheque;

@Service
public class ChequeService {
	@Autowired
	private ICheque iCheque;

	public Collection<Cheque> infoAboutAllCheque(int page) {
		return iCheque.infoAboutAllCheque(page);
	}

	public Cheque infoAboutAmountOfOrderById(int id) {
		return iCheque.infoAboutAmountOfOrderById(id);
	}

	public void addCheque(Cheque cheque) {
		iCheque.addCheque(cheque);
	}
}