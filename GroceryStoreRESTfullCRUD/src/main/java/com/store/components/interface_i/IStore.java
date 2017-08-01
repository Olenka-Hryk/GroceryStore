package com.store.components.interface_i;

import java.util.List;
import com.store.components.entity.Store;

public interface IStore {
	public Store searchByIdStore(int id);

	List<Store> infoAboutAllStore();

	public void addStore(Store store);
}
