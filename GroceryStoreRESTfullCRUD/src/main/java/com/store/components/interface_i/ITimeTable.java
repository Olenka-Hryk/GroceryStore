package com.store.components.interface_i;

import java.util.Collection;
import com.store.components.entity.TimeTable;

public interface ITimeTable {
	public TimeTable searchByIdWorker(int idWorker);

	Collection<TimeTable> infoAboutTimeTable(int page);
}
