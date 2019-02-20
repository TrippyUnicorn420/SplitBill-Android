package com.whatamidoingwithmylife.splitbill;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.whatamidoingwithmylife.splitbill.entities.SubMenu;

import java.util.List;

public class SubMenuViewModel extends AndroidViewModel {

    private Repo mRepository;
    private LiveData<String> mNumberMenus;
    private LiveData<String[]> mAllMenus;
    private Application application;

    public SubMenuViewModel(Application application) {
        super(application);
        this.application = application;
    }

    LiveData<String> getmNumberMenus(int MenuID) {
        mRepository = new Repo(application);
        mNumberMenus = mRepository.getNumberOfSubMenus(MenuID);
        return mNumberMenus;
    }

    public LiveData<String[]> getSubMenus(int MenuID) {
        mRepository = new Repo(application);
        mAllMenus = mRepository.getAllSubMenus(MenuID);
        return mAllMenus;
    }

}
