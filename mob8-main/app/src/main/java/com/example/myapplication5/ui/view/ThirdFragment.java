package com.example.myapplication5.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication5.R;


import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication5.model.products;
import com.example.myapplication5.viewModels.OrderViewModel;


import java.util.List;

public class ThirdFragment extends Fragment {
    public static class SecondFragmentRecyclerViewAdapter extends
            RecyclerView.Adapter <SecondFragmentRecyclerViewAdapter.ViewHolder>{
        private final LayoutInflater inflater;
        private final List<products> items;

        //
        private OnItemClicked onClick;

        public interface OnItemClicked {
            void onItemClick(int position);
        }
        //

        SecondFragmentRecyclerViewAdapter(Context context, List<products>
                items) {
            this.items = items;
            this.inflater = LayoutInflater.from(context);
        }
        @Override
        public SecondFragmentRecyclerViewAdapter.ViewHolder
        onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_item, parent,
                    false);
            return new ViewHolder(view);
        }
        @Override
        public void
        onBindViewHolder(SecondFragmentRecyclerViewAdapter.ViewHolder
                                 holder, int position) {
            products item = items.get(position);
            holder.textView1.setText(item.getProductName());
            holder.textView2.setText(item.getProductAmount());
            String number = Integer.toString(position + 1);
            holder.textView3.setText(number);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(holder.getAdapterPosition());
                }
            });
        }
        @Override
        public int getItemCount() {
            return items.size();
        }
        public class ViewHolder extends
                RecyclerView.ViewHolder {
            final TextView textView1;
            final TextView textView2;
            final TextView textView3;
            ViewHolder(View view){
                super(view);
                textView1 = view.findViewById(R.id.first_fragment_list_view_item_product_name);
                textView2 = view.findViewById(R.id.first_fragment_list_view_item_product_amount);
                textView3 = view.findViewById(R.id.first_fragment_list_view_item_number);
            }
        }
        //
        public void setOnClick(OnItemClicked onClick){
            this.onClick=onClick;
        }
        //
    }

    Button addMoreProductsButton;
    Button returnToMainFragment;
    Button deleteProductsButton;

    public ThirdFragment() {
        super(R.layout.fragment_third);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        returnToMainFragment = (Button) getActivity().findViewById(R.id.third_fragment_button);
        addMoreProductsButton = (Button) getActivity().findViewById(R.id.third_fragment_button);
        deleteProductsButton = (Button) getActivity().findViewById(R.id.third_fragment_button_clean);

        RecyclerView itemsList = getActivity().findViewById(R.id.third_fragment_recycler_view);

        OrderViewModel orderViewModel = new ViewModelProvider(getActivity()).get(OrderViewModel.class);

        orderViewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            List<products> items = uiState.getOrderedPositions();

            if (items == null || items.size() == 0) {
                itemsList.setVisibility(View.GONE);
                deleteProductsButton.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "There is no ordered products", Toast.LENGTH_SHORT).show();
            }
            else {
                if (itemsList.getVisibility() == View.GONE)
                    itemsList.setVisibility(View.VISIBLE);

                SecondFragmentRecyclerViewAdapter adapter = new SecondFragmentRecyclerViewAdapter(this.getContext(), items);
                LinearLayoutManager layoutManager = new
                        LinearLayoutManager(this.getContext().getApplicationContext());
                itemsList.setLayoutManager(layoutManager);

                adapter.setOnClick(new SecondFragmentRecyclerViewAdapter.OnItemClicked() {
                    @Override
                    public void onItemClick(int position) {
                        if (orderViewModel.getUiState().getValue() != null) {
                            products product = (products) orderViewModel.getUiState().getValue().getProduct(position);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Product", product);
                            navController.navigate(R.id.action_thirdFragment_to_fourthFragment, bundle);
                        }
                    }
                });

                itemsList.setAdapter(adapter);
            }
        });

        addMoreProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_thirdFragment_to_firstFragment);
            }
        });
        returnToMainFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_thirdFragment_to_mainFragment);
            }
        });
        deleteProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsList.setVisibility(View.GONE);
                orderViewModel.getUiState().getValue().cleanDatabase();
                deleteProductsButton.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "There is no purchased products", Toast.LENGTH_SHORT).show();
            }
        });

    }
}