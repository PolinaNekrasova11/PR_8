package com.example.myapplication5.data_source.Room.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductEntity {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo(name = "product_amount")
    public String productAmount;
}