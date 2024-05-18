package com.example.myapplication5.repository;
import android.content.Context;

import com.example.myapplication5.data_source.SP.SharedPreferencesDataSource;
import com.example.myapplication5.model.products;
public interface productInterf {


    public void createCurrentInfoManager(Context context);

    public String getCurrentProductName();


    public String getCurrentProductAmount() ;
    public void setCurrentProductName(String goodName) ;
    public void setCurrentProductAmount(String goodAmount);
    public products getProduct() ;

}
