package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Store;

public class StoreLazyList extends LazyDataModel<Store> {

    private static final long serialVersionUID = 1L;

    private List<Store> datasource;

    
    public StoreLazyList(List<Store> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Store getRowData(String rowKey) {
        for(Store store : datasource) {
        	String stringStoreId = String.valueOf(store.getId()); 
            if(stringStoreId.equals(rowKey))
                return store;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Store store) {
        return store.getId();
    }
 
    @Override
    public List<Store> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Store> data = new ArrayList<Store>();
 
        //filter
        for(Store store : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(store.getClass().getField(filterProperty).get(store));
 
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
                data.add(store);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new StoreLazySorter(sortField, sortOrder));
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