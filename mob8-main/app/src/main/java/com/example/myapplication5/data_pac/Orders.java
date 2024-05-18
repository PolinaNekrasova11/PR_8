package com.example.myapplication5.data_pac;

import android.os.Parcelable;

import java.io.Serializable;

public class Orders implements Serializable {
    private String productName;
    private String productAmount;

    public Orders() {}

    public Orders(String productName, String productAmount) {
        this.productName = productName;
        this.productAmount = productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductAmount() {
        return productAmount;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }
}