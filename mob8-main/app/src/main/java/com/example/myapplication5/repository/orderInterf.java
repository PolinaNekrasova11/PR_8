package com.example.myapplication5.repository;
import android.content.Context;

import com.example.myapplication5.model.products;

import java.util.ArrayList;

public interface orderInterf {

    ArrayList<products> getOrderedPositions();
    void setOrderedPositions(ArrayList<products> orderedPositions);
    void putProduct(products product);
    products getProduct(int position);
    void createDatabase(Context context, ArrayList<products> values);

    void cleanDatabase();





}
