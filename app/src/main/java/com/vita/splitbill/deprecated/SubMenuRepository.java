package com.vita.splitbill.deprecated;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.vita.splitbill.database.SplitBillRoomDatabase;
import com.vita.splitbill.database.SubMenuDao;

public class SubMenuRepository {

    private SubMenuDao mSubMenuDao;
    private LiveData<String[]> mAllSubMenus;
    private LiveData<String> mNumberOfSubMenus;
    private int menuID;

    public SubMenuRepository(Application application, int menuID) {
        SplitBillRoomDatabase db = SplitBillRoomDatabase.getDatabase(application);
        mSubMenuDao = db.subMenuDao();
        mAllSubMenus = mSubMenuDao.getSubMenus(menuID);
        mNumberOfSubMenus = mSubMenuDao.getNumberOfSubMenus(menuID);

        this.menuID = menuID;
    }

    public LiveData<String> getmNumberOfSubMenus() {
        return mNumberOfSubMenus;
    }

    public LiveData<String[]> getAllSubMenus() {
        return mAllSubMenus;
    }

//    public void getSubMenus(MenuFragment.MenuTitles mCallback) {
//        new insertAsyncTask(mSubMenuDao, menuID, mCallback).execute();
//    }
//

//    private static class insertAsyncTask extends AsyncTask<Void, Void, String[]> {
//
//        private SubMenuDao mAsyncTaskDao;
//        int subMenuId;
//        MenuFragment.MenuTitles mCallback;
//
//
//        insertAsyncTask(SubMenuDao dao, int sId, MenuFragment.MenuTitles mCallback) {
//            mAsyncTaskDao = dao;
//            subMenuId = sId;
//            this.mCallback = mCallback;
//        }
//
//        @Override
//        protected String[] doInBackground(Void... voids) {
//            return mAsyncTaskDao.getSubMenus(subMenuId);
//        }
//
//        @Override
//        protected void onPostExecute(String[] aVoid) {
//            super.onPostExecute(aVoid);
//            if(mCallback!=null){
//                mCallback.onStringReceived(aVoid);
//            }
//        }
//
//    }

}
