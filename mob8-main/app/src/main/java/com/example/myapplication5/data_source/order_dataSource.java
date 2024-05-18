package com.example.myapplication5.data_source;
import com.example.myapplication5.model.products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class order_dataSource {
    private Map<Integer, products> order;
    public order_dataSource(Map<Integer, products> order) {
        this.order = order;
    }
    public ArrayList<products> getOrder() {
        return new ArrayList<products>(order.values());
    }
    public int putProductToOrder(products product) {
        if (order != null) {
            Set<Integer> set = order.keySet();
            if (set.size() > 0) {
                int maxId = Collections.max(set);
                order.put(maxId + 1, product);
                return maxId + 1;
            } else {
                int index = 0;
                order.put(index, product);
                return index;
            }
        } else {
            order = new HashMap<Integer, products>();
            int index = 0;
            order.put(index, product);
            return index;
        }
    }
    public products getProductById(int id) {return order != null ? order.get(id) : null;}
    public boolean updateProductById(int id, products product) {
        if (order == null || order.get(id) == null) return false;
        order.put(id, product);
        return true;
    }
    public boolean deleteProductById(int id) {
        if (order == null || order.get(id) == null) return false;
        order.remove(id);
        return true;
    }
}
