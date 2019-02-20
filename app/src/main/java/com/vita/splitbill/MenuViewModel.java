package com.vita.splitbill;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

// This is a ViewModel. This is responsible for interfacing
// with the repository to add, delete or select items from
// the database. Using a ViewModel is the only way to get
// things from the database to the UI, AND hides all database
// interfacing from the UI, y'know, which is what Room kinda
// wants you to always do, or else it will be very sad.

public class MenuViewModel extends AndroidViewModel {

    private Repo mRepository;
    private Application application;
    private LiveData<String[]> mAllMenuTitles;

    public MenuViewModel(Application application) {
        super(application);
        this.application = application;
    }

    LiveData<String[]> getAllMenuTitles(int resID) {
        mRepository = new Repo(application);
        mAllMenuTitles = mRepository.getAllMenuTitles(resID);
        return mAllMenuTitles;
    }

}
