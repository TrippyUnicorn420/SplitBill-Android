package com.whatamidoingwithmylife.splitbill.deprecated;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.whatamidoingwithmylife.splitbill.database.FoodDao;
import com.whatamidoingwithmylife.splitbill.database.SplitBillRoomDatabase;
import com.whatamidoingwithmylife.splitbill.entities.Food;

import java.util.List;

// This is the repository for the Menu table in the database.
// This repo uses the DAO to interface with the database. It
// does everything on a separate thread, which can prevent the
// GUI from locking when long database queries are run.

// The repositories exist because they do everything related
// to the database on a thread that isn't the UI thread.
// If database actions are done on the UI thread, the app will
// crash, because Room doesn't like it when you try to do that,
// and will stop you from doing specifically that by all means.

public class FoodRepository {

    private FoodDao mFoodDao;
    private LiveData<List<Food>> mAllFood;
    private int SubMenuID;

    public FoodRepository(Application application, int subMenuID) {
        SplitBillRoomDatabase db = SplitBillRoomDatabase.getDatabase(application);
        mFoodDao = db.foodDao();
        mAllFood = mFoodDao.bringTheFood(subMenuID);
    }

    LiveData<List<Food>> bringTheFood() {
        return mAllFood;
    }

    public void insert(Food food) {
        new FoodRepository.insertAsyncTask(mFoodDao).execute(food);
    }

    private static class insertAsyncTask extends AsyncTask<Food, Void, Void> {

        private FoodDao mAsyncTaskDao;

        insertAsyncTask(FoodDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Food... params) {
            return null;
        }

    }
}
