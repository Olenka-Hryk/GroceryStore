package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Subtypes;

public class SubtypeLazyList extends LazyDataModel<Subtypes> {

    private static final long serialVersionUID = 1L;

    private List<Subtypes> datasource;

    
    public SubtypeLazyList(List<Subtypes> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Subtypes getRowData(String rowKey) {
        for(Subtypes subtypes : datasource) {
        	String stringSubtypeId = String.valueOf(subtypes.getId()); 
            if(stringSubtypeId.equals(rowKey))
                return subtypes;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Subtypes subtypes) {
        return subtypes.getId();
    }
 
    @Override
    public List<Subtypes> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Subtypes> data = new ArrayList<Subtypes>();
 
        //filter
        for(Subtypes subtypes : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(subtypes.getClass().getField(filterProperty).get(subtypes));
 
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
                data.add(subtypes);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new SubtypeLazySorter(sortField, sortOrder));
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