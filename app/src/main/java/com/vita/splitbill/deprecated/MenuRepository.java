package com.vita.splitbill.deprecated;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.vita.splitbill.database.MenuDao;
import com.vita.splitbill.database.SplitBillRoomDatabase;
import com.vita.splitbill.entities.Menu;

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

public class MenuRepository {

    private MenuDao mMenuDao;
    private LiveData<List<Menu>> mAllMenus;
    private LiveData<String[]> mAllMenuTitles;

    public MenuRepository(Application application, int resID) {
        SplitBillRoomDatabase db = SplitBillRoomDatabase.getDatabase(application);
        mMenuDao = db.menuDao();
        mAllMenus = mMenuDao.getAllMenus(resID);
        mAllMenuTitles = mMenuDao.getAllMenuTitles(resID);
    }

    LiveData<List<Menu>> getAllMenus() {
        return mAllMenus;
    }

    LiveData<String[]> getAllMenuTitles() {
        return mAllMenuTitles;
    }

    public void insert(Menu menu) {
        new MenuRepository.insertAsyncTask(mMenuDao).execute(menu);
    }

    private static class insertAsyncTask extends AsyncTask<Menu, Void, Void> {

        private MenuDao mAsyncTaskDao;

        insertAsyncTask(MenuDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Menu... params) {
            return null;
        }

    }
}
