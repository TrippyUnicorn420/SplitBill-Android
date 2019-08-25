package com.vita.splitbill.core;


import android.support.annotation.Nullable;

import com.vita.splitbill.entities.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Texas Instruments TI-84 PLUS Silver
 * Edition, a graphing calculator. Handles bill calculation
 * and fixes Menu/Submenu IDs.
 *
 * All methods are static.
 *
 * @author TrippyUnicorn420
 * @version 1.0
 */

public class TI84PLUS {
    private static List<Food> currentBill = new ArrayList<>();

    /**
     * Takes menuID and its corresponding resID, and makes menuID
     * restaurant-independent for the ViewPager
     * @param menuID: Primary key for the menu object
     * @param resID: Foreign key linking to restaurant object
     * @return fixedMenuID: MenuID independent of restaurant
     */
    public static int fixMenuID(int menuID, int resID) {
        int fixedMenuID = 0;

        switch (resID) {
            case 1:
                fixedMenuID = menuID;
                break;

            case 2:
                fixedMenuID = menuID + 1;
                break;

            case 3:
                fixedMenuID = menuID + 3;
                break;

            case 4:
                fixedMenuID = menuID + 10;
                break;

            case 5:
                fixedMenuID = menuID + 22;
                break;

            case 6:
                fixedMenuID = menuID + 23;
                break;

            case 7:
                fixedMenuID = menuID + 24;
                break;

            case 8:
                fixedMenuID = menuID + 25;
                break;

            case 9:
                fixedMenuID = menuID + 34;
                break;

            case 10:
                fixedMenuID = menuID + 42;
                break;
        }
        return fixedMenuID;
    }

    /**
     * Takes submenuID and its corresponding menuID and makes it menuID
     * independent for when the {@link com.vita.splitbill.NewMenuActivity.MenuFragment}
     * needs food
     * @param subMenuID: Primary key of the SubMenu object
     * @param menuID: Foreign key that linking to a Menu object
     * @return fixedSubMenuID: a Menu-independent SubMenuID
     */
    public static int fixSubMenuID(int subMenuID, int menuID) {
        int fixedSubMenuID = 0;
        switch (menuID) {
            case 1:
                fixedSubMenuID = subMenuID;
                break;

            case 2:
                fixedSubMenuID = subMenuID + 11;
                break;

            case 3:
                fixedSubMenuID = subMenuID + 26;
                break;

            case 4:
                fixedSubMenuID = subMenuID + 36;
                break;

            case 5:
                fixedSubMenuID = subMenuID + 41;
                break;

            case 6:
                fixedSubMenuID = subMenuID + 67;
                break;

            case 7:
                fixedSubMenuID = subMenuID + 80;
                break;

            case 8:
                fixedSubMenuID = subMenuID + 84;
                break;

            case 9:
                fixedSubMenuID = subMenuID + 93;
                break;

            case 10:
                fixedSubMenuID = subMenuID + 97;
                break;

            case 11:
                fixedSubMenuID = subMenuID + 106;
                break;

            case 12:
                fixedSubMenuID = subMenuID + 112;
                break;

            case 13:
                fixedSubMenuID = subMenuID + 115;
                break;

            case 14:
                fixedSubMenuID = subMenuID + 116;
                break;

            case 15:
                fixedSubMenuID = subMenuID + 118;
                break;

            case 16:
                fixedSubMenuID = subMenuID + 120;
                break;

            case 17:
                fixedSubMenuID = subMenuID + 125;
                break;

            case 18:
                fixedSubMenuID = subMenuID + 129;
                break;

            case 19:
                fixedSubMenuID = subMenuID + 131;
                break;

            case 20:
                fixedSubMenuID = subMenuID + 133;
                break;

            case 21:
                fixedSubMenuID = subMenuID + 135;
                break;

            case 22:
                fixedSubMenuID = subMenuID + 136;
                break;

            case 23:
                fixedSubMenuID = subMenuID + 143;
                break;

            case 24:
                fixedSubMenuID = subMenuID + 151;
                break;

            case 25:
                fixedSubMenuID = subMenuID + 160;
                break;

            case 26:
                fixedSubMenuID = subMenuID + 171;
                break;

            case 27:
                fixedSubMenuID = subMenuID + 173;
                break;

            case 28:
                fixedSubMenuID = subMenuID + 175;
                break;

            case 29:
                fixedSubMenuID = subMenuID + 177;
                break;

            case 30:
                fixedSubMenuID = subMenuID + 186;
                break;

            case 31:
                fixedSubMenuID = subMenuID + 189;
                break;

            case 32:
                fixedSubMenuID = subMenuID + 218;
                break;

            case 33:
                fixedSubMenuID = subMenuID + 220;
                break;

            case 34:
                fixedSubMenuID = subMenuID + 221;
                break;

            case 35:
                fixedSubMenuID = subMenuID + 230;
                break;

            case 36:
                fixedSubMenuID = subMenuID + 232;
                break;

            case 37:
                fixedSubMenuID = subMenuID + 240;
                break;

            case 38:
                fixedSubMenuID = subMenuID + 245;
                break;

            case 39:
                fixedSubMenuID = subMenuID + 246;
                break;

            case 40:
                fixedSubMenuID = subMenuID + 247;
                break;

            case 41:
                fixedSubMenuID = subMenuID + 248;
                break;

            case 42:
                fixedSubMenuID = subMenuID + 249;
                break;

            case 43:
                fixedSubMenuID = subMenuID + 256;
                break;

            case 44:
                fixedSubMenuID = subMenuID + 259;
                break;

            case 45:
                fixedSubMenuID = subMenuID + 276;
                break;

            case 46:
                fixedSubMenuID = subMenuID + 280;
                break;

            case 47:
                fixedSubMenuID = subMenuID + 281;
                break;

        }
        return fixedSubMenuID;
    }

