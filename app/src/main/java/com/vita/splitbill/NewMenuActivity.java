package com.vita.splitbill;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;

import com.vita.splitbill.core.TI84PLUS;
import com.vita.splitbill.dialogs.AddDialog;
import com.vita.splitbill.dialogs.BackDialog;
import com.vita.splitbill.dialogs.BillEmptyDialog;
import com.vita.splitbill.dialogs.YallDoneDialog;
import com.vita.splitbill.entities.Food;

import java.util.List;

// This is the new Menu activity. Fresh start and all that.
// I still hate it.
public class NewMenuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String resName;

    public static int ResID;
    public static int MenuID;
    // ↑ This here int is static because it needs to
    // be accessed from the fragment, something I
    // struggled to figure out for a good week. It's
    // one of the reasons why I hate this class so much.
    public static int mNumberMenus;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_menu);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mTabLayout = findViewById(R.id.newTabs);
        mViewPager = findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initActivity();
        MenuViewModel viewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        viewModel.getAllMenuTitles(ResID).observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] strings) {
                setupSpinner(strings);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();

                if (TI84PLUS.isBillEmpty()) {
                    BillEmptyDialog alertDialog = BillEmptyDialog.newInstance("");
                    alertDialog.show(fm, "fragment_alert");
                }
                else {
                    YallDoneDialog alertDialog = YallDoneDialog.newInstance(resName);
                    alertDialog.show(fm, "fragment_alert");
                }
            }
        });

    }

    // This method gets the name of the restaurant and its primary
    // key from the Intent that started the activity, and binds it
    // to the private fields of the class.
    public void initActivity(){
        resName = getIntent().getExtras().getString("Name");
        ResID = getIntent().getExtras().getInt("ResID");
    }

    // This method opens a {@Link com.whatamidoingwithmylife.splitbill.dialogs.BackDialog}
    // if the system back button is pressed, and the bill isn't empty.
    public void onBackPressed() {
         if (!TI84PLUS.isBillEmpty()) {
             FragmentManager fm = getSupportFragmentManager();
             BackDialog alertDialog = BackDialog.newInstance("Go back?");
             alertDialog.setCancelable(false);
             alertDialog.show(fm, "fragment_alert");
         }
         else {
             finish();
         }
    }


    // Since the MenuTitles are LiveData, I need this method to run
    // as the data from the database comes in, otherwise the Spinner
    // will stay empty.
    public void setupSpinner(String[] titles) {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner.setAdapter(new MyAdapter(toolbar.getContext(), titles));
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int MenuID = spinner.getSelectedItemPosition() + 1;
                int newMenuID = TI84PLUS.fixMenuID(MenuID, ResID);
                setPages(newMenuID);
                actuallySetTabs(newMenuID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // This method actually sets the tabs by getting
    // them from the database using the MenuID from the Spinner.
    public void actuallySetTabs(int MenuID) {
        mTabLayout.removeAllTabs();
        mViewPager.invalidate();
        mSectionsPagerAdapter.notifyDataSetChanged();
        this.MenuID = MenuID;
        SubMenuViewModel vm = ViewModelProviders.of(this).get(SubMenuViewModel.class);

        vm.getSubMenus(MenuID).observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] menus) {
                populateTabs(menus);
            }
        });
    }

    // This method adds the tabs to the ViewPager.
    // Dear comment reader, you have no idea how much
    // of a pain this was to get working. It took me
    // three months.
    public void populateTabs(String[] subMenus) {
        for (int i = 0; i < subMenus.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(subMenus[i]),i);
            Log.d("SplitBill/NewMenu", "Added " + (subMenus[i]) + " to TabLayout");
        }
        mViewPager.invalidate();
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    // There used to be a removeAllTabs() method here.
    // I explain its removal in the deprecated MenuActivity.

    // This next method sets the number of pages in the PagerAdapter.
    // If there are more tabs than pages, some tabs will be inaccessible.
    // If there are more pages than tabs, the user will be able to scroll
    // potentially incorrect data.
    public void setPages(int MenuID) {
        mTabLayout.removeAllTabs();
        mViewPager.invalidate();
        mSectionsPagerAdapter.notifyDataSetChanged();
        SubMenuViewModel vm = ViewModelProviders.of(this).get(SubMenuViewModel.class);
        vm.getmNumberMenus(MenuID).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mNumberMenus = Integer.parseInt(s);
                mSectionsPagerAdapter.notifyDataSetChanged();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.Amybutton) {
            AddDialog dialog = AddDialog.newInstance("something idk");
            FragmentManager fm = getSupportFragmentManager();
            dialog.setCancelable(false);
            dialog.show(fm, "fragment_alert");
            return true;
        }

        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // This is the adapter for the Spinner. I really couldn't tell you what
    // it does because it was generated by this Activity template. Fact of
    // the matter is it works. ¯\_(ツ)_/¯
    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }

    // This is the fragment that contains the food.
    public static class MenuFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public MenuFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MenuFragment newInstance(int sectionNumber) {
            MenuFragment fragment = new MenuFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        private void runLayoutAnimation(final RecyclerView recyclerView) {
            final Context context = recyclerView.getContext();
            final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        }

        // What happens in this here onCreateView() method is that basically
        // the page number is taken, fixed by the calculator, given to the
        // waiter as a SubMenuID, and the waiter brings the food to the table
        // based on that SubMenuID. Simple stuff.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_new_menu, container, false);

            final RecyclerView foodView = rootView.findViewById(R.id.newFoodRecycler);
            final FoodRecyclerAdapter table = new FoodRecyclerAdapter(this.getContext());
            
            foodView.setAdapter(table);
            foodView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            FoodViewModel waiter = ViewModelProviders.of(this).get(FoodViewModel.class);

            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);
            foodView.setLayoutAnimation(animation);

            int SubMenuID = TI84PLUS.fixSubMenuID(getArguments().getInt(ARG_SECTION_NUMBER), NewMenuActivity.MenuID);

            waiter.bringTheFood(SubMenuID).observe(this, new Observer<List<Food>>() {
                @Override
                public void onChanged(@Nullable List<Food> food) {
                    table.heresTheFood(food);
                    runLayoutAnimation(foodView);
                }
            });

            return rootView;
        }
    }

    public static class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MenuFragment.newInstance(position + 1);
        }

        // This method tells the PagerAdapter to create a certain number of pages.
        @Override
        public int getCount() {
            return NewMenuActivity.mNumberMenus;
        }

        // I had to manually override this next method. In its default configuration,
        // whenever the user changes pages, the PagerAdapter creates fragments and
        // keeps them cached. This results in menus being incorrect if the user
        // goes back and picks another restaurant until the fragment is loaded out
        // of memory.

        // Menus are incorrect because, in its default configuration, the
        // getItemPosition() method returns POSITION_UNCHANGED, which tells
        // the ViewPager that whatever is in the fragment is correct. I need it to
        // tell the ViewPager that the fragment is out of date with POSITION_NONE
        // whenever the user swipes to a page, to force the PagerAdapter to refresh
        // the fragment.

        // There's a bit of a performance hit, but I don't notice it on my S5.
        // Most importantly, it works. Inefficient, but it works.

        // ¯\_(ツ)_/¯

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
