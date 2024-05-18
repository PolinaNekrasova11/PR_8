package com.example.myapplication5.repository;

import android.content.Context;

import com.example.myapplication5.data_source.SP.SharedPreferencesDataSource;
import com.example.myapplication5.data_source.product_dataSource;
import com.example.myapplication5.model.products;

import java.io.Serializable;

public class productRep implements productInterf, Serializable {
    private SharedPreferencesDataSource currentProductInfoManager;
    public productRep() {}

    public void createCurrentInfoManager(Context context) {
        if (currentProductInfoManager == null)
            currentProductInfoManager = new SharedPreferencesDataSource(context);
    }

    public String getCurrentProductName() {
        if (currentProductInfoManager == null) return null;
        else return currentProductInfoManager.getStringRecord("R.string.ProductName");
    }

    public String getCurrentProductAmount() {
        if (currentProductInfoManager == null) return null;
        else return currentProductInfoManager.getStringRecord("R.string.ProductAmount");
    }

    public void setCurrentProductName(String productName) {
        if (currentProductInfoManager == null) return;
        else currentProductInfoManager.writeContent("ProductName", productName);
    }
    public void setCurrentProductAmount(String productAmount) {
        if (currentProductInfoManager == null) return;
        else currentProductInfoManager.writeContent("ProductAmount", productAmount);
    }
    public products getProduct() {
        if (currentProductInfoManager == null) return null;
        else return new products(
                currentProductInfoManager.getStringRecord("R.string.ProductName"),
                currentProductInfoManager.getStringRecord("R.string.ProductAmount")
        );
    }
}
