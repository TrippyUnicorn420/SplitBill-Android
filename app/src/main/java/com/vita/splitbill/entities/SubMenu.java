package com.vita.splitbill.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

// This is the SubMenu object. It defines the
// names of the submenus that show up in the tabs in
// Menu activity (determined by what is selected in
// the Spinner), AND is also the table schema for the
// submenus table in the SQLite database.

// The @Entity annotation defines this object as a table in the database.
// @PrimaryKey specifies which field in the database is to be treated as
// the primary key.
// @ColumnInfo specifies what the column title in the database should be.

// Normally, you don't need accessors and mutators for this kind of object,
// since they will be seldom used, but Room requires them.

@Entity(tableName = "SubMenus", foreignKeys = @ForeignKey(entity = Menu.class, parentColumns = "MenuID", childColumns = "MenuID", onDelete = CASCADE))
// ↑ You've probably noticed that this entity declaration is longer than
// normal. This table has a foreign key constraint to the Menus table.
// The constraint is on the MenuID column.
public class SubMenu {

    @PrimaryKey(autoGenerate = true)
    private int SubMenuID;

    @ColumnInfo(name = "SubMenuTitle")
    private String SubMenuTitle;

    @ColumnInfo(name = "MenuID")
    private int MenuID;

    public SubMenu(String SubMenuTitle, int MenuID) {
        this.SubMenuTitle = SubMenuTitle;
        this.MenuID = MenuID;
    }

    public int getSubMenuID() {
        return SubMenuID;
    }

    public void setSubMenuID(int subMenuID) {
        SubMenuID = subMenuID;
    }

    public String getSubMenuTitle() {
        return SubMenuTitle;
    }

    public void setSubMenuTitle(String subMenuTitle) {
        SubMenuTitle = subMenuTitle;
    }

    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int menuID) {
        MenuID = menuID;
    }

}
