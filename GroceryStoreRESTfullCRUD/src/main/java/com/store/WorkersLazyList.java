package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Workers;

public class WorkersLazyList extends LazyDataModel<Workers> {

    private static final long serialVersionUID = 1L;

    private List<Workers> datasource;

    
    public WorkersLazyList(List<Workers> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Workers getRowData(String rowKey) {
        for(Workers worker : datasource) {
        	String stringWorkerId = String.valueOf(worker.getId()); 
            if(stringWorkerId.equals(rowKey))
                return worker;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Workers worker) {
        return worker.getId();
    }
 
    @Override
    public List<Workers> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Workers> data = new ArrayList<Workers>();
 
        //filter
        for(Workers worker : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(worker.getClass().getField(filterProperty).get(worker));
 
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
                data.add(worker);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new WorkersLazySorter(sortField, sortOrder));
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