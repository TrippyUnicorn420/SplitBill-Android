package com.whatamidoingwithmylife.splitbill.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Food", foreignKeys = @ForeignKey(entity = SubMenu.class, parentColumns = "SubMenuID", childColumns = "SubMenuID", onDelete = CASCADE))
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int FoodID;

    @ColumnInfo(name = "FoodTitle")
    private String FoodTitle;

    @ColumnInfo(name = "FoodInfo")
    private String FoodInfo;

    @ColumnInfo(name = "Price")
    private String Price;

    @ColumnInfo(name = "SubMenuID")
    private int SubMenuID;

    public Food(String FoodTitle, String FoodInfo, String Price, int SubMenuID) {
        this.FoodTitle = FoodTitle;
        this.FoodInfo = FoodInfo;
        this.Price = Price;
        this.SubMenuID = SubMenuID;
    }

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int foodID) {
        FoodID = foodID;
    }

    public String getFoodTitle() {
        return FoodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        FoodTitle = foodTitle;
    }

    public String getFoodInfo() {
        return FoodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        FoodInfo = foodInfo;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getSubMenuID() {
        return SubMenuID;
    }

    public void setSubMenuID(int subMenuID) {
        SubMenuID = subMenuID;
    }

    public String toString() {
        return FoodTitle + ": R " + Price;
    }
}
