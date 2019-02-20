package com.vita.splitbill;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.vita.splitbill.database.FoodDao;
import com.vita.splitbill.database.MenuDao;
import com.vita.splitbill.database.RestaurantDao;
import com.vita.splitbill.database.SplitBillRoomDatabase;
import com.vita.splitbill.database.SubMenuDao;
import com.vita.splitbill.entities.Food;
import com.vita.splitbill.entities.Menu;
import com.vita.splitbill.entities.Restaurant;

import java.util.List;

// This is the repository. There were millions of these before,
// they are now all in the deprecated package. I merged them
// all to make everything a bit neater.

// The repo uses the DAOs to interface with the database. It
// does everything on a separate thread, which can prevent the
// GUI from locking when long database queries are run.

// The repo exists because it does almost everything related
// to the database on a thread that isn't the UI thread.
// If database actions are done on the UI thread, the app will
// crash, because Room doesn't like it when you try to do that,
// and will stop you from doing specifically that by all means.

public class Repo {

    private RestaurantDao mRestaurantDao;
    private LiveData<List<Restaurant>> mAllRestaurants;

    private MenuDao mMenuDao;
    private LiveData<List<Menu>> mAllMenus;
    private LiveData<String[]> mAllMenuTitles;

    private SubMenuDao mSubMenuDao;
    private LiveData<String[]> mAllSubMenus;
    private LiveData<String> mNumberOfSubMenus;

    private FoodDao mFoodDao;
    private LiveData<List<Food>> mAllFood;

    private SplitBillRoomDatabase db;

    Repo(Application application) {
        db = SplitBillRoomDatabase.getDatabase(application);
        mRestaurantDao = db.restaurantDao();
        mMenuDao = db.menuDao();
        mSubMenuDao = db.subMenuDao();
        mFoodDao = db.foodDao();
    }

    public LiveData<List<Restaurant>> getAllRestaurants() {
        mAllRestaurants = mRestaurantDao.getAllRestaurants();
        return mAllRestaurants;
    }

    public LiveData<List<Menu>> getAllMenus(int resID) {
        mAllMenus = mMenuDao.getAllMenus(resID);
        return mAllMenus;
    }

    public LiveData<String[]> getAllMenuTitles(int resID) {
        mAllMenuTitles = mMenuDao.getAllMenuTitles(resID);
        return mAllMenuTitles;
    }

    public LiveData<String[]> getAllSubMenus(int menuID) {
        mAllSubMenus = mSubMenuDao.getSubMenus(menuID);
        return mAllSubMenus;
    }

    public LiveData<String> getNumberOfSubMenus(int menuID) {
        mNumberOfSubMenus = mSubMenuDao.getNumberOfSubMenus(menuID);
        return mNumberOfSubMenus;
    }

    public LiveData<List<Food>> bringTheFood(int subMenuID) {
        mAllFood = mFoodDao.bringTheFood(subMenuID);
        return mAllFood;
    }

}
