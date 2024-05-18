// MainFragment.java
package com.example.myapplication5.ui.view;



import com.example.myapplication5.R;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import androidx.lifecycle.ViewModelProvider;


import com.example.myapplication5.model.products;
import com.example.myapplication5.repository.FilesRepository;
import com.example.myapplication5.repository.FilesRepositoryClass;
import com.example.myapplication5.viewModels.ProductViewModel;
import com.example.myapplication5.viewModels.OrderViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private final int PERMISSION_REQUEST_CODE = 70;
    Button main_fragment_button1;
    Button main_fragment_button2;
    Button main_fragment_button3; //кнопка для сохранения записей из бд в файл ASDS
    Button main_fragment_button4; //кнопка для сохранения записей из бд в файл CFDS
    TextView main_fragment_text_view;
    public MainFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        main_fragment_button1 = (Button) getActivity().findViewById(R.id.fragment_main_button1);
        main_fragment_button2 = (Button) getActivity().findViewById(R.id.fragment_main_button2);
        main_fragment_button3 = (Button) getActivity().findViewById(R.id.fragment_main_button3);
        main_fragment_button4 = (Button) getActivity().findViewById(R.id.fragment_main_button4);
        main_fragment_text_view = (TextView) getActivity().findViewById(R.id.text_view);
        OrderViewModel order = new ViewModelProvider(getActivity()).get(OrderViewModel.class);

        order.createOrder(getActivity().getApplicationContext(), null);
        order.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            int arrayLength;
            if (uiState != null && uiState.getOrderedPositions() != null)
                arrayLength = uiState.getOrderedPositions().size();
            else
                arrayLength = 0;
            String info = "There are products: " + Integer.toString(arrayLength);
            main_fragment_text_view.setText(info);
        });
        main_fragment_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_firstFragment);
            }
        });
        main_fragment_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_thirdFragment);
            }
        });
        ProductViewModel productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        productViewModel.getUiState().getValue().createCurrentInfoManager(getActivity().getApplicationContext());
        productViewModel.getUiState().getValue().setCurrentProductAmount("");
        productViewModel.getUiState().getValue().setCurrentProductName("");



        FilesRepository filesRepository = new FilesRepositoryClass(
                getActivity().getApplicationContext(),
                "dbHistoryASDS",
                "dbHistory"
        );

        if (main_fragment_text_view.getText().equals("There are products: 0")) {
            main_fragment_button3.setVisibility(View.GONE);
            main_fragment_button4.setVisibility(View.GONE);
        } else {
            main_fragment_button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder dbStringBuilder = new StringBuilder();
                    ArrayList<products> orderList = order.getUiState().getValue().getOrderedPositions();
                    if (orderList != null && orderList.size() > 0) {
                        for (products product : orderList) {
                            dbStringBuilder.append("id: ").append(product.getId())
                                    .append("; name: ")
                                    .append(product.getProductName())
                                    .append("; amount: ")
                                    .append(product.getProductAmount())
                                    .append(";\n");
                        }

                        filesRepository.writeIntoAppSpecDS(dbStringBuilder.toString());

                        String result = filesRepository.readFromAppSpecDS();

                        if (result != null)
                            Toast.makeText(getActivity(), "В файл AppSpecificDS было записано символов: " + result.length(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            main_fragment_button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder dbStringBuilder = new StringBuilder();
                    ArrayList<products> orderList = order.getUiState().getValue().getOrderedPositions();

                    if (orderList != null && orderList.size() > 0) {
                        for (products product : orderList) {
                            dbStringBuilder.append("id: ")
                                    .append(product.getId())
                                    .append("; name: ")
                                    .append(product.getProductName()).append("; amount: ")
                                    .append(product.getProductAmount())
                                    .append(";\n");
                        }

                        if (!filesRepository.writeIntoCommonFilesDS(dbStringBuilder.toString())) {
                            requestPermission();
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            filesRepository.writeIntoCommonFilesDS(dbStringBuilder.toString());
                        }

                        String result = filesRepository.readFromCommonFilesDS();
                        if (result != null)
                            Toast.makeText(getActivity(), "В файл CommonFilesDS было записано символов: " + result.length(), Toast.LENGTH_SHORT)
                                    .show();
                    }
                }

            });
        }
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Log.e("value", "Permission Granted, Now you can use local drive .");
                }
                break;
        }
    }

}