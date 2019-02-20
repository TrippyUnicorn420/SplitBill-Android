package com.vita.splitbill.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vita.splitbill.entities.Restaurant;

import java.util.List;

// This is a Data Access Object (a DAO).
// This interface is the code that interfaces
// directly with the database, specifically the
// Restaurants table. All calls to the database have
// to go through the DAO somehow, because there's
// no other way to access the database without it.

@Dao
public interface RestaurantDao {

    @Query("SELECT * FROM Restaurants")
    LiveData<List<Restaurant>> getAllRestaurants();

    @Insert
    void insert(Restaurant restaurant);

    @Query("DELETE FROM Restaurants")
    void deleteAll();
}
