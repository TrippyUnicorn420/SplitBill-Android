//package com.vita.splitbill.deprecated;
//
//import android.app.Application;
//import android.arch.lifecycle.ViewModel;
//import android.arch.lifecycle.ViewModelProvider;
//
//import com.vita.splitbill.MenuViewModel;
//
//// This is a ViewModelFactory. This here class exists for the Menu
//// (and probably the SubMenu too) because I needed to pass ResID to
//// the MenuViewModel, something ViewModelProviders can't do without
//// this class.
//
//// Android Studio says that I use unchecked and unsafe operations
//// here, but it works, so... ¯\_(ツ)_/¯
//
//public class MenuViewModelFactory extends ViewModelProvider.NewInstanceFactory {
//
//    private Application mApplication;
//    private int menuID;
//
//    public MenuViewModelFactory(Application application, int menuID) {
//        this.mApplication = application;
//        this.menuID = menuID;
//    }
//
//    @Override
//    public <T extends ViewModel> T create(Class<T> modelClass) {
//        return (T) new MenuViewModel(mApplication, menuID);
//    }
//
//}
