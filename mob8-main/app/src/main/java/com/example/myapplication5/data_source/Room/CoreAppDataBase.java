package com.example.myapplication5.data_source.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication5.data_source.Room.DAO.ProductDAO;
import com.example.myapplication5.data_source.Room.Entities.ProductEntity;

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class CoreAppDataBase extends RoomDatabase {
    public abstract ProductDAO productDAO();
}