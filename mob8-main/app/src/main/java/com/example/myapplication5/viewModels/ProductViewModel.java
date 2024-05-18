package com.example.myapplication5.viewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.repository.productInterf;
import com.example.myapplication5.repository.productRep;

import java.util.Objects;

public class ProductViewModel extends ViewModel {
    private final MutableLiveData<productInterf> uiState =
            new MutableLiveData<>(new productRep());
    public MutableLiveData<productInterf> getUiState() {
        return uiState;
    }


    public void createProduct(Context context) {
        Objects.requireNonNull(uiState.getValue()).createCurrentInfoManager(context);
    }

    public void inputProductParameters(String goodName, String goodAmount) {
        productInterf productRepository = uiState.getValue();
        if (productRepository != null) {
            productRepository.setCurrentProductName(goodName);
            productRepository.setCurrentProductAmount(goodAmount);
        }

        uiState.setValue(productRepository);
    }
}