package com.whatamidoingwithmylife.splitbill.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

// This is the Menu object. It defines the
// names of the menus that show up in the Spinner in
// Menu activity, AND is also the table
// schema for the menus table in the SQLite database.

// The @Entity annotation defines this object as a table in the database.
// @PrimaryKey specifies which field in the database is to be treated as
// the primary key.
// @ColumnInfo specifies what the column title in the database should be.

// The table that is created as specified by this object also has a
// foreign key constraint. Room yells at me at every compile time to create
// an index for this table (since database changes may prompt full table
// rescans, which may slow the app down), but since the database will never
// change while the app is running, I ignore that warning.

// Normally, you don't need accessors and mutators for this kind of object,
// since they will be seldom used, but Room requires them.

@Entity(tableName = "Menus", foreignKeys = @ForeignKey(entity = Restaurant.class, parentColumns = "ResID", childColumns = "ResID", onDelete = CASCADE))
    // ↑ You've probably noticed that this entity declaration is longer than
    // normal. This table has a foreign key constraint to the Restaurants table.
    // The constraint is on the ResID column.
public class Menu {

    @PrimaryKey(autoGenerate = true)
    private int MenuID;

    @ColumnInfo(name = "MenuTitle")
    private String MenuTitle;

    @ColumnInfo(name = "ResID")
    private int ResID;



    public Menu(String MenuTitle, int ResID) {
        this.MenuTitle = MenuTitle;
        this.ResID = ResID;
    }

    public int getResID() {
        return ResID;
    }

    public void setResID(int resID) {
        ResID = resID;
    }

    public String getMenuTitle() {

        return MenuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        MenuTitle = menuTitle;
    }

    public int getMenuID() {

        return MenuID;
    }

    public void setMenuID(int menuID) {
        MenuID = menuID;
    }
}
