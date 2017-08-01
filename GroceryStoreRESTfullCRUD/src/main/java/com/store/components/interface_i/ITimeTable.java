package com.store.components.interface_i;

import java.util.List;
import com.store.components.entity.TimeTable;

public interface ITimeTable {
	public TimeTable searchByIdWorker(int idWorker);

	List<TimeTable> infoAboutAllTimeTable();
}
