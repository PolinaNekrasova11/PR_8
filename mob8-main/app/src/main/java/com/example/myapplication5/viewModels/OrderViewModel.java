package com.example.myapplication5.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.repository.orderInterf;
import com.example.myapplication5.repository.OrderRep;
import com.example.myapplication5.model.products;


import java.util.ArrayList;
import java.util.Objects;

public class OrderViewModel extends ViewModel {
    private final MutableLiveData<orderInterf> uiState =
            new MutableLiveData<>(new OrderRep());
    public LiveData<orderInterf> getUiState() {
        return uiState;
    }
    public void createOrder(Context context, ArrayList<products> products) {
        Objects.requireNonNull(uiState.getValue()).createDatabase(context, products);
    }

    public void addProductToOrder(products product) {
        orderInterf order = uiState.getValue();

        if (order == null) return;
        else order.putProduct(product);

        uiState.setValue(
                order
        );
    }
}