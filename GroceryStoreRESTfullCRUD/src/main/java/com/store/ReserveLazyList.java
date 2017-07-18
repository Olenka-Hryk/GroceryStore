package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Reserve;

public class ReserveLazyList extends LazyDataModel<Reserve> {

    private static final long serialVersionUID = 1L;

    private List<Reserve> datasource;

    
    public ReserveLazyList(List<Reserve> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Reserve getRowData(String rowKey) {
        for(Reserve reserve : datasource) {
        	String stringReserveId = String.valueOf(reserve.getId()); 
            if(stringReserveId.equals(rowKey))
                return reserve;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Reserve reserve) {
        return reserve.getId();
    }
 
    @Override
    public List<Reserve> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Reserve> data = new ArrayList<Reserve>();
 
        //filter
        for(Reserve reserve : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(reserve.getClass().getField(filterProperty).get(reserve));
 
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
                data.add(reserve);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new ReserveLazySorter(sortField, sortOrder));
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