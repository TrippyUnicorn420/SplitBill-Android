//package com.vita.splitbill.deprecated;
//
//import android.content.Intent;
//import android.support.design.widget.TabLayout;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//
//import android.widget.Toast;
//
//import com.vita.splitbill.R;
////import com.vita.splitbill.SubMenuViewModelFactory;
//import com.vita.splitbill.dialogs.BackDialog;
//import com.vita.splitbill.dialogs.YallDoneDialog;
//
//// This class was the class for the menu activity.
//// I hate this class. Passionately.
//
//// I hated it so much that I deprecated it and
//// replaced it with {@Link com.vita.splitbill.NewMenuActivity}.
//// This class is useless now.
//// While deprecating more stuff, this class kept throwing compile-time errors. I eventually
//// got so annoyed and commented this whole thing out. It's now 100% useless.
//
//public class MenuActivity extends AppCompatActivity implements MenuFragment.MenuTitles {
//    private static final String TAG = "SplitBill";
//
//    private Toolbar toolbar;
//    private String resName;
//
//    private int MenuID;
//    public static int ResID;
//    // ↑ This here int is static because it needs to
//    // be accessed from the fragment, something I
//    // struggled to figure out for a good week. It's
//    // one of the reasons why I hate this class so much.
//    public static int mNumberMenus;
//
//    private SectionsPagerAdapter mSectionsPagerAdapter;
//    private TabLayout mTabLayout;
//    private ViewPager mViewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//        mTabLayout = findViewById(R.id.tabs);
//        mViewPager = findViewById(R.id.container);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
//
//        setResName();
//        initActivity();
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fm = getSupportFragmentManager();
//                YallDoneDialog alertDialog = YallDoneDialog.newInstance("Y'all done?");
//                alertDialog.show(fm, "fragment_alert");
//            }
//        });
//
//        removeAllTabs();
//
//
////        SubMenuViewModel vm = ViewModelProviders.of(this, new SubMenuViewModelFactory(getApplication(), MenuActivity.ResID)).get(SubMenuViewModel.class);
////        vm.getmNumberMenus().observe(this, new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                mNumberMenus = Integer.parseInt(s);
////                mSectionsPagerAdapter.notifyDataSetChanged();
////            }
////        });
//    }
//
//    // Remember that static ResID int I declared a method back?
//    // This method is responsible for setting it without crashing
//    // everything from a NullPointerException. It's also responsible
//    // for setting the restaurant name on the activity toolbar.
//
//    public void initActivity(){
//        toolbar = findViewById(R.id.toolbar);
//
//        resName = getIntent().getExtras().getString("Name");
//        ResID = getIntent().getExtras().getInt("ResID");
//
//        toolbar.setTitle(resName);
//    }
//
//    // This method was named out of frustration, mostly.
//    // It's responsible for receiving the Spinner position
//    // from the fragment and then setting the tab titles.
//    // Another reason why I hate this class so much.
//    public void doTheWigWam(int MenuID) {
//
//        // Basically what this switch does is to fix the
//        // MenuIDs. The Spinner in the fragment does not
//        // care about which restaurant is selected, so if
//        // I don't fix the MenuID using this switch, the
//        // ViewPager tabs will be incorrect. :)
//        switch (ResID) {
//            case 1:
//                MenuID = MenuID;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 2:
//                MenuID = MenuID + 1;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 3:
//                MenuID = MenuID + 3;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 4:
//                MenuID = MenuID + 10;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 5:
//                MenuID = MenuID + 22;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 6:
//                MenuID = MenuID + 23;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 7:
//                MenuID = MenuID + 24;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 8:
//                MenuID = MenuID + 25;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 9:
//                MenuID = MenuID + 34;
//                actuallySetTabs(MenuID);
//                break;
//
//            case 10:
//                MenuID = MenuID + 42;
//                actuallySetTabs(MenuID);
//                break;
//        }
//
//    }
//
//
//    public void actuallySetTabs(int MenuID) {
//        // This code is surrounded in an if statement so
//        // that no new tabs are added (or a ViewPager reset
//        // is initiated) every time the user swipes pages.
//        // It will only run if the MenuID changes.
//
//        if (this.MenuID != MenuID) {
//            removeAllTabs();
//            this.MenuID = MenuID;
////            SubMenuViewModel vm = ViewModelProviders.of(this, new SubMenuViewModelFactory(getApplication(), MenuID)).get(SubMenuViewModel.class);
//
////            vm.getSubMenus().observe(this, new Observer<List<SubMenu>>() {
////                @Override
////                public void onChanged(@Nullable List<SubMenu> menus) {
////                    String[] strings = new String[menus.size()];
////                    for (int i = 0; i < menus.size(); i++) {
////                        strings[i] = menus.get(i).getSubMenuTitle();
////                    }
////                    populateTabs(strings);
////                }
////            });
//        }
//    }
//
//    public void onStringReceived(String[] strings) {
//        for (int i = 0; i < strings.length; i++)
//            Toast.makeText(getApplicationContext(), ""+ strings[i], Toast.LENGTH_SHORT).show();
//        populateTabs(strings);
//    }
//
//    // Very unlikely that anybody will read this, since it's
//    // deprecated, but I just (30 September 2018 at 9:47 PM)
//    // figured out that the next method is completely useless!
//    // The TabLayout already has a removeAllTabs() function
//    // that works much more elegantly than this method below!
//    // Yaaaaaay! :)
//    public void removeAllTabs() {
//        int cnp = mViewPager.getChildCount();
//        while (cnp > 1) {
//            mSectionsPagerAdapter.destroyItem(mViewPager, cnp - 1, mSectionsPagerAdapter.getItem(cnp - 1));
//            mTabLayout.removeTabAt(cnp - 1);
//            cnp--;
//        }
//        mSectionsPagerAdapter.notifyDataSetChanged();
//    }
//
//    public void populateTabs(String[] subMenus) {
//        for (int i = 0; i < subMenus.length; i++) {
//            mTabLayout.addTab(mTabLayout.newTab().setText(subMenus[i]),i);
//            Log.d(TAG, "Added " + (subMenus[i]) + " to TabLayout");
//        }
//
//        mSectionsPagerAdapter.notifyDataSetChanged();
//    }
//
//    public void onBackPressed() {
//        FragmentManager fm = getSupportFragmentManager();
//        BackDialog alertDialog = BackDialog.newInstance("Go back?");
//        alertDialog.setCancelable(false);
//        alertDialog.show(fm, "fragment_alert");
//    }
//
//    public String getResName() {
//        return resName;
//    }
//
//    public void setResName() {
//        toolbar = findViewById(R.id.toolbar);
//
//        Intent intent = getIntent();
//        resName = intent.getStringExtra("Name");
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    public static class SectionsPagerAdapter extends FragmentStatePagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return MenuFragment.newInstance(position + 1);
//        }
//
//        @Override
//        public int getCount() {
//            return MenuActivity.mNumberMenus;
//        }
//    }
//}