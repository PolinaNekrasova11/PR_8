package com.example.myapplication5.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;

import com.example.myapplication5.R;


import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.myapplication5.repository.productInterf;
import com.example.myapplication5.model.products;

public class FourFragment extends Fragment {
    Button goBack;
    TextView goodInfo;

    public FourFragment() {
        super(R.layout.fragment_four);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goodInfo = (TextView) getActivity().findViewById(R.id.fragment_fourth_text_view2);
        goBack = (Button) getActivity().findViewById(R.id.fragment_fourth_button);

        Bundle args = getArguments();
        products product = null;

        if (args != null) {
            product = (products) args.getSerializable("Product");
            String info =  product.getProductName() + " " + product.getProductAmount() ;
            goodInfo.setText(info);
        } else {
            Toast.makeText(getActivity(), "An error with products!", Toast.LENGTH_SHORT).show();
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_fourthFragment_to_thirdFragment);
            }
        });
    }
}