    /**
     * Checks if the bill is empty.
     *
     * That's pretty much it.
     * @return true if Bill is empty, false otherwise
     */
    public static boolean isBillEmpty() {
        return currentBill.isEmpty();
    }

    /**
     * Returns the bill as List\<Food>.
     * @return currentBill
     */
    public static List<Food> getRawBill() {
        return currentBill;
    }

    // This gets the bill as an ArrayList of Strings
    // for the Bill activity.
    @Nullable
    public static ArrayList<String> getCurrentBill() {
        ArrayList<String> finalBill = new ArrayList<>();
        double total = 0;
        if (!isBillEmpty()) {
            for (int i = 0; i < currentBill.size(); i++) {
                finalBill.add(currentBill.get(i).toString());
                total = total + Double.parseDouble(currentBill.get(i).getPrice());
            }
            double fixedTotal = (double) Math.round(total * 100) / 100;
            finalBill.add("");
            finalBill.add("Total: R" + fixedTotal + "0\n");
            finalBill.add("Tip (10%): R" + (fixedTotal / 10));
            return finalBill;
        }
        else {
            return null;
        }
    }

    // This gets the bill as a big String for the Printer
    // and for sharing the bill to other apps as plain text.
    public static String getCurrentBillAsString() {
        String bill = "";
        double total = 0;
        if (!isBillEmpty()) {
            for (int i = 0; i < currentBill.size(); i++) {
                bill = bill + currentBill.get(i).toString() + "\n";
                total = total + Double.parseDouble(currentBill.get(i).getPrice());
            }
            double fixedTotal = Math.round(total * 100.0) / 100.0;
            bill = bill + "\n";
            bill = bill + "Total: R" + fixedTotal + 0 + "\n";
            bill = bill + "Tip (10%): R" + (fixedTotal / 10) + "\n";
            return bill;
        }
        else
            return null;
    }

    public static void clearBill() {
        currentBill.clear();
    }

    public static void addToBill(Food food) {
        currentBill.add(food);
    }

    public static void removeFromBill(Food food) {
        currentBill.remove(food);
    }

}
