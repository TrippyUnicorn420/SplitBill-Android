package com.whatamidoingwithmylife.splitbill.deprecated;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.whatamidoingwithmylife.splitbill.database.RestaurantDao;
import com.whatamidoingwithmylife.splitbill.database.SplitBillRoomDatabase;
import com.whatamidoingwithmylife.splitbill.entities.Restaurant;

import java.util.List;

// This is the repository for the Restaurant table in the database.
// This repo uses the DAO to interface with the database. It
// does everything on a separate thread, which can prevent the
// GUI from locking when long database queries are run.

// The repositories exist because they do everything related
// to the database on a thread that isn't the UI thread.
// If database actions are done on the UI thread, the app will
// crash, because Room doesn't like it when you try to do that,
// and will stop you from doing specifically that by all means.

public class RestaurantRepository {

    private RestaurantDao mRestaurantDao;
    private LiveData<List<Restaurant>> mAllRestaurants;

    RestaurantRepository(Application application) {
        SplitBillRoomDatabase db = SplitBillRoomDatabase.getDatabase(application);
        mRestaurantDao = db.restaurantDao();
        mAllRestaurants = mRestaurantDao.getAllRestaurants();
    }

    LiveData<List<Restaurant>> getAllRestaurants() {
        return mAllRestaurants;
    }

    public void insert(Restaurant restaurant) {
        new insertAsyncTask(mRestaurantDao).execute(restaurant);
    }

    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        insertAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurant... params) {
            return null;
        }

    }

}
