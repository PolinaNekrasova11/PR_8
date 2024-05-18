package com.example.myapplication5.repository;


import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.myapplication5.data_source.Room.CoreAppDataBase;
import com.example.myapplication5.data_source.Room.DAO.ProductDAO;
import com.example.myapplication5.data_source.Room.Entities.ProductEntity;
import com.example.myapplication5.data_source.order_dataSource;
import com.example.myapplication5.model.products;

import com.example.myapplication5.domain.ProductEntityToProductConverter;
import com.example.myapplication5.domain.ProductToProductEntityConverter;

public class OrderRep implements orderInterf {

    public OrderRep() {}

    private CoreAppDataBase db;
    @Override
    public void createDatabase(Context context, ArrayList<products> values) {
        if (db != null) return;

        db = Room.databaseBuilder(context,
                CoreAppDataBase.class, "products").allowMainThreadQueries().build();
        ProductDAO productDAO = db.productDAO();

        if (values != null && values.size() > 0)
            productDAO.insertAll((ArrayList<ProductEntity>) ProductToProductEntityConverter.convertList(values));
    }

    @Override
    public ArrayList<products> getOrderedPositions() {
        if (db == null) return null;

        ProductDAO productDAO = db.productDAO();
        if (productDAO.countRecords() > 0)
            return (ArrayList<products>) ProductEntityToProductConverter
                    .convertList(productDAO.getAll());
        return null;
    }
    public void setOrderedPositions(ArrayList<products> orderedPositions) {
        if (db == null) return;

        ProductDAO productDAO = db.productDAO();
        if (orderedPositions == null || orderedPositions.size() == 0) return;

        if (productDAO.countRecords() > 0) productDAO.cleanTable();

        productDAO.insertAll((ArrayList<ProductEntity>) ProductToProductEntityConverter.convertList(orderedPositions));
    }
    @Override
    public void putProduct(products product) {
        if (db == null) return;
        if (product == null) return;

        ProductDAO productDAO = db.productDAO();
        productDAO.insert(ProductToProductEntityConverter.convert(product));
    }
    @Override
    public products getProduct(int id) {
        if (db == null) return null;

        ProductDAO productDAO = db.productDAO();
        return ProductEntityToProductConverter.convert(productDAO.findById(id));
    }
    @Override
    public void cleanDatabase() {
        if (db == null) return;

        ProductDAO productDAO = db.productDAO();
        productDAO.cleanTable();
    }
}