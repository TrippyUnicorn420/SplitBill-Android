package com.vita.splitbill.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vita.splitbill.entities.Food;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM Food WHERE SubMenuID = :SubMenuID")
    LiveData<List<Food>> bringTheFood(int SubMenuID);

    @Insert
    void insert(Food food);

    @Query("DELETE FROM Food")
    void deleteAll();

}
