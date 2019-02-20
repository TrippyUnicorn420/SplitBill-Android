package com.whatamidoingwithmylife.splitbill.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.whatamidoingwithmylife.splitbill.entities.Menu;

import java.util.List;

// This is a Data Access Object (a DAO).
// This interface is the code that interfaces
// directly with the database, specifically the
// Menus table. All calls to the database have
// to go through the DAO somehow, because there's
// no other way to access the database without it.

@Dao
public interface MenuDao {

    @Query("SELECT * FROM Menus WHERE ResID = :ResID")
    LiveData<List<Menu>> getAllMenus(int ResID);

    @Query("SELECT MenuTitle FROM Menus WHERE ResID = :ResID")
    LiveData<String[]> getAllMenuTitles(int ResID);

    @Insert
    void insert(Menu menu);

    @Query("DELETE FROM Menus")
    void deleteAll();
}
