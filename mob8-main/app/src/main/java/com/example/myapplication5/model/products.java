package com.example.myapplication5.model;

import com.example.myapplication5.repository.productInterf;

import java.io.Serializable;


public class products implements Serializable {
        private String productName;
        private String productAmount;
        private int id;


    public products() {}
        public products(String productName, String productAmount) {
            this.productName = productName;
            this.productAmount = productAmount;
        }

        public String getProductName() {
            return productName;
        }

        public String getProductAmount() {
            return productAmount;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setProductAmount(String productAmount) {
            this.productAmount = productAmount;
        }
        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

