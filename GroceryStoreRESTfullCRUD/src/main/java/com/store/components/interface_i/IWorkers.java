package com.store.components.interface_i;

import java.util.List;
import com.store.components.entity.Workers;

public interface IWorkers {

	Workers searchByIdWorker(int id);

	List<Workers> infoAboutAllWorker();

	void addWorker(Workers worker);
}
