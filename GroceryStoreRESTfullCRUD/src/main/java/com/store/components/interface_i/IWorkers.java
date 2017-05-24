package com.store.components.interface_i;

import java.util.Collection;
import com.store.components.entity.Workers;

public interface IWorkers {
	public Workers searchByIdWorker(int id);

	Collection<Workers> infoAboutWorker(int page);

	public void addWorker(Workers worker);
}
