package com.vita.splitbill.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// This is the Restaurant object. It defines all
// the attributes that the CardViews on the splash
// page have, AND is also the table schema for the
// Restaurants table in the SQLite database.

// The @Entity annotation defines this object as a table in the database.
// @PrimaryKey specifies which field in the database is to be treated as
// the primary key.
// @ColumnInfo specifies what the column title in the database should be.

// Normally, you don't need accessors and mutators for this kind of object,
// since they will be seldom used, but Room requires them.

@Entity(tableName = "Restaurants")
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    private int ResID;

    @ColumnInfo(name = "ResName")
    private String Name;

    @ColumnInfo(name = "ResPicture")
    private int PictureID;

    public Restaurant(String Name, int PictureID) {
        this.Name = Name;
        this.PictureID = PictureID;
    }

    public int getPictureID() {
        return PictureID;
    }

    public void setPictureID(int pictureID) {
        PictureID = pictureID;
    }

    public String getName() {
        return Name;
    }

    public int getResID() {
        return ResID;
    }

    public void setResID(int resID) {
        ResID = resID;
    }

    public void setName(String name) {
        Name = name;
    }

}
