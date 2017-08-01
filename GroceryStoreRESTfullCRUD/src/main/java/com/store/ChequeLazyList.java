package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Cheque;

public class ChequeLazyList extends LazyDataModel<Cheque> {

    private static final long serialVersionUID = 1L;

    private List<Cheque> datasource;

    
    public ChequeLazyList(List<Cheque> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Cheque getRowData(String rowKey) {
        for(Cheque cheque : datasource) {
        	String stringChequeId = String.valueOf(cheque.getId()); 
            if(stringChequeId.equals(rowKey))
                return cheque;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Cheque cheque) {
        return cheque.getId();
    }
 
    @Override
    public List<Cheque> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Cheque> data = new ArrayList<Cheque>();
 
        //filter
        for(Cheque cheque : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(cheque.getClass().getField(filterProperty).get(cheque));
 
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
                data.add(cheque);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new ChequeLazySorter(sortField, sortOrder));
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