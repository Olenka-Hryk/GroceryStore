package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Sale;

public class SaleLazyList extends LazyDataModel<Sale> {

    private static final long serialVersionUID = 1L;

    private List<Sale> datasource;

    
    public SaleLazyList(List<Sale> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Sale getRowData(String rowKey) {
        for(Sale sale : datasource) {
        	String stringSaleId = String.valueOf(sale.getId()); 
            if(stringSaleId.equals(rowKey))
                return sale;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Sale sale) {
        return sale.getId();
    }
 
    @Override
    public List<Sale> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Sale> data = new ArrayList<Sale>();
 
        //filter
        for(Sale sale : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(sale.getClass().getField(filterProperty).get(sale));
 
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
                data.add(sale);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new SaleLazySorter(sortField, sortOrder));
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