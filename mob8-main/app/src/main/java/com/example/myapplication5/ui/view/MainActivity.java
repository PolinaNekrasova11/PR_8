package com.example.myapplication5.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication5.viewModels.ProductViewModel;
import com.example.myapplication5.viewModels.OrderViewModel;

import com.example.myapplication5.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductViewModel productView_Model = new ViewModelProvider(this).get(ProductViewModel.class);
        OrderViewModel orderView_Model = new ViewModelProvider(this).get(OrderViewModel.class);
    }

}