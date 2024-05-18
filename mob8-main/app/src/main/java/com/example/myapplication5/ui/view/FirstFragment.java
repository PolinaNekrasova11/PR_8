// FirstFragment.java
package com.example.myapplication5.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication5.R;

import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication5.viewModels.ProductViewModel;

public class FirstFragment extends Fragment {

    EditText first_fragment_edit_text1;
    EditText first_fragment_edit_text2;
    Button first_fragment_button;

    public FirstFragment() {super(R.layout.fragment_first);}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        first_fragment_button = (Button) getActivity().findViewById(R.id.fragment_first_button);
        first_fragment_edit_text1 = (EditText) getActivity().findViewById(R.id.fragment_first_edit_text1);
        first_fragment_edit_text2 = (EditText) getActivity().findViewById(R.id.fragment_first_edit_text2);
        ProductViewModel productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);


        productViewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            if (uiState.getCurrentProductAmount() != null && uiState.getCurrentProductAmount() != null) {
                first_fragment_edit_text1.setText(uiState.getCurrentProductName());
                first_fragment_edit_text2.setText(uiState.getCurrentProductAmount());
            }
        });


        first_fragment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_name = first_fragment_edit_text1.getText().toString();
                String product_amount = first_fragment_edit_text2.getText().toString();
                productViewModel.inputProductParameters(product_name, product_amount);
                Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_SecondFragment);


            }
        });
    }

}