package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Products;

public class ProductsLazyList extends LazyDataModel<Products> {

    private static final long serialVersionUID = 1L;

    private List<Products> datasource;
    
    public ProductsLazyList(List<Products> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Products getRowData(String rowKey) {
        for(Products products : datasource) {
        	String stringProductId = String.valueOf(products.getId()); 
            if(stringProductId.equals(rowKey))
                return products;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Products products) {
        return products.getId();
    }
 
    @Override
    public List<Products> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Products> data = new ArrayList<Products>();
 
        //filter
        for(Products product : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(product.getClass().getField(filterProperty).get(product));
 
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
                data.add(product);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new ProductsLazySorter(sortField, sortOrder));
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