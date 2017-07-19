package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Customers;

public class CustomersLazyList extends LazyDataModel<Customers> {

    private static final long serialVersionUID = 1L;

    private List<Customers> datasource;

    
    public CustomersLazyList(List<Customers> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Customers getRowData(String rowKey) {
        for(Customers customer : datasource) {
        	String stringCustomerId = String.valueOf(customer.getId()); 
            if(stringCustomerId.equals(rowKey))
                return customer;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Customers customer) {
        return customer.getId();
    }
 
    @Override
    public List<Customers> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Customers> data = new ArrayList<Customers>();
 
        //filter
        for(Customers customer : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(customer.getClass().getField(filterProperty).get(customer));
 
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
                data.add(customer);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new CustomersLazySorter(sortField, sortOrder));
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