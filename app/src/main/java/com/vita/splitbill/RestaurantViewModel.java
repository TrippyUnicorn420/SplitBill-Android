package com.vita.splitbill;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vita.splitbill.entities.Restaurant;

import java.util.List;

// This is a ViewModel. This is responsible for interfacing
// with the repository to add, delete or select items from
// the database. Using a ViewModel is the only way to get
// things from the database to the UI, AND hides all database
// interfacing from the UI.

public class RestaurantViewModel extends AndroidViewModel {

    private Repo mRepository;
    private LiveData<List<Restaurant>> mAllRestaurants;

    public RestaurantViewModel(Application application) {
        super(application);
        mRepository = new Repo(application);
        mAllRestaurants = mRepository.getAllRestaurants();
    }

    LiveData<List<Restaurant>> getAllRestaurants() {
        return mAllRestaurants;
    }

}
