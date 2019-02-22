//package com.vita.splitbill;
//
//import android.app.Application;
//import android.arch.lifecycle.ViewModel;
//import android.arch.lifecycle.ViewModelProvider;
//
//// This is a ViewModelFactory. This here class exists for the Menu
//// (and probably the SubMenu too) because I needed to pass ResID to
//// the MenuViewModel, something ViewModelProviders can't do without
//// this class.
//
//// Android Studio says that I use unchecked and unsafe operations
//// here, but it works, so... ¯\_(ツ)_/¯
//
//public class FoodViewModelFactory extends ViewModelProvider.NewInstanceFactory {
//
//    private Application mApplication;
//    private int subMenuID;
//
//    public FoodViewModelFactory(Application application, int subMenuID) {
//        this.mApplication = application;
//        this.subMenuID = subMenuID;
//    }
//
//    @Override
//    public <T extends ViewModel> T create(Class<T> modelClass) {
//        return (T) new FoodViewModel(mApplication, subMenuID);
//    }
//
//}
