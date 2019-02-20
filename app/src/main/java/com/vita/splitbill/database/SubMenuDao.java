package com.vita.splitbill.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vita.splitbill.entities.SubMenu;

@Dao
public interface SubMenuDao {

    @Query("SELECT SubMenuTitle FROM SubMenus WHERE MenuID = :MenuID")
    LiveData<String[]> getSubMenus(int MenuID);

    @Query("SELECT COUNT(SubMenuID) FROM SubMenus WHERE MenuID = :MenuID")
    LiveData<String> getNumberOfSubMenus(int MenuID);

    @Insert
    void insert(SubMenu subMenu);

    @Query("DELETE FROM SubMenus")
    void deleteAll();

}
