package com.example.myapplication5.data_source;
import com.example.myapplication5.model.products;


public class product_dataSource {
    private final products[] currentProduct = new products[1];
    public product_dataSource(products product) {this.currentProduct[0] = product;}

    public products getCurrentProduct() {
        return currentProduct[0];
    }

    public void SetCurrentProduct(products product) {
        this.currentProduct[0] = product;
    }
}
