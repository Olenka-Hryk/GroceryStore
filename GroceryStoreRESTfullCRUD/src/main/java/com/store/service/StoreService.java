package com.store.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Store;
import com.store.components.interface_i.IStore;

@Service
public class StoreService {

	@Autowired
	private IStore iStore;

	public Store searchByIdStore(int id) {
		return iStore.searchByIdStore(id);
	}

	public Collection<Store> infoAboutStore(int page) {
		return iStore.infoAboutStore(page);
	}

	public void addStore(Store store) {
		iStore.addStore(store);
	}
}
