package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.TimeTable;

public class TimeTableLazyList extends LazyDataModel<TimeTable> {

    private static final long serialVersionUID = 1L;

    private List<TimeTable> datasource;

    
    public TimeTableLazyList(List<TimeTable> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public TimeTable getRowData(String rowKey) {
        for(TimeTable timetable : datasource) {
        	String stringTimeTableId = String.valueOf(timetable.getId()); 
            if(stringTimeTableId.equals(rowKey))
                return timetable;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(TimeTable timetable) {
        return timetable.getId();
    }
 
    @Override
    public List<TimeTable> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<TimeTable> data = new ArrayList<TimeTable>();
 
        //filter
        for(TimeTable timetable : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(timetable.getClass().getField(filterProperty).get(timetable));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(timetable);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new TimeTableLazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}