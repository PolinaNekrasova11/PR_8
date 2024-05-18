package com.example.myapplication5.domain;

import com.example.myapplication5.data_source.Room.Entities.ProductEntity;
import com.example.myapplication5.model.products;

import java.util.ArrayList;
import java.util.List;

public class ProductToProductEntityConverter {
    public static ProductEntity convert(products product) {
        ProductEntity result = new ProductEntity();
        result.id = product.getId();
        result.productName = product.getProductName();
        result.productAmount = product.getProductAmount();
        return result;
    }

    public static List<ProductEntity> convertList(List<products > products) {
        List<ProductEntity> resultList = new ArrayList<>();

        for (products product : products) {
            ProductEntity tempPE = new ProductEntity();
            tempPE.id = product.getId();
            tempPE.productName = product.getProductName();
            tempPE.productAmount = product.getProductAmount();
            resultList.add(tempPE);
        }

        return resultList;
    }
}