package com.store.components.interface_i;

import java.util.Collection;
import com.store.components.entity.Store;

public interface IStore {
	public Store searchByIdStore(int id);

	Collection<Store> infoAboutStore(int page);

	public void addStore(Store store);
}