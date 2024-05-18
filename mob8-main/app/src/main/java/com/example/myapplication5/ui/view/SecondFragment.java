package com.example.myapplication5.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.widget.TextView;

import android.view.View;
import android.widget.Button;

import com.example.myapplication5.R;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication5.model.products;
import com.example.myapplication5.viewModels.ProductViewModel;
import com.example.myapplication5.viewModels.OrderViewModel;

import com.example.myapplication5.data_pac.Orders;

import java.util.ArrayList;
import java.util.Objects;


public class SecondFragment extends Fragment {
    Orders order;
    Button secondFragmentButton1;
    Button secondFragmentButton2;
    TextView second_fragment_text_view;

    public SecondFragment() {super(R.layout.fragment_second);}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //к листу
        secondFragmentButton2 = (Button) getActivity().findViewById(R.id.fragment_second_button2);

        //Информация о товаре

        second_fragment_text_view = getActivity().findViewById(R.id.fragment_second_text_view2);

        ProductViewModel productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        OrderViewModel orderViewModel = new ViewModelProvider(getActivity()).get(OrderViewModel.class);
        productViewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            String info = uiState.getCurrentProductName() + " " + uiState.getCurrentProductAmount();
            second_fragment_text_view.setText(info);
        });

        secondFragmentButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(orderViewModel.getUiState().getValue()).getOrderedPositions() == null)
                    orderViewModel.getUiState().getValue().createDatabase(getActivity().getApplicationContext(), null);

                ArrayList<products> products = orderViewModel.getUiState().getValue().getOrderedPositions();
                int id = 0;
                if (products != null) id = products.size();

                products productToInput = productViewModel.getUiState().getValue().getProduct();
                productToInput.setId(id);

                orderViewModel.addProductToOrder(productToInput);
                productViewModel.inputProductParameters(null, null);

                Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_thirdFragment);
            }
        });

    }
}