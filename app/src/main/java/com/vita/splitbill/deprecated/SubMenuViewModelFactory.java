//package com.vita.splitbill;
//
//import android.app.Application;
//import android.arch.lifecycle.ViewModel;
//import android.arch.lifecycle.ViewModelProvider;
//
//public class SubMenuViewModelFactory extends ViewModelProvider.NewInstanceFactory {
//
//    private Application mApplication;
//    private int menuID;
//
//    public SubMenuViewModelFactory(Application application, int menuID) {
//        this.mApplication = application;
//        this.menuID = menuID;
//    }
//
//    @Override
//    public <T extends ViewModel> T create(Class<T> modelClass) {
//        return (T) new SubMenuViewModel(mApplication, menuID);
//    }
//
//}
