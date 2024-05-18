package com.example.myapplication5.domain;


import com.example.myapplication5.data_source.Room.Entities.ProductEntity;
import com.example.myapplication5.model.products;

import java.util.ArrayList;
import java.util.List;

public class ProductEntityToProductConverter {
    public static products convert(ProductEntity productEntity) {
        products result = new products(productEntity.productName, productEntity.productAmount);
        result.setId(productEntity.id);

        return result;
    }

    public static List<products> convertList(List<ProductEntity> productEntities) {
        List<products> resultList = new ArrayList<>();

        for (ProductEntity productEntity : productEntities)
            resultList.add(convert(productEntity));

        return resultList;
    }
}