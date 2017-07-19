package com.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.store.components.entity.Orders;

public class OrdersLazyList extends LazyDataModel<Orders> {

    private static final long serialVersionUID = 1L;

    private List<Orders> datasource;

    
    public OrdersLazyList(List<Orders> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Orders getRowData(String rowKey) {
        for(Orders orders : datasource) {
        	String stringOrderId = String.valueOf(orders.getId()); 
            if(stringOrderId.equals(rowKey))
                return orders;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(Orders orders) {
        return orders.getId();
    }
 
    @Override
    public List<Orders> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Orders> data = new ArrayList<Orders>();
 
        //filter
        for(Orders order : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(order.getClass().getField(filterProperty).get(order));
 
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
                data.add(order);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new OrdersLazySorter(sortField, sortOrder));
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