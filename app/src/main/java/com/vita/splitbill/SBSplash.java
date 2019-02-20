package com.vita.splitbill;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.vita.splitbill.dialogs.FirstLaunchDialog;
import com.vita.splitbill.entities.Restaurant;

import java.util.List;

// This is the splash activity. If you tapped the icon
// in the launcher to open the app, this is where you
// would end up first. It *is* possible to launch one
// of the other activities directly and bypass this one
// altogether by opening cmd and typing "adb shell am
// start com.whatamidoingwithmylife.splitbill/.[Activ
// ityNameHere]", though it may cause a NullPointerRef
// erence error and crash altogether. I haven't tried it.

// Okay, I've tried it. It causes a SecurityException.

public class SBSplash extends AppCompatActivity {

    private SharedPreferences prefs = null;
    private RestaurantViewModel mRestaurantViewModel;

    // This onCreate() method is a bit different from the
    // others. It shows a little splash when the app is
    // launched from a cold start while everything is loading.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbsplash);

        Log.i("SplitBill/Splash","SplitBill. At your service.");

        final RecyclerView anotherOne = (RecyclerView) findViewById(R.id.recyclerview_id);
        final RestaurantListAdapter adapter = new RestaurantListAdapter(this);
        anotherOne.setLayoutManager(new GridLayoutManager(SBSplash.this,2));
        anotherOne.setAdapter(adapter);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.grid_layout_animation_from_bottom);
        anotherOne.setLayoutAnimation(animation);
        mRestaurantViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);

        mRestaurantViewModel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(@Nullable final List<Restaurant> restaurants) {
                adapter.setRestaurants(restaurants);
                runLayoutAnimation(anotherOne);
            }
        });

        prefs = getSharedPreferences("com.whatamidoingwithmylife.splitbill", MODE_PRIVATE);
    }

    // I've overridden this method so that it shows our lovely
    // failsafe dialog when the app is launched for the first time.
    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            FragmentManager fm = getSupportFragmentManager();
            FirstLaunchDialog dialog = FirstLaunchDialog.newInstance("An interesting title");
            dialog.setCancelable(false);
            dialog.show(fm, "fragment_alert");
            prefs.edit().putBoolean("firstrun", false).apply();
        }
        // TODO When you finally finish SplitBill version 1.0, remove this
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.grid_layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
