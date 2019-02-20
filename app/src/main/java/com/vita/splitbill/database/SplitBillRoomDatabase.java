package com.vita.splitbill.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vita.splitbill.R;
import com.vita.splitbill.entities.Food;
import com.vita.splitbill.entities.Menu;
import com.vita.splitbill.entities.Restaurant;
import com.vita.splitbill.entities.SubMenu;

// This is the database.

// In the @Database annotation, the entities are specified,
// which tells SQLite the tables that need to be made.

// The DAOs are also linked and specified here.

@Database(entities = {Restaurant.class, Menu.class, SubMenu.class, Food.class}, exportSchema = false, version = 1)
public abstract class SplitBillRoomDatabase extends RoomDatabase {

    public abstract RestaurantDao restaurantDao();
    public abstract MenuDao menuDao();
    public abstract SubMenuDao subMenuDao();
    public abstract FoodDao foodDao();

    private static SplitBillRoomDatabase INSTANCE;

    // This next method checks if an instance of the database
    // exists in memory. If it does, nothing happens. If it doesn't,
    // it's launched with the context of the app, and does so
    // synchronised with the rest of the app.

    public static SplitBillRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SplitBillRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SplitBillRoomDatabase.class, "splitbilldatabase.db")
                            .addCallback(sRoomDatabaseCallback)
                            // If I wanted to force Room to let me do
                            // database queries on the UI thread (which
                            // nobody should EVER do), this is where I
                            // would add ".allowMainThreadQueries()".
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        // This tells Room to tell SQLite what to do when the database is
        // first created.

        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }

        // This tells Room to tell SQLite what to do when the database exists,
        // and is opened.

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }

    };

    // This is an asynchronous call to the database which
    // instructs Room to instruct SQLite to populate the database
    // with the fields specified below.

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RestaurantDao restaurantDao;
        private final MenuDao menuDao;
        private final SubMenuDao subMenuDao;
        private final FoodDao foodDao;

        PopulateDbAsync(SplitBillRoomDatabase db) {
            restaurantDao = db.restaurantDao();
            menuDao = db.menuDao();
            subMenuDao = db.subMenuDao();
            foodDao = db.foodDao();
        }

        // Oh boy, this next method is a long method.
        // Basically, the doInBackground method populates the database.
        // There's quite a lot of things in the database, that's why
        // it's so big. It's executed the first time the app is launched.

        // Does it slow app launch down? Nope. It's done asynchronously
        // and on a background thread.

        @Override
        protected Void doInBackground(final Void... params){
            restaurantDao.deleteAll();

            Log.i("SplitBill/Database","Populating database...");

            Restaurant restaurant = new Restaurant("Spur", R.drawable.spur);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Ocean Basket", R.drawable.oceanbasket);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Parrots", R.drawable.parrots);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Cappucinos", R.drawable.cappucinos);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Nando's", R.drawable.cheekynandos);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Rocomamas", R.drawable.rocomamas);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("John Dory's", R.drawable.john);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Baobab", R.drawable.baobab);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Mugg & Bean", R.drawable.themugg);
            restaurantDao.insert(restaurant);
            restaurant = new Restaurant("Summit", R.drawable.summit);
            restaurantDao.insert(restaurant);

            Log.i("SplitBill/Database","The Restaurants table has been successfully populated.");

            menuDao.deleteAll();
            Menu menu = new Menu("Spur",1);
            menuDao.insert(menu);

            menu = new Menu("Main", 2);
            menuDao.insert(menu);
            menu = new Menu("Sushi",2);
            menuDao.insert(menu);

            menu = new Menu("Breakfast",3);
            menuDao.insert(menu);
            menu = new Menu("Main Menu", 3);
            menuDao.insert(menu);
            menu = new Menu("Sushi",3);
            menuDao.insert(menu);
            menu = new Menu("Coffee's & Gourmet Drinks",3);
            menuDao.insert(menu);
            menu = new Menu("Cocktails",3);
            menuDao.insert(menu);
            menu = new Menu("Wine",3);
            menuDao.insert(menu);
            menu = new Menu("Alcoholic Beverages",3);
            menuDao.insert(menu);

            menu = new Menu("Breakfasts",4);
            menuDao.insert(menu);
            menu = new Menu("The Bread Box",4);
            menuDao.insert(menu);
            menu = new Menu("Light Meals",4);
            menuDao.insert(menu);
            menu = new Menu("Burgers",4);
            menuDao.insert(menu);
            menu = new Menu("Let's Start, Focaccia & Salads",4);
            menuDao.insert(menu);
            menu = new Menu("Favourites, House Specialties & Combo Grills",4);
            menuDao.insert(menu);
            menu = new Menu("Flame Grills & Oven Roasted",4);
            menuDao.insert(menu);
            menu = new Menu("Wood Fire Pizza",4);
            menuDao.insert(menu);
            menu = new Menu("Pasta",4);
            menuDao.insert(menu);
            menu = new Menu("Desserts",4);
            menuDao.insert(menu);
            menu = new Menu("Cold Beverages",4);
            menuDao.insert(menu);
            menu = new Menu("Hot Beverages",4);
            menuDao.insert(menu);

            menu = new Menu("Nando's",5);
            menuDao.insert(menu);

            menu = new Menu("Rocomamas",6);
            menuDao.insert(menu);

            menu = new Menu("John Dory's",7);
            menuDao.insert(menu);

            menu = new Menu("Breakfast",8);
            menuDao.insert(menu);
            menu = new Menu("Light Meals",8);
            menuDao.insert(menu);
            menu = new Menu("Starters & Salads",8);
            menuDao.insert(menu);
            menu = new Menu("Main",8);
            menuDao.insert(menu);
            menu = new Menu("Beverages",8);
            menuDao.insert(menu);
            menu = new Menu("Wine",8);
            menuDao.insert(menu);
            menu = new Menu("Desserts",8);
            menuDao.insert(menu);
            menu = new Menu("Kiddies",8);
            menuDao.insert(menu);
            menu = new Menu("Sushi",8);
            menuDao.insert(menu);

            menu = new Menu("The Roastery",9);
            menuDao.insert(menu);
            menu = new Menu("All-Day Breakfast",9);
            menuDao.insert(menu);
            menu = new Menu("Delicious Fillings",9);
            menuDao.insert(menu);
            menu = new Menu("Mix Match Share",9);
            menuDao.insert(menu);
            menu = new Menu("Gourmet Burgers",9);
            menuDao.insert(menu);
            menu = new Menu("Hearty & Generous",9);
            menuDao.insert(menu);
            menu = new Menu("Freshly Baked",9);
            menuDao.insert(menu);
            menu = new Menu("Drinks",9);
            menuDao.insert(menu);

            menu = new Menu("Breakfast",10);
            menuDao.insert(menu);
            menu = new Menu("A La Carte",10);
            menuDao.insert(menu);
            menu = new Menu("Light Lunch",10);
            menuDao.insert(menu);
            menu = new Menu("Cigars",10);
            menuDao.insert(menu);
            menu = new Menu("Drinks",10);
            menuDao.insert(menu);
            menu = new Menu("Bottle Service",10);
            menuDao.insert(menu);

            Log.i("SplitBill/Database","The Menus table has been successfully populated.");

            subMenuDao.deleteAll();
            SubMenu subMenu = new SubMenu("Sizzling Starters",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Great For Sharing",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salads & Sides",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Legendary Steaks",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Ribs, Grills & Combos",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Schnitzels",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Seafood",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Burgers",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Desserts",1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Breakfast Menu", 1);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Kids' Menu",1);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Starters & Meze",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Soups & Salads",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Calamari",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Fish",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Platters To Share",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Med Rice", 2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Top Ups",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Platters For 1",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Prawns",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sides",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Combos",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Kids' Mains",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Tiny Treats",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Desserts",2);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Kids' Dessert",2);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Gunkan",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("New Style Sashimi",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Futomaki",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Platters",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Flower Gunkan",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("California Rolls (Uramaki)",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Rainbow Rolls (Uramaki)",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sashimi (Salmon or Tuna)",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Maki (Hosomaki)",3);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Nigiri",3);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Health Breakfasts", 4);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Classic Breakfasts", 4);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Special Breakfasts", 4);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Gourmet Breakfasts", 4);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Extras", 4);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Hot Starters", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Baskets", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Spuds", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Wraps", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Quesadilla", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Tramezzinis", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Fresh Butter Croissant", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Toasted Sandwiches", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Gourmet Sandwich", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salads", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Savoury Pancakes", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pies", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Burgers", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pregos", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Baby Back Ribs", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Our Famous Steaks", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Grill Combos", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Chicken", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Stir Fry Dishes", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Seafood", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pizza", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pasta", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Filled", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Oven-Baked", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Bakery", 5);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Desserts", 5);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Platters", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Special Rolls & Dishes", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("California Rolls", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Fashion Sandwiches", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Fashion Cakes", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Hand Rolls", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Inari Battleships", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Maki Rolls", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("The Then Beng Collection", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Nigiri", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sakula Roll", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sashimi", 6);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sumo Roll", 6);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Coffee's & Tea's", 7);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cold Drinks", 7);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Iced Drinks, Freezo's & Fresh Juice", 7);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Shakes", 7);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Mocktails", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Gudgu Cocktails", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Hall of Fame", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Mojo Co. Cocktails", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Ginifer Cocktails", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Shooters", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Martinis & Short Drinks", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Long Drinks & Crushes", 8);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Jugs", 8);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Wine by the Glass", 9);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("White Wines", 9);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Red Wines", 9);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Blush Wines", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Brandy", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Mixers", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Blush Wines", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Whiskey", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Blush Wines", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Draught", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Blush Wines", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Vodka", 10);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Ciders", 10);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Breakfasts", 11);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Breakfast Classics", 11);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Breakfast Melts", 11);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Muffins", 11);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Top Ups", 11);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Omelettes", 11);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("The Bread Box", 12);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Toasted Ciabatta/Seeded Rye Bread", 12);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Wraps", 12);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Light Meals", 13);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Burgers", 14);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Let's Start", 15);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Focaccia", 15);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salads", 15);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Favourites", 16);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("House Specialties", 16);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Combo Grill", 16);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Classic Sauces", 16);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Side Orders", 16);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Flame Grills", 17);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Oven Roasted", 17);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Classic Sauces", 17);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Side Orders", 17);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Wood Fire Pizza", 18);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("More Toppings", 18);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Pasta", 19);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pasta + Sauce", 19);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Desserts", 20);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Home-Made Cakes", 20);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Cold Beverages", 21);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Coffees", 22);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Espressos", 22);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Classic Cappuccinos", 22);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Gourmet Cappuccinos", 22);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Favourites", 22);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Teas", 22);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Rooibos Espresso", 22);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Full Chicken", 23);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("1/2 Chicken", 23);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("1/4 Chicken", 23);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Burgers", 23);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pitas", 23);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Wraps", 23);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Bowls", 23);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salads", 23);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("SMASHBurgers",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("MySMASHBurger",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Ribs",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Wings",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salad",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Six Bombs",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Los Nachos",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Shoestring Fries",24);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Turbocharged Fries",24);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Starters",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Platters",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Fish Market",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sushi",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Grr-Grills",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Top-ups and Sides",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salad Sensations",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Combos",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Desserts",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Drinks",25);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Kids",25);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Breakfast",26);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Omelette",26);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Light Meals",27);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Baobab Burgers",27);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Starters",28);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salads",28);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Nama",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sauces",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Potjies",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Ribs",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Side Orders",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Mzansi's Favourites",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Tlhapi",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Dikgogo",29);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Chicken Schnitzel",29);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Hot Beverages",30);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cold Beverages",30);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Alcoholic Beverages",30);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Methode Cap Classique",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sparkling Wine",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Champagne",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pinks",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Chenin Blanc",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("White Wine Sauvignon Blanc",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("White Blends",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Chardonnay",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Semi-Sweet",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Red Wines Carbernet Sauvignon",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pinotage",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Shiraz/Syrah",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Merlot",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Red Blends",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Vodka",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Brandy",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cognac",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Single Malt",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Whiskey",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Bourbon",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Rum",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Gin",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Liqueur",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Tequila",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Virgin Cocktails",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Port, Sherry & Grappa",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Whites By The Glass",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Red Wine By The Glass",31);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pinks By The Glass",31);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Desserts",32);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Dessert Cocktails",32);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Kiddies Meals",33);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("California Rolls",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Battleships",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Nigiri",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Fashion Sandwiches",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Futomaki",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sashimi",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Roses",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Seared Salmon",34);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Platters",34);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("M&B House Roast",35);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("House Roast - Decaf",35);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Classics",36);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Time For Toast",36);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Eggs Benedict",36);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sweet & Savoury",36);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Power Smoothies",36);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Omelettes",36);
            subMenuDao.insert(subMenu);
            // I find it interesting how every single restaurant that serves
            // omelettes spells the word differently.
            subMenu = new SubMenu("Power Breakfast Bowls",36);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Freshly Baked Butter Croissants",36);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Paninos",37);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Folded Fillers",37);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Toasted Sandwiches",37);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salads",37);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Paninos",37);
            subMenuDao.insert(subMenu);

            // You know, I could probably start leaving random thoughts,
            // easter eggs and whatnot this far down this class, since
            // nobody will ever read these comments.

            // I mean, come on, we're 777 lines of code in, will this entire
            // method be printed? It's literally just 600 lines of database entries.
            // Nobody but me will ever be down here.

            subMenu = new SubMenu("Mix, Match, Share",38);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Gourmet Burgers",39);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Hearty & Generous",40);
            subMenuDao.insert(subMenu);

            // This menu really sucks. The layout is absolutely awful.
            // No wonder Uber Eats didn't bother including the whole thing.

            subMenu = new SubMenu("Freshly Baked",41);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Hot",42);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cold",42);
            subMenuDao.insert(subMenu);

            // There is SO. MUCH. FRAGMENTATION. How do they get anything done?

            subMenu = new SubMenu("Superfood Lattes",42);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Tea, Chai & Espresso",42);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Refreshing Cold Drinks",42);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Bottomless Hot Range",42);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Bottomless Cold Range",42);
            subMenuDao.insert(subMenu);

            // Come to think of it, I wouldn't have to be doing any of this
            // if I set up Microsoft SQL Server 2017 on SUPERAWESOME, since Room DOES
            // support it... because this is getting rather tedious.

            subMenu = new SubMenu("Light & Fresh",43);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Summit Breakfasts",43);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Omelettes",43);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Starters",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Salad",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Soups",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Burgers",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Signature Steaks",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Classic Cuts",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Ribs",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("House Specialties",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sides",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sauces",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Poultry",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Seafood",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pasta",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Vegetarian",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sushi",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Platters",44);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Dessert",44);
            subMenuDao.insert(subMenu);

            // Funny how one of the most expensive places here has one
            // of the most simple menu layouts.

            subMenu = new SubMenu("Toasted Sandwiches",45);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Tramezzini",45);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Wraps",45);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("More Light Lunches",45);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Cigars",46);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Champagne",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Method Cap Classique",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sparkling Wine",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Sauvignon Blanc",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Chardonnay",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Chenin Blanc",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("White Blends",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Semi-Sweet",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Rose",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cabernet Sauvignon",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Merlot",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Shiraz",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pinotage",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Red Blends",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Pinot Noir",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cognac",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Premium Brandy",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Single Malt Whiskeies",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Premium Whiskeys",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cocktails",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Signature Cocktails",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Shooters",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Gin",47);
            subMenuDao.insert(subMenu);

            subMenu = new SubMenu("Whiskey",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Single Malts",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Cognacs",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("Tequila",47);
            subMenuDao.insert(subMenu);
            subMenu = new SubMenu("White Spirits",47);
            subMenuDao.insert(subMenu);

            Log.i("SplitBill/Database","The SubMenus table has successfully been populated.");
            // There's something I never thought I'd send to logcat.
            // Time for the Food table... this will be fun.

            foodDao.deleteAll();
            Food food = new Food("Starter Buffalo Wings","Chicken wings tossed in Spur's famous Durky Sauce.", "69.90", 1);
            foodDao.insert(food);
            food = new Food("Crispy Cheesy Garlic Roll","Crispy cheesy garlic roll.", "32.90", 1);
            foodDao.insert(food);
            food = new Food("Chicken Livers","Spicy peri-peri chicken livers. Served with chips and tartare sauce.", "59.90", 1);
            foodDao.insert(food);
            food = new Food("Starter Calamari","Lightly dusted, flash-fried calamari. Served with chips and tartare sauce.", "69.90", 1);
            foodDao.insert(food);
            food = new Food("Crumbed Mushrooms","Lightly crumbed and fried until golden brown. Served with tartare sauce.", "69.90", 1);
            foodDao.insert(food);
            food = new Food("Starter Nachos Mexicana","Smothered in zippy salsa, sticky cheese, chunky cottage cheese and guacamole.", "64.90", 1);
            foodDao.insert(food);
            food = new Food("Jalapeño Chilli Poppers","Spicy jalapeño peppers stuffed with feta, cheddar, mozzarella, then crumbed and flash-fried. Served with sweet chilli sauce.", "69.90", 1);
            foodDao.insert(food);
            food = new Food("Cheesy garlic Prawns","Prawns smothered in a cheesy garlic sauce and melted cheese. Served with garlic toast.", "74.90", 1);
            foodDao.insert(food);
            food = new Food("Crumbed Mushrooms & Calamari","A delicious combination of your favourites - served with either our tangy tartare, Durky OR sweet chilli sauces.", "79.90", 1);
            foodDao.insert(food);
            food = new Food("Buffalo Wings & Chilli Poppers","A delicious combination of your favourites - served with either our tangy tartare, Durky OR sweet chilli sauces.", "69.90", 1);
            foodDao.insert(food);

            // I already hate this. You know what -- screw it. I'm going to include
            // parts of these submenus. It will take me all day to include
            // all the menu items for the other 9 restaurants and 200ish submenus.

            // Hold on. 200?! That can't be right. Let me check.

            // It's 310.

            // https://static.independent.co.uk/s3fs-public/thumbnails/image/2017/07/11/11/harold-0.jpg

            food = new Food("Buffalo Wings","A full portion of chicken wings tossed in Spur’s famous Durky sauce. Served with a portion of chips.", "119.90", 2);
            foodDao.insert(food);
            food = new Food("Nachos Mexicana","A full portion of nachos, smothered in zippy salsa, sticky cheese, chunky cottage cheese and guacamole.", "94.90", 2);
            foodDao.insert(food);
            food = new Food("Large Cheesy Quesadillas","Large tortillas filled with melted cheese. Served with salsa and guacamole.", "89.90", 2);
            foodDao.insert(food);
            food = new Food("Large Cheesy Quesadillas but with chicken strips","Large tortillas filled with melted cheese. Served with salsa and guacamole.", "104.90", 2);
            foodDao.insert(food);

            food = new Food("Greek Salad","Spur’s Garden Salad topped with olives and feta.", "69.90", 3);
            foodDao.insert(food);
            food = new Food("Chicken, Avo & Bacon Salad","Spur's Garden Salad topped with avocado, grilled chicken breast strips, grilled bacon pieces, sautéed mushrooms and crispy croutons.", "84.90", 3);
            foodDao.insert(food);
            food = new Food("Fresh Hot Veg","Ask your waitron about their daily selection.", "26.90", 3);
            foodDao.insert(food);
            food = new Food("Prawn & Avo Salad","Spur’s Garden Salad topped with pan-fried garlic prawns and fresh avo.", "94.90", 3);
            foodDao.insert(food);

            food = new Food("Chargrilled Rump (200g)","Juicy and perfectly prepared.", "115.90", 4);
            foodDao.insert(food);
            food = new Food("Chargrilled Rump (300g)","Juicy and perfectly prepared.", "139.90", 4);
            foodDao.insert(food);
            food = new Food("Spur Fillet (200g)","Still the most tender cut of all!", "159.90", 4);
            foodDao.insert(food);
            food = new Food("Spur Fillet (300g)","Still the most tender cut of all!", "189.90", 4);
            foodDao.insert(food);
            food = new Food("Spur T-Bone Steak (350g)","Generous in size and taste.", "139.90", 4);
            foodDao.insert(food);
            food = new Food("Spur T-Bone Steak (500g)","Generous in size and taste.", "184.90", 4);
            foodDao.insert(food);
            food = new Food("Cheesy Jalapeño Steak (200g)","Rump or Sirloin topped with a slice of melted cheese, jalapeños and cheese sauce.", "129.90", 4);
            foodDao.insert(food);
            food = new Food("Cheesy Jalapeño Steak (300g)","Rump or Sirloin topped with a slice of melted cheese, jalapeños and cheese sauce.", "154.90", 4);
            foodDao.insert(food);
            food = new Food("Spur Sauces","Complement your meal with our selection of sauces. Delicious with our tender steaks, burgers and grills!", "21.90", 4);
            foodDao.insert(food);

            food = new Food("Spur's Famous Pork Spare Ribs (400g)","Succulent pork spare ribs marinated in our great-tasting Spur Basting.", "129.90", 5);
            foodDao.insert(food);
            food = new Food("Spur's Famous Pork Spare Ribs (600g)","Succulent pork spare ribs marinated in our great-tasting Spur Basting.", "169.90", 5);
            foodDao.insert(food);
            food = new Food("Ribs & Chicken (400g)","Marinated pork ribs with a quarter chicken.", "169.90", 5);
            foodDao.insert(food);
            food = new Food("Half Ranch Chicken","Served best in our Spur Basting.", "114.90", 5);
            foodDao.insert(food);
            food = new Food("Lamb Chops","(When available) A feast of juicy and tender lamb chops.", "184.90", 5);
            foodDao.insert(food);

            food = new Food("Chicken Schnitzel (Half)","Crumbed chicken breast fillets, topped with cheese or creamy mushroom sauce.", "72.90", 6);
            foodDao.insert(food);
            food = new Food("Chicken Schnitzel (Full)","Crumbed chicken breast fillets, topped with cheese or creamy mushroom sauce.", "102.90", 6);
            foodDao.insert(food);
            food = new Food("Hawaiian Schnitzel (Half)","Crumbed chicken breast fillets, topped with bacon, cheese and grilled pineapple.", "84.90", 6);
            foodDao.insert(food);
            food = new Food("Hawaiian Schnitzel (Full)","Crumbed chicken breast fillets, topped with bacon, cheese and grilled pineapple.", "115.90", 6);
            foodDao.insert(food);
            food = new Food("Schnitzel Stacker (Half)","Crumbed chicken breast fillets, topped with ham, cheese and creamy mushroom sauce.", "84.90", 6);
            foodDao.insert(food);
            food = new Food("Schnitzel Stacker (Full)","Crumbed chicken breast fillets, topped with ham, cheese and creamy mushroom sauce.", "115.90", 6);
            foodDao.insert(food);

            food = new Food("Calamari & Hake","Our favourite fish combination of tender calamari and hake, lightly dusted and grilled. Served with tartare sauce.", "114.90", 7);
            foodDao.insert(food);
            food = new Food("Cheesy Prawn and Hake (Half)","Hake, lightly dusted and grilled, topped with cheesy garlic prawns.", "94.90", 7);
            foodDao.insert(food);
            food = new Food("Cheesy Prawn and Hake (Full)","Hake, lightly dusted and grilled, topped with cheesy garlic prawns.", "124.90", 7);
            foodDao.insert(food);

            food = new Food("Original Spur Burger","Our finest ground beef patty (160g) grilled to perfection.", "69.90", 8);
            foodDao.insert(food);
            food = new Food("Cheese Burger","Tuck into our single or stacked \"cheesy\" burger - beef, chicken or soya patties served with tomato, onions, gherkins and lettuce. Topped with Melted cheese.", "77.90", 8);
            foodDao.insert(food);
            food = new Food("Cheddamelt Burger","Tangy melted cheese and creamy mushroom or pepper sauce.", "87.90", 8);
            foodDao.insert(food);
            food = new Food("Jalapeno & Cheese Burger","Melted cheese topped with jalapeños and cheese sauce.", "87.90", 8);
            foodDao.insert(food);

            food = new Food("Peppermint Crisp Tart","Crushed Peppermint CRISP pieces smothered between layers of fresh cream and caramel sauce, set atop a crunchy biscuit crumble. Served with cream or soft serve.", "45.90", 9);
            foodDao.insert(food);
            food = new Food("Malva Pudding","Moist, baked sponge pudding topped with caramel syrup and served warm. Served with cream or soft serve.", "45.90", 9);
            foodDao.insert(food);
            food = new Food("Crazy Shakes","An indulgent milkshake loaded with your choice of crazy toppings. Caramel Popcorn or Oreo or Lemon Meringue.", "52.90", 9);
            foodDao.insert(food);
            food = new Food("Classic Waffles","Our traditional home-baked Spur waffle topped with soft serve and caramel syrup.", "39.90", 9);
            foodDao.insert(food);

            food = new Food("Ranch Breakfast","2 fried eggs, 2 rashers of bacon, steak (100g), fried tomato, 2 pork sausages or farm-style wors and mushrooms. Served with chips and 2 slices of toast.", "89.90", 10);
            foodDao.insert(food);
            food = new Food("Unreal Breakfast Special","2 fried eggs, 2 grilled rashers of bacon & fried tomato. Served with chips, 2 slices of toast, jam & butter.", "36.90", 10);
            foodDao.insert(food);
            food = new Food("Chilli Con Carne on Toast ","2 fried eggs on chilli con carne. Served on 2 slices of toast.", "46.90", 10);
            foodDao.insert(food);
            food = new Food("Cheese Griller Breakfast","2 Fried eggs, 2 rashers of grilled bacon, 1 cheese griller and fried tomato. Served with chips and 2 slices of toast.", "56.90", 10);
            foodDao.insert(food);

            food = new Food("Milk","Allergens! Cow’s Milk", "12.90", 11);
            foodDao.insert(food);

            food = new Food("FRESH OYSTERS (1)","where available", "20.00", 12);
            foodDao.insert(food);
            food = new Food("FRESH OYSTERS (12)","where available", "200.00", 12);
            foodDao.insert(food);
            food = new Food("MUSSELS","in lemon garlic sauce", "49.00", 12);
            foodDao.insert(food);
            food = new Food("MUSSELS","in a tomato based Mediterranean sauce", "58.00", 12);
            foodDao.insert(food);
            food = new Food("VENUS MEZE PLATTER","calamari bowl, salmon bites, zucchini fritters, halloumi, olives, tarama, tzatziki, cucumber, tomato", "166.00", 12);
            foodDao.insert(food);
            food = new Food("FIRECRACKER SQUID","tender squid meat in a crisp crust with a touch of chilli", "42.00", 12);
            foodDao.insert(food);
            food = new Food("CALAMARI","grilled / fried", "49.00", 12);
            foodDao.insert(food);
            food = new Food("CALAMARI BOWL","grilled calamari tossed in Med salsa topped with rocket", "54.00", 12);
            foodDao.insert(food);
            food = new Food("CRUMBED PRAWN TAILS","served with herb mayo", "48.00", 12);
            foodDao.insert(food);
            food = new Food("HALLOUMI CHEESE","", "49.00", 12);
            foodDao.insert(food);
            food = new Food("MED RICE BALLS","crispy rice and halloumi balls served with herb mayo", "37.00", 12);
            foodDao.insert(food);
            food = new Food("SPANISH ANCHOVIES","served with med salsa", "54.00", 12);
            foodDao.insert(food);
            food = new Food("SALMON BITES","crumbless grilled salmon & trout cakes", "47.00", 12);
            foodDao.insert(food);
            food = new Food("ZUCCHINI FRITTERS","", "35.00", 12);
            foodDao.insert(food);
            food = new Food("MEDITERRANEAN SALSA","tomato, olives & capers in a zesty herb mix", "30.00", 12);
            foodDao.insert(food);
            food = new Food("TZATZIKI OR TARAMA","", "30.00", 12);
            foodDao.insert(food);
            food = new Food("OLIVES OR FETA","", "27.00", 12);
            foodDao.insert(food);

            food = new Food("TOMATO FISH SOUP","", "45.00", 13);
            foodDao.insert(food);
            food = new Food("CREAMY SALMON SOUP","", "69.00", 13);
            foodDao.insert(food);
            food = new Food("VILLAGE SALAD FOR 1","", "54.00", 13);
            foodDao.insert(food);
            food = new Food("VILLAGE TABLE SALAD","", "109.00", 13);
            foodDao.insert(food);
            food = new Food("MED VEG SALAD","roasted Mediterranean vegetables with halloumi cheese", "57.0", 13);
            foodDao.insert(food);
            food = new Food("SEARED SALMON SALAD","70g salmon or tuna with fresh avo, ponzu (soy & citrus) sauce & sesame seeds", "90.0", 13);
            foodDao.insert(food);

            food = new Food("CALAMARI","", "99.00", 14);
            foodDao.insert(food);
            food = new Food("CALAMARI 3 WAYS","grilled, fried and cajun-style", "139.00", 14);
            foodDao.insert(food);
            food = new Food("CALAMARI STEAK","", "89.00", 14);
            foodDao.insert(food);

            food = new Food("FAMOUS FISH & CHIPS (200g)","all time favourite hake", "70.00", 15);
            foodDao.insert(food);
            food = new Food("OB FISH & CHIPS (200g)","Basa best served fried", "63.00", 15);
            foodDao.insert(food);
            food = new Food("TILAPIA","light meal, topped with spicy butter, served with paprika sweet potato coated. This fish is a sustainable fresh water option.", "114.00", 15);
            foodDao.insert(food);
            food = new Food("KINGKLIP (200g)","", "109.00", 15);
            foodDao.insert(food);
            food = new Food("KINGKLIP (300g)","", "149.00", 15);
            foodDao.insert(food);
            food = new Food("SPECIALITY FISH (300g)","dorado or yellowtail, depending on availability", "105.00", 15);
            foodDao.insert(food);
            food = new Food("SOLE (180g)","", "125.00", 15);
            foodDao.insert(food);
            food = new Food("GRILLED SALMON (200g)","", "170.00", 15);
            foodDao.insert(food);
            food = new Food("GRILLED TUNA (200g)","", "149.00", 15);
            foodDao.insert(food);

            food = new Food("PLATTER FOR 2","6 mussels, 12 prince prawns, fish, calamari & calamari heads", "295.00", 16);
            foodDao.insert(food);
            food = new Food("SOLEMATE PLATTER","18 prince prawns, calamari, calamari steak, mussels & village salad", "340.00", 16);
            foodDao.insert(food);
            food = new Food("FAMILY PLATTER","12 prince prawns, calamari & 4 portions hake", "350.00", 16);
            foodDao.insert(food);
            food = new Food("FULL DECK PLATTER","30 prince prawns, mussels, calamari, calamari steak strips & fish", "499.00", 16);
            foodDao.insert(food);

            food = new Food("SALMON MED RICE","with mussels and Mediterranean vegetables", "99.00", 17);
            foodDao.insert(food);

            food = new Food("HALLOUMI CHEESE","", "35.00", 18);
            foodDao.insert(food);
            food = new Food("5 MUSSELS","", "40.00", 18);
            foodDao.insert(food);
            food = new Food("CALAMARI","in lemon garlic sauce", "40.00", 18);
            foodDao.insert(food);
            food = new Food("5 PRINCE PRAWNS","", "42.00", 18);
            foodDao.insert(food);
            food = new Food("CHIPS, RICE, SIDE SALAD, STIR-FRIED VEG, BUTTERNUT & SPINACH","", "20.00", 18);
            foodDao.insert(food);
            food = new Food("ROASTED MED VEG OR PAPRIKA SWEET POTATO","", "29.00", 18);
            foodDao.insert(food);

            food = new Food("BITE OF THE OCEAN","3 prince prawns, fish & calamari", "118.00", 19);
            foodDao.insert(food);
            food = new Food("PLATTER FOR 1","3 mussels, 6 prince prawns, fish, calamari & calamari heads", "150.00", 19);
            foodDao.insert(food);
            food = new Food("THE BIG ONE","4 king prawns, calamari & fish", "199.00", 19);
            foodDao.insert(food);

            food = new Food("PRAWN SPECIAL","12 prince prawns", "114.00", 20);
            foodDao.insert(food);
            food = new Food("PRINCE PRAWNS (18)","", "156.00", 20);
            foodDao.insert(food);
            food = new Food("PRINCE PRAWNS (24)","", "195.00", 20);
            foodDao.insert(food);
            food = new Food("QUEEN PRAWNS (8)","", "149.00", 20);
            foodDao.insert(food);
            food = new Food("QUEEN PRAWNS (12)","", "209.00", 20);
            foodDao.insert(food);
            food = new Food("KING PRAWNS (6)","", "179.00", 20);
            foodDao.insert(food);
            food = new Food("KING PRAWNS (10)","", "269.00", 20);
            foodDao.insert(food);

            food = new Food("CHIPS, RICE, STIR-FRIED VEG, BUTTERNUT & SPINACH OR SIDE SALAD","Choose one option with your meal", "0.00", 21);
            foodDao.insert(food);
            food = new Food("ROASTED MED VEG","", "8.00", 21);
            foodDao.insert(food);
            food = new Food("PAPRIKA SWEET POTATO","", "8.00", 21);
            foodDao.insert(food);

            food = new Food("TROUT","with clams in a spicy butter", "140.00", 22);
            foodDao.insert(food);
            food = new Food("TROUT","with 3 prawns in a light Mediterranean sauce", "150.00", 22);
            foodDao.insert(food);
            food = new Food("FISH & CALAMARI","Fish combos are served with hake", "106.00", 22);
            foodDao.insert(food);
            food = new Food("CALAMARI & KINGKLIP (200g)","", "150.00", 22);
            foodDao.insert(food);
            food = new Food("CALAMARI & KINGKLIP (300g)","", "190.00", 22);
            foodDao.insert(food);
            food = new Food("PRAWNS & MUSSELS","Prawn combos are served with 6 prince prawns, unless specified", "106.00", 22);
            foodDao.insert(food);
            food = new Food("PRAWNS & CALAMARI","Prawn combos are served with 6 prince prawns, unless specified", "147.00", 22);
            foodDao.insert(food);
            food = new Food("PRAWNS & FISH","Prawn combos are served with 6 prince prawns, unless specified -  Fish combos are served with hake", "112.00", 22);
            foodDao.insert(food);
            food = new Food("PRAWNS & KINGKLIP (200g)","Prawn combos are served with 6 prince prawns, unless specified", "150.00", 22);
            foodDao.insert(food);
            food = new Food("PRAWNS & KINGKLIP (300g)","Prawn combos are served with 6 prince prawns, unless specified", "190.00", 22);
            foodDao.insert(food);

            food = new Food("CHIPS","add an extra portion", "20.00", 23);
            foodDao.insert(food);
            food = new Food("SALMON BITES","crumbless grilled salmon & trout cakes", "47.00", 23);
            foodDao.insert(food);
            food = new Food("AMAZE BALLS","rice & halloumi balls deep fried and served with tomato sauce (vegetarian)", "37.00", 23);
            foodDao.insert(food);
            food = new Food("SAUCY FISH BITES","battered hake, served with 1000 island sauce, chips and rice", "45.00", 23);
            foodDao.insert(food);
            food = new Food("SUSHILICIOUS","2 mini salmon california rolls, 2 mini avo california rolls, 2 mini salmon nigiri, 2 mini prawn gunkans, cucumber sticks, carrot stick and potato strips", "42.00", 23);
            foodDao.insert(food);
            food = new Food("CRUMBED PRAWN TAILS","with herb mayo", "48.00", 23);
            foodDao.insert(food);
            food = new Food("PAN O' PRAWNS","5 perfectly grilled prawns, served with 1000 island sauce, chips and rice", "55.00", 23);
            foodDao.insert(food);
            food = new Food("KIDS PLATTER FOR 1","battered hake fingers, calamari, halloumi cheese chunks and 2 grilled prawns, served with 1000 island sauce, chips and rice", "69.00", 23);
            foodDao.insert(food);
            food = new Food("CRISPY CALAMARI","delicious fried calamari, served with 1000 island sauce, chips and rice", "55.00", 23);
            foodDao.insert(food);
            food = new Food("SURFBOARDS","flat bread with tomato base topped with halloumi (vegetarian)", "29.00", 23);
            foodDao.insert(food);
            food = new Food("SURFBOARDS","flat bread with tomato base topped with halloumi & prawns", "49.00", 23);
            foodDao.insert(food);

            food = new Food("YOGI BERRY","the perfect blend of delicious yoghurt, ice cream and berries", "26.00", 24);
            foodDao.insert(food);
            food = new Food("CHOC CRUNCH","creamy and delicious with a sprinkle of choc chip biscuits", "26.00", 24);
            foodDao.insert(food);
            food = new Food("ZESTY LEMON","a sweet surprise of citrus and shortbread", "26.00", 24);
            foodDao.insert(food);

            food = new Food("BAKLAVA CHEESECAKE","baked cheesecake on a crunchy base topped with a honey nut suryp", "47.00", 25);
            foodDao.insert(food);
            food = new Food("CHOCOLATE LAVA CAKE","rich Belgian chocolate cake with a soft chocolate centre served with cream or ice cream", "49.00", 25);
            foodDao.insert(food);
            food = new Food("ICE CREAM WITH DECADENT SAUCE","served with chocolate or butter toffee sauce", "28.00", 25);
            foodDao.insert(food);
            food = new Food("ICE CREAM WITH HALVA","served with butter toffee sauce", "30.00", 25);
            foodDao.insert(food);
            food = new Food("ICE CREAM WITH TURKISH DELIGHT","served with rose syrup", "30.00", 25);
            foodDao.insert(food);
            food = new Food("PEAR & WHITE CHOC PUDDING","baked pears in a vanilla sponge with rich white chocolate served with ice cream or cream", "47.00", 25);
            foodDao.insert(food);
            food = new Food("TOFFEE NUT PIE","sticky toffee and nut combination in a pastry topped with chocolate ganache. Served hot (or cold on request) with ice cream or cream", "49.00", 25);
            foodDao.insert(food);
            food = new Food("BAKLAVA","Mama Liza's original recipe served with cream or ice cream", "50.00", 25);
            foodDao.insert(food);
            food = new Food("KATAIFI","Mama Liza's original recipe served with cream or ice cream", "42.00", 25);
            foodDao.insert(food);

            food = new Food("ICE CREAM & HALVA","served with butter toffee sauce", "30.00", 26);
            foodDao.insert(food);
            food = new Food("ICE CREAM & TURKISH DELIGHT","served with rose syrup", "30.00", 26);
            foodDao.insert(food);
            food = new Food("ICE CREAM WITH DECADENT SAUCE","served with chocolate or butter toffee sauce", "28.00", 26);
            foodDao.insert(food);
            food = new Food("PEAR & WHITE CHOC PUDDING","baked pears in a vanilla sponge with rich white chocolate served with ice cream or cream", "47.00", 26);
            foodDao.insert(food);
            food = new Food("CHOCOLATE LAVA CAKE","rich Belgian chocolate cake with a soft chocolate centre served with cream or ice cream", "49.00", 26);
            foodDao.insert(food);
            food = new Food("TOFFEE NUT PIE","sticky toffee and nut combination in a pastry topped with chocolate ganache, served hot (or cold on request) with ice cream or cream", "49.00", 26);
            foodDao.insert(food);
            food = new Food("KIDS BAKLAVA","layered flaky pastry with pecan nuts and cinnamon filling, covered in syrup, served with ice cream or cream", "30.00", 26);
            foodDao.insert(food);
            food = new Food("KATAIFI","shredded pastry filled with almonds and pecan nuts soaked in syrup, served with ice cream or cream", "42.00", 26);
            foodDao.insert(food);

            food = new Food("PANKO PRAWN","", "39.00", 27);
            foodDao.insert(food);
            food = new Food("CALAMARI","", "39.00", 27);
            foodDao.insert(food);
            food = new Food("WASABI PRAWN","", "44.00", 27);
            foodDao.insert(food);

            food = new Food("CREAMY TUNA","tuna with sesame garlic mayo, olives, spring onion and a soy balsamic dressing", "80.00", 28);
            foodDao.insert(food);

            food = new Food("SWEET PRAWN","", "59.00", 29);
            foodDao.insert(food);
            food = new Food("PANKO SALMON","", "59.00", 29);
            foodDao.insert(food);

            food = new Food("SUSHI FOR 1","3 pc prawn nigiri, 3 pc crab stick maki, 4 pc salmon California rolls, 4 pc tuna California rolls", "108.00", 30);
            foodDao.insert(food);
            food = new Food("SUSHI FOR 2","6 pc prawn nigiri, 6 pc crab stick maki, 8 pc salmon California rolls, 8 pc tuna California rolls", "199.00", 30);
            foodDao.insert(food);
            food = new Food("CHAN'S PLATTER","2 pc salmon roses, 3 pc avo maki, 3 pc cucumber maki, 4 pc crab stick California rolls", "105.00", 30);
            foodDao.insert(food);
            food = new Food("BONSAI PLATTER (Vegetarian)","3 pc cucumber maki, 3 pc avo maki, 4 pc Greek California rolls, 4 pc tomato nigiri", "80.00", 30);
            foodDao.insert(food);
            food = new Food("RISING SUN PLATTER","2 pc salmon roses, 3 pc cucumber maki, 3 pc crab stick maki, 4 pc salmon California rolls, 4 pc prawn California rolls", "120.00", 30);
            foodDao.insert(food);
            food = new Food("SALMON PLATTER","4 pc California rolls, 6 pc maki, 6 pc nigiri, 6 pc sashimi", "249.00", 30);
            foodDao.insert(food);
            food = new Food("TWO WAY PLATTER","3 pc salmon roses, 6 pc sweet prawn futomaki, 8 pc rainbow rolls, 8 pc fried crab stick California rolls", "225.00", 30);
            foodDao.insert(food);
            food = new Food("FUSION CRUNCH PLATTER","6 pc panko salmon futomaki, 4 pc lemon salmon California roll, 4 pc crunchy Athena California roll, 4 pc coriander bomb California roll", "140.00", 30);
            foodDao.insert(food);
            food = new Food("FLOWER POWER PLATTER","3 pc salmon roses, 3 pc tuna tulips, 3 pc spicy roses", "159.00", 30);
            foodDao.insert(food);

            food = new Food("SALMON ROSES","", "70.00", 31);
            foodDao.insert(food);
            food = new Food("SPICY SALMON ROSES","", "64.00", 31);
            foodDao.insert(food);
            food = new Food("TUNA TULIPS","", "60.00", 31);
            foodDao.insert(food);

            food = new Food("PRAWN (4pcs)","", "45.00", 32);
            foodDao.insert(food);
            food = new Food("SALMON (4pcs)","", "45.00", 32);
            foodDao.insert(food);
            food = new Food("TUNA (4pcs)","", "45.00", 32);
            foodDao.insert(food);
            food = new Food("CRAB STICK (4pcs)","", "35.00", 32);
            foodDao.insert(food);
            food = new Food("CRUNCHY ATHENA (4pcs)","", "34.00", 32);
            foodDao.insert(food);
            food = new Food("CORIANDER BOMB (4pcs)","", "42.00", 32);
            foodDao.insert(food);
            food = new Food("FRIED PRAWN (4pcs)","", "46.00", 32);
            foodDao.insert(food);
            food = new Food("TEMPURA ROCK SHRIMP (4pcs)","", "65.00", 32);
            foodDao.insert(food);
            food = new Food("LEMON SALMON (4pcs)","", "48.00", 32);
            foodDao.insert(food);
            food = new Food("PRAWN (8pcs)","", "78.00", 32);
            foodDao.insert(food);
            food = new Food("SALMON (8pcs)","", "78.00", 32);
            foodDao.insert(food);
            food = new Food("TUNA (8pcs)","", "78.00", 32);
            foodDao.insert(food);
            food = new Food("CRAB STICK (8pcs)","", "49.00", 32);
            foodDao.insert(food);
            food = new Food("CRUNCHY ATHENA (8pcs)","", "59.00", 32);
            foodDao.insert(food);
            food = new Food("CORIANDER BOMB (8pcs)","", "69.00", 32);
            foodDao.insert(food);
            food = new Food("FRIED PRAWN (8pcs)","", "80.00", 32);
            foodDao.insert(food);
            food = new Food("TEMPURA ROCK SHRIMP (8pcs)","", "110.00", 32);
            foodDao.insert(food);
            food = new Food("LEMON SALMON (8pcs)","", "85.00", 32);
            foodDao.insert(food);

            food = new Food("SALMON & AVO","", "49.00", 33);
            foodDao.insert(food);
            food = new Food("TUNA & AVO","", "49.00", 33);
            foodDao.insert(food);
            food = new Food("SALMON & AVO","", "89.00", 33);
            foodDao.insert(food);
            food = new Food("TUNA & AVO","", "89.00", 33);
            foodDao.insert(food);

            food = new Food("3 PIECE PLATTER","", "68.00", 34);
            foodDao.insert(food);
            food = new Food("9 PIECE PLATTER","", "166.00", 34);
            foodDao.insert(food);

            food = new Food("CUCUMBER","", "30.00", 35);
            foodDao.insert(food);
            food = new Food("CRAB STICKS","", "30.00", 35);
            foodDao.insert(food);
            food = new Food("AVO","", "36.00", 35);
            foodDao.insert(food);
            food = new Food("PRAWN","", "48.00", 35);
            foodDao.insert(food);
            food = new Food("SALMON","", "48.00", 35);
            foodDao.insert(food);
            food = new Food("TUNA","", "48.00", 35);
            foodDao.insert(food);

            food = new Food("PRAWN","", "50.00", 36);
            foodDao.insert(food);
            food = new Food("TOMATO","", "35.00", 36);
            foodDao.insert(food);
            food = new Food("SALMON","", "54.00", 36);
            foodDao.insert(food);
            food = new Food("TUNA","", "54.00", 36);
            foodDao.insert(food);

            // Why on earth does Parrots have SEVENTY ONE SUBMENUS!?
            // No. Just no. There's no way I'm filling this entire thing.

            food = new Food("Smashing Rocket","Smashed Avo, topped with roasted cherry tomatos, rocket, caramalized onions, freshly squeezed lemon juice and sesame seeds, served with rye toast", "58.90", 37);
            foodDao.insert(food);
            food = new Food("Omega Plate","Smoked salmon served on rye toast with scrambled eggs, avocado* & cottage cheese", "96.90", 37);
            foodDao.insert(food);

            food = new Food("Classic","2 Fried eggs, 3 bacon rashers, baby spinach, grilled button mushrooms and grilled tomato", "59.90", 38);
            foodDao.insert(food);
            food = new Food("Hash Breakfast","2 Fried eggs, 2 bacon rashers, 1 hash brown, grilled tomato & toast", "35.90", 38);
            foodDao.insert(food);
            food = new Food("The Griller ","2 Fried eggs, 2 bacon rashers, 1 cheese griller, grilled tomato and toast", "45.90", 38);
            foodDao.insert(food);
            food = new Food("House Breakfast","1 Fried egg, 2 bacon rashers, grilled tomato, 1 flap jack, chips and toast", "36.90", 38);
            foodDao.insert(food);

            food = new Food("Breakfast Wrap","Filled with fluffy scrambled eggs, 2 bacon rashers, rocket, mozzarella cheese and fresh tomato – served with chips", "56.90", 39);
            foodDao.insert(food);
            food = new Food("Chicken Livers on Toast","Mild peri-peri Portuguese-style chicken livers with roasted cocktail tomatoes, served on freshly baked ciabatta bread & scrambled eggs", "48.90", 39);
            foodDao.insert(food);
            food = new Food("English Breakfast Muffin","Filled with 1 egg, 2 bacon rashers, 1 hash brown, grilled tomato, cheddar cheese and rocket – served with chips", "58.90", 39);
            foodDao.insert(food);

            food = new Food("Bacon & Banana French Toast","Sprinkled with cinnamon & served with syrup", "57.90", 40);
            foodDao.insert(food);
            food = new Food("Berries French Toast","Served with berry compote & cream cheese", "50.90", 40);
            foodDao.insert(food);
            food = new Food("Flapjack Stack","5 Flapjacks served with syrup & cream OR ice cream", "44.90", 40);
            foodDao.insert(food);

            food = new Food("+ Avocado*","", "15.90", 41);
            foodDao.insert(food);
            food = new Food("+ Hash brown","", "11.90", 41);
            foodDao.insert(food);
            food = new Food("+ Bacon rasher (3)","", "16.90", 41);
            foodDao.insert(food);
            food = new Food("+ Frankfurter sausage","", "11.90", 41);
            foodDao.insert(food);
            food = new Food("+ Bockwurst sausage","", "18.90", 41);
            foodDao.insert(food);
            food = new Food("+ Baked beans","", "12.90", 41);
            foodDao.insert(food);
            food = new Food("+ Cheese griller","", "12.90", 41);
            foodDao.insert(food);

            // I should replace that currently useless search button on the
            // NewMenuActivity Toolbar with a little + icon. So that people
            // can actually use this app with how incomplete I'm leaving some
            // menus.

            // I love going to Parrots, but now I hate just how much variety
            // their menu has.

            food = new Food("Garlic & Cheese Roll ","Foot long roll with fresh garlic, melted cheddar & mozzarella cheese", "43.90", 42);
            foodDao.insert(food);
            food = new Food("Halloumi ","Golden fried Cypriot cheese fingers, served with sweet chilli sauce", "59.90", 42);
            foodDao.insert(food);
            food = new Food("Jalapeño Poppers","3 Crumbed jalapeño peppers filled with mozzarella & cream cheese, served with sweet chilli sauce", "43.90", 42);
            foodDao.insert(food);
            food = new Food("Chicken Trinchardo","Portuguese-style chicken strips in spicy creamy sauce, served with fresh ciabatta bread", "59.90", 42);
            foodDao.insert(food);
            food = new Food("Buffalo Wings","6 Buffalo wings basted in your choice of sauce BBQ, Parrots Comeback sauce, sweet chilli or mild creamy peri-peri", "58.90", 42);
            foodDao.insert(food);

            food = new Food("Crumbed Chicken Strips","", "75.90", 43);
            foodDao.insert(food);
            food = new Food("BBQ Buffalo Wings & Ribs","", "85.90", 43);
            foodDao.insert(food);
            food = new Food("Calamari and Squid Heads","", "88.90", 43);
            foodDao.insert(food);

            food = new Food("SPUDS","Baked potato topped with Swiss Cheese sauce, a side salad & your choice of a topping. Check the menu to see what toppings they have.", "62.90", 44);
            foodDao.insert(food);

            food = new Food("Health ","Baby spinach, roasted butternut, grilled halloumi cheese, fried baby marrow, tomato, red onion & honey mustard mayo", "72.90", 45);
            foodDao.insert(food);
            food = new Food("Sparerib","Deboned sparerib, lettuce, tomato, onion & Parrots Comeback sauce", "77.90", 45);
            foodDao.insert(food);
            food = new Food("Roast Chicken","Roast chicken, bacon, avocado*, lettuce, tomato cucumber & honey mustard mayo", "73.90", 45);
            foodDao.insert(food);
            food = new Food("Steak","Sliced Kalahari rump, mushrooms, cheddar cheese, caramelised onion, lettuce, tomato, cucumber, sweet chilli sauce & honey mustard mayo", "81.90", 45);
            foodDao.insert(food);

            food = new Food("Cheese ","Mozzarella, cheddar & Napoletana sauce", "68.90", 46);
            foodDao.insert(food);
            food = new Food("Ham","Mozzarella, cheddar, Napoletana sauce, hickory ham & chunkey tomato", "75.90", 46);
            foodDao.insert(food);
            food = new Food("Chilli Beef Mince","Mozzarella, cheddar, chilli beef mince, black kidney beans, chunky tomato & Napoletana sauce", "78.90", 46);
            foodDao.insert(food);
            food = new Food("Cajun","Mozzarella, cheddar, cajun chicken, Enchilada sauce, chunky tomato & feta", "81.90", 46);
            foodDao.insert(food);

            food = new Food("","Toasted pita bread filled with mozzarella cheese, your choice of two fillings & served with chips or side salad", "72.90", 47);
            foodDao.insert(food);

            food = new Food("","+ Butter, cheese & preserves", "42.90", 48);
            foodDao.insert(food);
            food = new Food("","+ Roast chicken mayo, bacon, avocado* & honey mustard mayo", "65.90", 48);
            foodDao.insert(food);
            food = new Food("","+ Hickory ham, mozzarella cheese & tomato", "62.90", 48);
            foodDao.insert(food);

            food = new Food("Cheese","", "43.90", 49);
            foodDao.insert(food);
            food = new Food("Chicken Mayo","", "61.90", 49);
            // 1 500 lines of code!
            foodDao.insert(food);
            food = new Food("Bacon, egg, cheddar cheese & tomato","", "62.90", 49);
            foodDao.insert(food);
            food = new Food("Tuna mayo, tomato & mozarella cheese","", "64.90", 49);
            foodDao.insert(food);
            food = new Food("Bacon, avo & feta","", "68.90", 49);
            foodDao.insert(food);

            food = new Food("The Club","Crumbed chicken fillet, bacon, mozzarella cheese, tomato & honey mustard mayo", "85.90", 50);
            foodDao.insert(food);
            food = new Food("Prime Time","Sliced Kalahari rump, rocket, caramelised onion, tomato, cheddar cheese & honey mustard mayo", "86.90", 50);
            foodDao.insert(food);
            food = new Food("Philly Chicken","Chicken fillet, caramalised onions, roast peppers & mozzarella cheese", "73.90", 50);
            foodDao.insert(food);

            food = new Food("George’s Greek Salad ","Cos lettuces, tomato, mixed peppers, red onion, olives, feta & origanum topped with a special greek vinaigrette dressing", "76.90", 51);
            foodDao.insert(food);
            food = new Food("Beef","Seared Kalahari sirloin steak, caramelised onions, rocket, roasted cherry tomato, mixed peppers & vinaigrette dressing", "97.90", 51);
            foodDao.insert(food);
            food = new Food("Smoked Chicken","Smoked chicken, orange slices, beetroot, cherry tomato, spring onion & honey mustard dressing", "88.90", 51);
            foodDao.insert(food);
            food = new Food("Tuna","Tuna chunks, boiled egg, Peppadew®, carrots, red onion & vinaigrette dressing", "79.90", 51);
            foodDao.insert(food);
            food = new Food("Biltong & Halloumi ","Biltong, cherry tomato, red cabbage, sesame seeds, grilled halloumi cheese fingers topped with sweet chilli dressing", "95.90", 51);
            foodDao.insert(food);
            food = new Food("Chicken & Deep Fried Feta ","Grilled chicken, deep fried crumbed feta, with cherry tomato, green pepper, red pepper, red cabbage, with a vinaigrette dressing", "89.90", 51);
            foodDao.insert(food);

            // I'm seriously starting to wonder how long a slower device
            // will take to fill this entire database...

            food = new Food("Two pancakes topped with Swiss Cheese sauce & your choice of a filling.","", "62.90", 52);
            foodDao.insert(food);

            // Who cares, it's asynchronous. Not like the user will notice
            // it anyways.

            food = new Food("Topped with gravy & served with chips or a side salad","", "46.90", 53);
            foodDao.insert(food);
            food = new Food("Burger Pie","", "53.90", 53);
            foodDao.insert(food);

            food = new Food("“Say Cheese”","Classic burger with mozzarella, cheddar & Emmentaler cheese", "86.90", 54);
            foodDao.insert(food);
            food = new Food("Maverick","Classic burger with mushroom sauce, caramelised onions & Emmentaler cheese", "95.90", 54);
            foodDao.insert(food);
            food = new Food("Parrots no.33*","Double classic burger with bacon, mushrooms, pickles, mozzarella & cheddar cheese", "121.90", 54);
            foodDao.insert(food);
            food = new Food("The Rocket ","200g Pure Kalahari beef patty, rocket, roasted cherry tomato, caramelised onions, cheddar cheese, mozzarella cheese & guacamole", "99.90", 54);
            foodDao.insert(food);
            food = new Food("The Hashtag","Classic burger with a hashbrown, onion rings, bacon, Emmentaler cheese topped with a pickle", "103.90", 54);
            foodDao.insert(food);
            food = new Food("Memphis","Classic burger with bacon, egg & cheddar cheese topped with a pineapple ring", "94.90", 54);
            foodDao.insert(food);
            food = new Food("California Beach","Double juicy grilled chicken burger with grilled pineapple & mozzarella cheese topped with mayo", "82.90", 55);
            foodDao.insert(food);

            food = new Food("Beef Prego","2 x 100g Sirlion steak & caramalised onion served on a bun with home-made round cut chips & coleslaw", "88.90", 55);
            foodDao.insert(food);
            food = new Food("Chicken Prego","Double chicken breast & caramalised onions served on a bun with home-made round cut chips & coleslaw", "76.90", 55);
            foodDao.insert(food);

            food = new Food("300g ","Basted in our famous Comeback sauce, served with your choice of starch & veg of the day", ".90", 56);
            foodDao.insert(food);
            food = new Food("600g ","Basted in our famous Comeback sauce, served with your choice of starch & veg of the day", ".90", 56);
            foodDao.insert(food);

            // Why is there so much variety?

            food = new Food("Rump 500g ","", "184.90", 57);
            foodDao.insert(food);
            food = new Food("Sirloin 500g","", "184.90", 57);
            foodDao.insert(food);
            food = new Food("Fillet 300g ","", "189.90", 57);
            foodDao.insert(food);
            food = new Food("T-Bone 600g ","", "184.90", 57);
            foodDao.insert(food);

            food = new Food("Ribs & Chicken","300g Pork ribs & half spring chicken", "163.90", 58);
            foodDao.insert(food);
            food = new Food("Ribs & Chops","300g Pork ribs & 2 x 100g lamb chops", "189.90", 58);
            foodDao.insert(food);
            food = new Food("Mixed Grills","200g Kalahari rump steak, boerewors, 100g lamb chop & 3 Buffalo wings, 2 rashers bacon & 1 egg", "174.90", 58);
            foodDao.insert(food);
            food = new Food("Ribs & Prawns","300g Pork ribs & 6 prawns", "191.90", 58);
            foodDao.insert(food);
            food = new Food("Surf ‘n Turf","200g Kalahari rump steak & half calamari", "179.90", 58);
            foodDao.insert(food);

            food = new Food("Chicken Cordon Blue","Crumbed chicken fillet stuffed with hickory ham & mozzarella cheese – topped with a sauce of your choice. Served with your choice of starch & veg of the day", "119.90", 59);
            foodDao.insert(food);
            food = new Food("Chicken Breasts","Two flame-grilled chicken breasts topped with a sauce of your choice. Served with your choice of starch & veg of the day", "90.90", 59);
            foodDao.insert(food);
            food = new Food("Chicken Al Forno","Two flame-grilled chicken fillets topped with avocado*, melted cheddar & mozzarella cheese, served with your choice of starch & veg of the day", "106.90", 59);
            foodDao.insert(food);
            food = new Food("Chicken Parmigiano","Two crumbed chicken fillets topped with Napoletana sauce & parmesan cheese, served with spaghetti", "96.90", 59);
            foodDao.insert(food);
            food = new Food("Health Grill","Two flame-grilled chicken fillets marinated in lemon & flavoured with origanum, served with Greek salad on the side & veg of the day", "86.90", 59);
            foodDao.insert(food);

            food = new Food("Vegetable ","Served with rice, vegetables, sweet soy sauce & a sprinkle of sesame seeds", "63.90", 60);
            foodDao.insert(food);
            food = new Food("Chicken","Served with rice, vegetables, sweet soy sauce & a sprinkle of sesame seeds", "98.90", 60);
            foodDao.insert(food);
            food = new Food("Beef","Served with rice, vegetables, sweet soy sauce & a sprinkle of sesame seeds", "104.90", 60);
            foodDao.insert(food);
            food = new Food("Prawn & Calamari","Served with rice, vegetables, sweet soy sauce & a sprinkle of sesame seeds", "124.90", 60);
            foodDao.insert(food);

            food = new Food("Falkland Calamari Tubes","", ".90", 61);
            foodDao.insert(food);
            food = new Food("Hake","", ".90", 61);
            foodDao.insert(food);
            food = new Food("Platter for 1","Hake, 3 prawns, 3 mussels & calamari", ".90", 61);
            foodDao.insert(food);

            // I firmly believe that pineapple does not belong on pizza.
            // As a result, the 5 of their 21 pizzas that I include here
            // should not have pineapples on them.

            food = new Food("Margherita ","Tomato base, mozzarella cheese & herbs", "66.90", 62);
            foodDao.insert(food);
            food = new Food("Veggie Lovers ","Mushroom, asparagus, onion, Peppadew®, green pepper, olives & garlic", "88.90", 62);
            foodDao.insert(food);
            food = new Food("Bacardo","Bacon & avocado*", "82.90", 62);
            foodDao.insert(food);
            food = new Food("Meat Lovers","Boerewors, bacon, ham, salami, sausage, pepperoni & tomato slices", "101.90", 62);
            foodDao.insert(food);
            food = new Food("Mexicana","Bolognese mince, chilli, green pepper, onion & garlic", "99.90", 62);
            foodDao.insert(food);

            food = new Food("Fettucine Alfredo","Hickory ham & mushrooms in creamy white sauce", "78.90", 63);
            foodDao.insert(food);
            food = new Food("Fettucine Alla Supremo","Creamy sweet chilli chicken strips, Peppadew® & feta cheese", "84.90", 63);
            foodDao.insert(food);
            food = new Food("Penne Alla Mexican","Chicken, mushrooms, chilli, green pepper & feta cheese in creamy white sauce", "89.90", 63);
            foodDao.insert(food);
            food = new Food("Penne Alla Olive","Chicken strips, Peppadew®, sundried tomato & olives", "82.90", 63);
            foodDao.insert(food);

            food = new Food("Ravioli Alla Bolognese","Pasta filled with bolognese mince in a traditional home-made sauce", "90.90", 64);
            foodDao.insert(food);

            food = new Food("Beef Lasagne","Layered pasta with beef bolognese mince & white sauce topped with mozzarella cheese", "90.90", 65);
            foodDao.insert(food);
            food = new Food("Chicken Lasagne","Layered pasta with chicken strips, mushrooms & white sauce topped with mozzarella cheese", "90.90", 65);
            foodDao.insert(food);

            food = new Food("Cakes","Full portion", "44.90", 66);
            foodDao.insert(food);
            food = new Food("Home-made Muffins","+ Served with butter & homemade preserves", ".90", 66);
            foodDao.insert(food);

            food = new Food("2 Pancakes","Filled with banana & dusted with cinnamon sugar, topped with caramel.", "41.90", 67);
            foodDao.insert(food);
            food = new Food("Chocolate Brownies ","Chocolate brownie topped with chocolate sauce, nuts &  served with ice cream", "45.90", 67);
            foodDao.insert(food);
            food = new Food("Malva Pudding ","Baked Malva pudding served with custard & ice cream", "47.90", 67);
            foodDao.insert(food);

            food = new Food("Assorted Rolls (16pc)","4pc California Salmon\n" +
                    "4pc Maki Salmon\n" +
                    "4pc Maki Tuna\n" +
                    "4pc Californian Prawn", "119.90", 68);
            foodDao.insert(food);
            food = new Food("Parrots Garden (11pc) ","6pc Fashion Sandwich Salmon\n" +
                    "3pc Nigiri Salmon\n" +
                    "2pc Salmon Roses", "155.90", 68);
            foodDao.insert(food);
            food = new Food("Sakula (20pc)","8pc Sakula Salmon Roll\n" +
                    "8pc Sakula Prawn Roll\n" +
                    "2pc Kiri Cups\n" +
                    "2pc Battleship Caviar", "169.90", 68);
            foodDao.insert(food);
            food = new Food("Vegetarian ","8pc Dragon Roll v\n" +
                    "8pc Maki Avocado*\n" +
                    "1pc Vegetarian Hand Roll\n" +
                    "2pc Battleship Vegetarian", "114.90", 68);
            foodDao.insert(food);

            food = new Food("Dragon Roll (8pc)","", "66.90", 69);
            foodDao.insert(food);
            food = new Food("Parrots Sushi Salad","Salmon & avocado* served with Parrots special soya sauce", "109.90", 69);
            foodDao.insert(food);
            food = new Food("Salmon Prawn Rose (3pc)","", "61.90", 69);
            foodDao.insert(food);

            // > SubMenuID: 69
            // Nice

            food = new Food("Vegetarian ","", "46.90", 70);
            foodDao.insert(food);
            food = new Food("Prawn","", "79.90", 70);
            foodDao.insert(food);
            food = new Food("Smoked Salmon","", "79.90", 70);
            foodDao.insert(food);
            food = new Food("Crab","", "49.90", 70);
            foodDao.insert(food);
            food = new Food("Salmon","", "79.90", 70);
            foodDao.insert(food);

            food = new Food("Vegetarian ","", "49.90", 71);
            foodDao.insert(food);
            food = new Food("Prawn","", "81.90", 71);
            foodDao.insert(food);
            food = new Food("Smoked Salmon","", "83.90", 71);
            foodDao.insert(food);
            food = new Food("Crab","", "50.90", 71);
            foodDao.insert(food);
            food = new Food("Salmon","", "83.90", 71);
            foodDao.insert(food);

            // What even is a fashion sandwich?

            food = new Food("Crab","", "58.90", 72);
            foodDao.insert(food);
            food = new Food("Salmon","", "84.90", 72);
            foodDao.insert(food);
            food = new Food("Tuna","", "84.90", 72);
            foodDao.insert(food);
            food = new Food("Prawn","", "83.90", 72);
            foodDao.insert(food);
            food = new Food("Smoked Salmon","", "84.90", 72);
            foodDao.insert(food);

            food = new Food("Vegetarian ","", "45.90", 73);
            foodDao.insert(food);
            food = new Food("Prawn","", "55.90", 73);
            foodDao.insert(food);
            food = new Food("Smoked Salmon","", "56.90", 73);
            foodDao.insert(food);
            food = new Food("Crab","", "47.90", 73);
            foodDao.insert(food);
            food = new Food("Salmon","", "56.90", 73);
            foodDao.insert(food);

            food = new Food("Vegetarian ","", "43.90", 74);
            foodDao.insert(food);
            food = new Food("Crab","", "53.90", 74);
            foodDao.insert(food);
            food = new Food("Salmon","", "59.90", 74);
            foodDao.insert(food);
            food = new Food("Tuna","", "59.90", 74);
            foodDao.insert(food);
            food = new Food("Caviar","", "53.90", 74);
            foodDao.insert(food);

            food = new Food("Avocado*","", "39.90", 75);
            foodDao.insert(food);
            food = new Food("Prawn","", "55.90", 75);
            foodDao.insert(food);
            food = new Food("Crab","", "39.90", 75);
            foodDao.insert(food);

            food = new Food("Wasabi Parcel (6pc)","Wasabi, Salmon, Mayo & Caviar", "88.90", 76);
            foodDao.insert(food);
            food = new Food("Rainbow Sandwiches (6pc)","A twist on a classic layer of rice & prawn, topped with salmon, avo, inari, Japanese carrot & caviar", "82.90", 76);
            foodDao.insert(food);
            food = new Food("Master Weiging Roll (8pc)","Classic California roll with salmon, topped with roasted panko crumbs & sriracha mayo, finished with spring onion", "84.90", 76);
            foodDao.insert(food);

            food = new Food("Caviar","", "49.90", 77);
            foodDao.insert(food);
            food = new Food("Salmon","", "57.90", 77);
            foodDao.insert(food);
            food = new Food("Tuna","", "57.90", 77);
            foodDao.insert(food);
            food = new Food("Prawn","", "54.90", 77);
            foodDao.insert(food);
            food = new Food("Smoked Salmon","", "57.90", 77);
            foodDao.insert(food);

            food = new Food("Prawn","", "74.90", 78);
            foodDao.insert(food);
            food = new Food("Tuna","", "79.90", 78);
            foodDao.insert(food);
            food = new Food("Salmon","", "79.90", 78);
            foodDao.insert(food);

            food = new Food("Salmon","", "76.90", 80);
            foodDao.insert(food);
            food = new Food("Prawn","", "74.90", 80);
            foodDao.insert(food);
            food = new Food("Vegetarian ","", "49.90", 80);
            foodDao.insert(food);

            food = new Food("Filter Coffee","", "16.90", 81);
            foodDao.insert(food);
            food = new Food("Cappucino","", "23.90", 81);
            foodDao.insert(food);
            food = new Food("Espresso","", "16.90", 81);
            foodDao.insert(food);
            food = new Food("5 Roses","", "17.90", 81);
            foodDao.insert(food);
            food = new Food("Rooibos","", "17.90", 81);
            foodDao.insert(food);

            food = new Food("FUZE Ice-tea","", "23.90", 82);
            foodDao.insert(food);
            food = new Food("Appletizer","", "27.90", 82);
            foodDao.insert(food);
            food = new Food("Soda Fountain","", "20.90", 82);
            foodDao.insert(food);
            food = new Food("Sir Fruit Juice","", "23.90", 82);
            foodDao.insert(food);

            food = new Food("Slo-Jo Freezo’s","", "35.90", 83);
            foodDao.insert(food);
            food = new Food("Iced Coffee","", "31.90", 83);
            foodDao.insert(food);
            food = new Food("Frullata","", "38.90", 83);
            foodDao.insert(food);

            food = new Food("Milkshakes","", "29.90", 84);
            foodDao.insert(food);
            food = new Food("Gourmet","", "39.90", 84);
            foodDao.insert(food);

            food = new Food("Strawberry Daiquiri","Sultry strawberry juice blended frozen with wild strawberry extracts and freshly squeezed lemon.", "43.90", 85);
            foodDao.insert(food);
            food = new Food("Piña Colada","Fresh pineapple blended frozen with 1883 Piña Colada and freshly-squeezed lemon.", "43.90", 85);
            foodDao.insert(food);
            food = new Food("No-Jito","Freshly muddled lemon quarters, mint leaves and 1883 Mojito Mint. Served with crushed ice and charged with Schweppes soda water.", "43.90", 85);
            foodDao.insert(food);

            food = new Food("Groovy Granadilla","Gudgu Granadilla shaken with Vodka, lemon, strawberry and charged with schweppes soda water", ".90", 86);
            foodDao.insert(food);
            food = new Food("Shirley Temple","A tastey delight made with Vodka, Gudgu pink, lemonade, Gudgu ginger, and charged with Shweppes soda water", ".90", 86);
            foodDao.insert(food);

            food = new Food("The Real McCoy","1883 Rose charged with Smirnoff Spin and topped with a frozen layer of Piña Colada. Served tall.", "59.90", 87);
            foodDao.insert(food);
            food = new Food("Copa Cubana","1883 Strawberry charged with Smirnoff Spin topped with a frozen layer of Mango Daiquiri. Served tall.", "61.90", 87);
            foodDao.insert(food);
            food = new Food("Macaw Crush","Layers of blue, yellow and red. 1883 Blue Curacao topped with vodka, shaken with mango puree  and orange and capped with a layer of frozen strawberry Margarita. Served tall", "60.90", 87);
            foodDao.insert(food);

            food = new Food("Mojo Cosmo","For the love of cranberry. Mojo Cranberry Sour shaken with orange liqueur and a dash of fresh cranberry juice, strained in a chilled martini glass", "47.90", 88);
            foodDao.insert(food);
            food = new Food("Blue Sunrise","A spike of white tequila charged with Beach Bar Blueberry Sours, topped with orange juice and a dash of grenadine", "54.90", 88);
            foodDao.insert(food);

            food = new Food("Rose & Orange Extract G&T","A combination of floral and citrus notes. Ginifer gin, 1883 Rose, Triple Sec and fresh lemon, charged with Schweppes Indian Tonic, served in a goblet", "62.90", 89);
            foodDao.insert(food);
            food = new Food("Pineapple & Vanilla G&T","Fruity and flavour with a hint of Vanilla. Ginifer gin, 1883 Pineapple and Vanilla with fresh lemon, charged with Schweppes Indian Tonic, served in a goblet", "62.90", 89);
            foodDao.insert(food);

            food = new Food("Fire & Ice","Tequila Silver and Iced Mint", "21.90", 90);
            foodDao.insert(food);

            food = new Food("Cosmopolitan","Vodka shaken with 1883 Triple Sec, fresh lemon and cranberry juice, garnished with a citrus twist.", "41.90", 91);
            foodDao.insert(food);
            food = new Food("Strawberry Lips Martini","Strawberry Lips shaken with 1883 Cherry, a splash of milk, infused with Nachtmusik", "49.90", 91);
            foodDao.insert(food);

            food = new Food("Mojito","Cuban light rum muddled with fresh lemon, 1883 Mojito Mint and torn mint leaves, churned unstrained with crushed ice and charged with Schweppes soda water", "53.90", 92);
            foodDao.insert(food);

            food = new Food("Apples & Flowers","Ginifer Gin, 1883 Green Apple and Elderflower, built in a Goblet over cubed ice with fresh mint and lemon, charged with tonic water", "62.90", 93);
            foodDao.insert(food);

            food = new Food("Bella Vino Dry White","", "28.90", 94);
            foodDao.insert(food);
            food = new Food("Bella Vino Red","", "28.90", 94);
            foodDao.insert(food);
            food = new Food("Bella Vino Sweet Red","", "28.90", 94);
            foodDao.insert(food);
            food = new Food("Bella Vino Natural Sweet Rosè","", "28.90", 94);
            foodDao.insert(food);

            food = new Food("Bella Vino Sublime White"," Gooseberries, pears and litchi aromas on the nose beautifully balanced, smooth with", ".90", 95);
            foodDao.insert(food);
            food = new Food("Leopard’s Leap Chardonnay"," Well-rounded, the palate reveals favours of ripe grapefruit and lemon zest, supported by undertones of hazelnut that provide a unique aftertaste", "132.90", 95);
            foodDao.insert(food);

            food = new Food("Meerlust Rubicon","Iconic Cape wine. Very deep, full-bodied, structured by beautifully sleek, ripe tannins which restrain the vibrant dark fruit typical of this vintage. The wine lingers on the linear tannins", "599.90", 96);
            foodDao.insert(food);

            food = new Food("Salmon Trout Breakfast","rye toast topped with cream cheese, fresh avo (seasonal), salmon trout, tomato, eggs & finished with spring onion", "124.00", 107);
            foodDao.insert(food);
            food = new Food("Cappos Bacon Benedict","bacon, poached eggs & hollandaise sauce on fried polenta fingers", "67.00", 107);
            foodDao.insert(food);
            food = new Food("French Toast Breakfast","french toast with back bacon, caramelised nuts & seeds, topped with golden syrup", "69.00", 107);
            foodDao.insert(food);

            food = new Food("South African Breakfast","eggs with back bacon, boerewors, grilled tomato & chips", "60.00", 108);
            foodDao.insert(food);
            food = new Food("Everyday Breakfast","eggs with back bacon, grilled tomato", "40.00", 108);
            foodDao.insert(food);
            food = new Food("Cappos Breakfast","eggs with back bacon, breakfast steak, creamy mushrooms, grilled tomato, chips and boerewors or cheese griller", "89.00", 108);
            foodDao.insert(food);
            food = new Food("Rise & Shine Breakfast","eggs with a beef patty, grilled tomato and hash browns ", "55.00", 108);
            foodDao.insert(food);

            food = new Food("toast topped with","creamy mushroom, fresh basil & emmenthaler ", "70.00", 109);
            foodDao.insert(food);
            food = new Food("toast topped with","bolognaise, mozzarella & red onion", "70.00", 109);
            foodDao.insert(food);
            food = new Food("toast topped with","cajun chicken, cheddar, spring onion & tomato", "65.00", 109);
            foodDao.insert(food);

            food = new Food("","butter & jam ", "27.00", 110);
            foodDao.insert(food);
            food = new Food("","add cheddar ", "17.00", 110);
            foodDao.insert(food);

            food = new Food("egg","", "6.00", 111);
            foodDao.insert(food);
            food = new Food("toast","", "5.00", 111);
            foodDao.insert(food);
            food = new Food("mushrooms","", "13.00", 111);
            foodDao.insert(food);
            food = new Food("hash browns","", "13.00", 111);
            foodDao.insert(food);

            food = new Food("3 egg omelette with cheese ","2 fillings and your choice of toast", "79.00", 112);
            foodDao.insert(food);

            food = new Food("Ham & Cheddar","", "49.00", 113);
            foodDao.insert(food);
            food = new Food("Chicken Mayo","", "59.00", 113);
            foodDao.insert(food);
            food = new Food("Bacon, Egg & Cheddar","", "63.00", 113);
            foodDao.insert(food);

            food = new Food("Salmon Trout & Cucumber","lettuce stacked with salmon trout , tomato, cucumber ribbons, topped with spring onion & a creamy balsamic dressing", "135.00", 114);
            foodDao.insert(food);
            food = new Food("Cajun Chicken","rocket topped with cajun chicken, feta, roasted cherry tomato, fresh avo (seasonal) & a tangy tomato dressing", "110.00", 114);
            foodDao.insert(food);
            food = new Food("Chicken, Bacon & Avo","oven-roasted chicken breast in a tangy mustard mayo sauce with bacon, fresh avo (seasonal) & rocket", "105.00", 114);
            foodDao.insert(food);

            food = new Food("Haloumi","grilled or deep-fried haloumi, fresh avo (seasonal), spring onion, lettuce, cream cheese & sweet chilli sauce", "86.00", 115);
            foodDao.insert(food);
            food = new Food("Chicken, Bacon & Avo","lettuce, tomato, roasted chicken, bacon, fresh avo (seasonal) & a honey-mustard dressing", "95.00", 115);
            foodDao.insert(food);
            food = new Food("Salmon Trout","salmon trout with rocket, cucumber, carrots, red onions, & a peppadewTM-mustard dressing", "115.00", 115);
            foodDao.insert(food);
            food = new Food("Beef & Rocket","pan-fried beef strips, rocket, grilled onions, tomato, cheddar, green pepper & a honey mustard dressing", "90.00", 115);
            foodDao.insert(food);

            food = new Food("Quiche","home-baked, ask your waitron about today’s option", "57.00", 116);
            foodDao.insert(food);
            food = new Food("Flame Grilled Chicken Breast","chicken breast grilled & prepared in a variety of home-made sauces", "75.00", 116);
            foodDao.insert(food);
            food = new Food("Crumbed Steak","crumbed & slightly flattened fried steak, topped with emmenthaler & served with a sauce of your choice", "90.00", 116);
            foodDao.insert(food);
            food = new Food("Crumbed Steak","fresh avo (seasonal) & cheddar on a crumbed & slightly flattened fried steak", "95.00", 116);
            foodDao.insert(food);
            food = new Food("Gratinati","crumbed chicken breast topped with creamed spinach, feta & emmentaler & baked to a golden brown", "79.00", 116);
            foodDao.insert(food);

            food = new Food("Big Cheese","double cheddar, grilled onions, Cappos burger sauce, pickles, lettuce & tomato", "86.00", 117);
            foodDao.insert(food);
            food = new Food("Saucy Burger","grilled onions, pickles, lettuce, tomato & a sauce of your choice: pepper, mushroom, cheese, creamy garlic", "86.00", 117);
            foodDao.insert(food);
            food = new Food("Swiss","double emmentaler, bacon, red onion marmalade, smoky mayo, pickles, fresh rocket & tomato", "99.00", 117);
            foodDao.insert(food);
            food = new Food("Cappos Burger","double up open burger,cheddar, bacon, egg, creamy avo, Cappos burger sauce, pickles, lettuce & tomato", "139.00", 117);
            foodDao.insert(food);

            food = new Food("Crusted Chicken Strips","chicken strips coated with parmesan & crumbs, deep-fried & served with sweet chilli or basil-mayo sauce", "59.00", 118);
            foodDao.insert(food);
            food = new Food("Cappos Chicken Livers","chicken livers, onions & green peppers cooked in our secret spicy sauce (mild or hot)", "65.00", 118);
            foodDao.insert(food);
            food = new Food("Crispy Deep-Fried Mushrooms","button mushrooms crumbed & deep-fried until crisp, served with home-made tartar or basil-mayo sauce", "69.00", 118);
            foodDao.insert(food);

            food = new Food("3 cheese ","", "80.00", 119);
            foodDao.insert(food);
            food = new Food("garlic & herb ","", "40.00", 119);
            foodDao.insert(food);
            food = new Food("olive & feta ","", "49.00", 119);
            foodDao.insert(food);
            food = new Food("tomato base ","", "40.00", 119);
            foodDao.insert(food);

            food = new Food("Grilled Chicken & Parmesan Salad","grilled chicken breast, onion, celery, peppers, fresh basil, parmesan shavings & a peppadew-mustard dressing", "85.00", 120);
            foodDao.insert(food);
            food = new Food("Cajun Chicken Salad","baby spinach, celery, radish, kalamata olives, cajun chicken breast, crispy bacon & a honey mustard dressing", "95.00", 120);
            foodDao.insert(food);
            food = new Food("Salmon Trout Salad","salmon trout, rocket, fennel, cucumber, strawberries, fresh avo (seasonal) & a cream cheese dressing", "120.00", 120);
            foodDao.insert(food);

            food = new Food("Calamari","calamari & squid heads grilled or deep-fried until crisp & served with home-made tartar sauce", "137.00", 121);
            foodDao.insert(food);
            food = new Food("Chicken Al Forno","2 chicken breasts baked with creamy avo, feta & mozzarella", "125.00", 121);
            foodDao.insert(food);
            food = new Food("Chicken Schnitzel","crumbed chicken breasts with cheese or mushroom sauce", "115.00", 121);
            foodDao.insert(food);
            food = new Food("Oxtail","oxtail braised in red wine & napoletana sauce, servedwith roasted tomato gnocchi", "180.00", 122);
            foodDao.insert(food);
            food = new Food("Chicken PeppadewTM Stir-fry","chicken strips, peppadewTM stir-fried vegetables &noodles with honey, ginger & soya sauce", "112.00", 122);
            foodDao.insert(food);
            food = new Food("Prawns","6 king prawns prepared with lemon & herb or mozambican style peri-peri,served with rice & grilled vegetables", "175.00", 122);
            foodDao.insert(food);

            food = new Food("Cappos Grill","chicken wings, boerewors, lamb chop & short ribs", "180.00", 123);
            foodDao.insert(food);
            // 2000 lines of code!
            food = new Food("Sirloin & Prawns","250g flame grilled sirloin & 3 king prawns served withmozambican peri-peri sauce", "180.00", 123);
            foodDao.insert(food);
            food = new Food("Calamari & Hake","calamari, squid heads & hake, grilled or deep-fried, served withhome-made tartar or tangy tomato sauce", "165.00", 123);
            foodDao.insert(food);
            food = new Food("Ribs & Wings","4 chicken wings grilled with bbq, peri-peri or lemon & herb sauce & 400g pork rib", "179.00", 123);
            foodDao.insert(food);
            food = new Food("Chicken & Short Rib","½ chicken grilled with bbq, peri-peri or lemon & herb sauce & short ribs", "168.00", 123);
            foodDao.insert(food);

            food = new Food("","pepper/ mushroom/ cheese/ creamy garlic", "25.00", 124);
            foodDao.insert(food);

            food = new Food("","chips/ rice/ pan-fried potatoes/ polenta/ creamed spinach/grilled vegetables/ side salad", "25.00", 125);
            foodDao.insert(food);

            food = new Food("Fillet 200g","", "155.00", 126);
            foodDao.insert(food);
            food = new Food("Rump 300g","", "135.00", 126);
            foodDao.insert(food);
            food = new Food("Lamb Chops 300g","", "160.00", 126);
            foodDao.insert(food);
            food = new Food("Ribs 400g","grilled with Cappos bbq rib basting", "145.00", 126);
            foodDao.insert(food);

            food = new Food("T-Bone","500g t-bone roasted in the wood fire oven with garlic & black pepper", "189.00", 127);
            foodDao.insert(food);
            food = new Food("Lamb Chops","300g lamb chops roasted in the wood fire oven with rosemary, balsamic vinegar & black pepper", "175.00", 127);
            foodDao.insert(food);
            food = new Food("Roasted Chicken","½ chicken oven roasted in your choice of sauce: mozambican peri-peri/ creamy-mild curry/ lemon & herb", "120.00", 127);
            foodDao.insert(food);

            food = new Food("","pepper/ mushroom/ cheese/ creamy garlic", "25.00", 128);
            foodDao.insert(food);

            food = new Food("","chips/ rice/ pan-fried potatoes/ polenta/ creamed spinach/grilled vegetables/ side salad", "25.00", 129);
            foodDao.insert(food);

            food = new Food("Pepperoni Supremo","pepperoni, chillies, garlic, feta & mozzarella", "110.00", 130);
            foodDao.insert(food);
            food = new Food("Margherita","a classic tomato based pizza with mozzarella & oregano", "76.00", 130);
            foodDao.insert(food);
            food = new Food("Vegetarian","mushrooms, kalamata olives, artichokes, onion, green peppers,garlic, mozzarella & oregano", "100.00", 130);
            foodDao.insert(food);
            food = new Food("Mexicana","our authentic bolognaise sauce, peppers, onion, jalapeños,fresh avo (seasonal) & mozzarella", "109.00", 130);
            foodDao.insert(food);
            food = new Food("Cajun Chicken & Sundried Tomatoes","cajun chicken breasts, sun-dried tomato, feta, fresh basil & mozzarella", "119.00", 130);
            foodDao.insert(food);
            food = new Food("Rucola","salami, mozzarella, sun-dried tomato, parmesan shavings & fresh rocket", "115.00", 130);
            foodDao.insert(food);

            food = new Food("","mushroom", "13.00", 131);
            foodDao.insert(food);
            food = new Food("","kalamata olives/ onion/ pineapple/ green pepper/fresh rocket/ fresh basil/ artichokes/ jalapeño/sun-dried tomato", "10.00", 131);
            foodDao.insert(food);
            food = new Food("","cheddar/ mozzarella/ feta/ haloumi/ salami / pepperoni/ham/ chicken", "17.00", 131);
            foodDao.insert(food);
            food = new Food("","bacon/ fresh avo (seasonal)", "23.00", 131);
            foodDao.insert(food);

            food = new Food("Spaghetti Pollo Piccante","grilled chicken breast, chilli, garlic butter, parsley, sun-dried tomato,cream sauce served with long thin pasta & parmesan shavings", "105.00", 132);
            foodDao.insert(food);
            food = new Food("Basil-Pesto Chicken","chicken breasts, bacon & artichoke leaves in a creamybasil pesto sauce served with penne", "95.00", 132);
            foodDao.insert(food);
            food = new Food("Beef Lasagne","creamy layers of pasta sheets, classic bolognaise sauce & cheese", "90.00", 132);
            foodDao.insert(food);
            food = new Food("Gourmet Mac & Cheese","penne cooked in a creamy blend of parmesan, cheddar, mozzarella & bacon,topped with bread crumbs, baked to a golden brown &finished with spring onions", "84.00", 132);
            foodDao.insert(food);

            food = new Food("alfredo ","ham, mushrooms, parmesan & cream", "105.00", 133);
            foodDao.insert(food);
            food = new Food("bolognaise ","classic bolognaise sauce with ground mince &tomato sauce", "103.00", 133);
            foodDao.insert(food);
            food = new Food("al polllo ","chicken with mushrooms, parmesan,rosemary & cream", "105.00", 133);
            foodDao.insert(food);
            food = new Food("chicken masala ","chicken & baby marrow in acreamy-mild curry sauce", "95.00", 133);
            foodDao.insert(food);

            food = new Food("Apple Tart","an old favourite served with cream or ice cream, topped with nuts & caramel sauce", "48.00", 134);
            foodDao.insert(food);
            food = new Food("Peppermint Crisp Dessert","creamy caramel, biscuit crumbs & crushed peppermint chocolate", "54.00", 134);
            foodDao.insert(food);
            food = new Food("Ice Cream & Choc Delight","vanilla ice cream, topped with crushed oreo biscuit, chocolate syrup topped with cream, a marshmallow, flake & a cherry", "47.00", 134);
            foodDao.insert(food);
            food = new Food("Irish Coffee","whiskey, frangelico, kahlua", "40.00", 134);
            foodDao.insert(food);

            food = new Food("","ask your waitron about today's selection:", "40.00", 135);
            foodDao.insert(food);

            food = new Food("Ice Tea","", "27.00", 136);
            foodDao.insert(food);
            food = new Food("Mineral Water","500ml still or sparkling", "20.00", 136);
            foodDao.insert(food);
            food = new Food("Freezos","coffee, coffee chocolate, white chocolate", "38.00", 136);
            foodDao.insert(food);
            food = new Food("Fruit Juice","fruit cocktail, orange, cranberry, mango, strawberry", "26.00", 136);
            foodDao.insert(food);
            food = new Food("Frullata","vanilla ice cream blended with fruit juice, choose from the above fruit flavours", "42.00", 136);
            foodDao.insert(food);
            food = new Food("Cappufrappé ","frozen coffee with a hint of condensed milk topped with whipped cream, available in original, hazelnut, vanilla", "40.00", 136);
            foodDao.insert(food);
            food = new Food("Sodas","", "24.00", 136);
            foodDao.insert(food);
            food = new Food("Tizers","appletizer, red and white grapetizer", "28.00", 136);
            foodDao.insert(food);
            food = new Food("Cordials with lemonade or soda","passion fruit, kola tonic, lime cordial, elderflower", "28.00", 136);
            foodDao.insert(food);
            food = new Food("Rock Shandy","", "34.00", 136);
            foodDao.insert(food);
            food = new Food("Milkshakes","banana, vanilla, lime, strawberry, bubblegum, chocolate, coffee, milo, oreo, salted caramel, peanut butter, rum & raisin", "35.00", 136);
            foodDao.insert(food);
            food = new Food("Smoothies","choose from fruit juice flavours above", "38.00", 136);
            foodDao.insert(food);

            food = new Food("coffee","", "21.00", 137);
            foodDao.insert(food);
            food = new Food("decaffeinated","", "21.00", 137);
            foodDao.insert(food);

            food = new Food("single","", "18.00", 138);
            foodDao.insert(food);
            food = new Food("double","", "23.00", 138);
            foodDao.insert(food);

            food = new Food("cappuccino","", "26.00", 139);
            foodDao.insert(food);
            food = new Food("double shot cappuccino","", "30.00", 139);
            foodDao.insert(food);
            food = new Food("grande cappuccino","", "33.00", 139);
            foodDao.insert(food);

            food = new Food("hazelnut","", "34.00", 140);
            foodDao.insert(food);
            food = new Food("vanilla","", "34.00", 140);
            foodDao.insert(food);

            food = new Food("café latte","", "30.00", 141);
            foodDao.insert(food);
            food = new Food("milo","", "30.00", 141);
            foodDao.insert(food);
            food = new Food("horlicks","", "34.00", 141);
            foodDao.insert(food);

            food = new Food("five roses","", ".00", 142);
            foodDao.insert(food);
            food = new Food("rooibos","", ".00", 142);
            foodDao.insert(food);
            food = new Food("lemon tea with a lemon wedge & honey","", ".00", 142);
            foodDao.insert(food);
            food = new Food("chamomile","", ".00", 142);
            foodDao.insert(food);
            food = new Food("earl grey","", ".00", 142);
            foodDao.insert(food);

            food = new Food("red® cappuccino","", "27.00", 143);
            foodDao.insert(food);
            food = new Food("red® latte","", "30.00", 143);
            foodDao.insert(food);

            food = new Food("Full Chicken + 4 Rolls"," Full flame-grilled chicken with 4 Nando’s rolls ", "164.00", 144);
            foodDao.insert(food);
            food = new Food("Full Chicken + 4 Sharing Sides"," Full flame-grilled chicken with 4 sharing portion sides of your choice ", "237.00", 144);
            foodDao.insert(food);
            food = new Food("Full Chicken + 3 Sharing Sides"," Full flame-grilled chicken with 3 sharing portion sides of your choice ", "219.00", 144);
            foodDao.insert(food);
            food = new Food("Full Chicken + 2 Sharing Sides"," Full flame-grilled chicken with 2 sharing portion sides of your choice ", "199.00", 144);
            foodDao.insert(food);
            food = new Food("Full Chicken (on its own)","", "139.00", 144);
            foodDao.insert(food);
            food = new Food("Full Livers and a Nando's Roll"," Full portion of livers with a Nando’s roll ", "49.00", 144);
            foodDao.insert(food);

            food = new Food("1/2 Chicken (on its own)","", "79.00", 145);
            foodDao.insert(food);
            food = new Food("1/2 Chicken + any 2 Single Sides"," 1/2 Chicken with any 2 single portion sides of your choice ", "107.00", 145);
            foodDao.insert(food);
            food = new Food("1/2 Chicken + any Single Side"," 1/2 Chicken with a single portion side of your choice ", "84.00", 145);
            foodDao.insert(food);

            food = new Food("1/4 Chicken Meal"," 1/4 Chicken, single side and a soft drink can ", "67.00", 146);
            foodDao.insert(food);
            food = new Food("1/4 Chicken + Saucy Rice"," 1/4 Chicken with a side of saucy rice (no swop outs) ", "57.00", 146);
            foodDao.insert(food);
            food = new Food("1/4 Chicken (on its own)","", "47.00", 146);
            foodDao.insert(food);
            food = new Food("1/4 Chicken + any Single Side"," 1/4 Chicken with a single portion side of your choice ", "57.00", 146);
            foodDao.insert(food);
            food = new Food("1/4 Chicken, Single Chips & a Nando's Roll"," The famous Indlanathi (no swop outs) ", "59.00", 146);
            foodDao.insert(food);

            food = new Food("Veggie Burger Meal"," Veggie burger, single side and a soft drink can ", "67.00", 147);
            foodDao.insert(food);
            food = new Food("Chicken Burger Meal"," Chicken burger, single side and a soft drink can ", "67.00", 147);
            foodDao.insert(food);
            food = new Food("Double Chicken Burger (on its own)","", "69.00", 147);
            foodDao.insert(food);
            food = new Food("Double Veggie Burger (on its own)","", "69.00", 147);
            foodDao.insert(food);
            food = new Food("The Grande Meal"," A saucy chicken burger, 4 succulent winglets & a single side ", "84.00", 147);
            foodDao.insert(food);

            food = new Food("Veggie, Avo & Pickled Red Onion Pita (on its own)"," Veggie patty in a pita with avo and pickled red onion ", "59.00", 148);
            foodDao.insert(food);
            food = new Food("Chicken, Avo & Pickled Red Onion Pita (on its own)"," Chicken pita filled with avo and pickled red onion ", "59.00", 148);
            foodDao.insert(food);
            food = new Food("Veggie, Avo & Pickled Red Onion Pita + any Single Side"," Veggie patty in a pita with avo and pickled red onion, and a single portion side of your choice ", "72.00", 148);
            foodDao.insert(food);
            food = new Food("Chicken, Avo & Pickled Red Onion Pita + any Single Side"," Chicken pita filled with avo and pickled red onion, with a single portion side of your choice ", "72.00", 148);
            foodDao.insert(food);

            food = new Food("Veggie, Avo & Pickled Red Onion Wrap (on its own)"," Veggie wrap with avo and pickled red onion ", "54.50", 149);
            foodDao.insert(food);
            food = new Food("Chicken, Avo & Pickled Red Onion Wrap (on its own)"," Chicken wrap with avo and pickled red onion ", "59.00", 149);
            foodDao.insert(food);
            food = new Food("Veggie, Avo & Pickled Red Onion Wrap + any Single Side"," Veggie wrap with avo and pickled red onion, with a single portion side of your choice ", "67.50", 149);
            foodDao.insert(food);
            food = new Food("Chicken, Avo & Pickled Red Onion Wrap + any Single Side"," Chicken wrap with avo and pickled red onion, with a single portion side of your choice ", "72.00", 149);
            foodDao.insert(food);

            food = new Food("Livers & Casa Pap"," Starter portion of livers with Casa Pap (no swop outs) ", "37.00", 150);
            foodDao.insert(food);
            food = new Food("Hotpot & Spicy Rice"," Pulled chicken, Nando’s spicy rice, veggies & tomato relish ", "37.00", 150);
            foodDao.insert(food);
            food = new Food("Chicken Strips & Spicy Rice"," Grilled chicken strips on a bed of Nando’s spicy rice ", "47.00", 150);
            foodDao.insert(food);
            food = new Food("Hotpot & Casa Pap"," Pulled chicken, Casa Pap, veggies & tomato relish ", "37.00", 150);
            foodDao.insert(food);
            food = new Food("Full Livers and a Nando's Roll"," Full portion of livers with a Nando’s roll ", "49.00", 150);
            foodDao.insert(food);
            food = new Food("Starter Livers and a Nando's Roll"," Starter portion of livers with a Nando’s roll ", "39.00", 150);
            foodDao.insert(food);
            food = new Food("Chicken Strips, Veg & Spicy Rice"," Grilled chicken strips and mixed vegetables on a bed of Nando's spicy rice ", "49.00", 150);
            foodDao.insert(food);

            food = new Food("Sol Salada"," Lettuce, tomato wedges, cucumber, red and green peppers, onion rings, with grilled halloumi and pineapple ", "57.00", 151);
            foodDao.insert(food);
            food = new Food("Grilled Chicken Salad"," Nando's salad with flame-grilled chicken strips ", "57.00", 151);
            foodDao.insert(food);
            food = new Food("Coleslaw (Single)","", "27.00", 151);
            foodDao.insert(food);
            food = new Food("Nando's Salad (Single)","", "27.00", 151);
            foodDao.insert(food);
            food = new Food("Nando's Salad (Sharing)","", "37.00", 151);
            foodDao.insert(food);
            food = new Food("Coleslaw (Sharing)","", "37.00", 151);
            foodDao.insert(food);
            food = new Food("Feta, Cranberry & Pumpkin Seed Salad"," Tomato wedges, cucumber, red and green peppers, onion rings, feta. pumpkin seeds and dried cranberries on fancy lettuce ", "57.00", 151);
            foodDao.insert(food);

            food = new Food("OldSkool", "Smash, Tomato, Red Onion & RocoMayo", "59.00", 152);
            foodDao.insert(food);
            food = new Food("ClassicCheese", "Smash, Aged Cheddar, Sweet Pickle, Tomato, Red Onion & RocoMayo", "72.00", 152);
            foodDao.insert(food);
            food = new Food("ROCKSTAR", "Smash, Emmenthal Cheese, Caramelised Red Onions & RocoMayo", "78.00", 152);
            foodDao.insert(food);
            food = new Food("CHEESE+BACON", "Smash, Bacon, Aged Cheddar, Sweet Pickle, Tomato, Red Onion & RocoMayo", "78.00", 152);
            foodDao.insert(food);
            food = new Food("MUSHROOMSWIZZ", "Smash, Emmenthal Cheese, Sauteed Mushrooms & RocoMayo", "83.00", 152);
            foodDao.insert(food);
            food = new Food("ChiliCHEESEBOMB", "Smash, Bacon, Cheese Bomb, Aged Cheddar, Mozzarella, Fresh Chilli, Chilli Mayo", "93.00", 152);
            foodDao.insert(food);

            food = new Food("SMASHBURGER","150g Smash, Tomato, Red Onion, RocoMayo", "47.00", 153);
            foodDao.insert(food);
            food = new Food("Bacon","", "13.00", 153);
            foodDao.insert(food);
            food = new Food("Cheddar","", "10.00", 153);
            foodDao.insert(food);
            food = new Food("Emmenthal","", "11.00", 153);
            foodDao.insert(food);
            food = new Food("Guacamole","", "8.00", 153);
            foodDao.insert(food);

            food = new Food("PORK 250g","", "88.00", 154);
            foodDao.insert(food);
            food = new Food("PORK 500g","", "170.00", 154);
            foodDao.insert(food);
            food = new Food("PORK 750g","", "250.00", 154);
            foodDao.insert(food);
            food = new Food("SWEET FIRE RIBS 250g","", "88.00", 154);
            foodDao.insert(food);
            food = new Food("BEEF 500g","", "145.00", 154);
            foodDao.insert(food);
            food = new Food("BEEF 1kg","", "290.00", 154);
            foodDao.insert(food);

            food = new Food("FOUR","", "32.00", 155);
            foodDao.insert(food);
            food = new Food("EIGHT","", "62.00", 155);
            foodDao.insert(food);
            food = new Food("SIXTEEN","", "120.00", 155);
            foodDao.insert(food);

            food = new Food("Village Greek","", "37.00", 156);
            foodDao.insert(food);
            food = new Food("Spicy Coleslaw with bacon","", "37.00", 156);
            foodDao.insert(food);

            food = new Food("Chilli Cheese","", "37.00", 157);
            foodDao.insert(food);
            food = new Food("Feta","", "37.00", 157);
            foodDao.insert(food);
            food = new Food("Jalapeno","", "37.00", 157);
            foodDao.insert(food);

            food = new Food("Classico","", "58.00", 158);
            foodDao.insert(food);
            food = new Food("Chilli Con Carne","", "75.00", 158);
            foodDao.insert(food);
            food = new Food("Chicken Fajitas","", "80.00", 158);
            foodDao.insert(food);

            food = new Food("Small","", "19.00", 159);
            foodDao.insert(food);
            food = new Food("Medium","", "29.00", 159);
            foodDao.insert(food);
            food = new Food("Large","", "40.00", 159);
            foodDao.insert(food);

            food = new Food("Cheesy","", "29.00", 160);
            foodDao.insert(food);
            food = new Food("Chilli Cheese","", "29.00", 160);
            foodDao.insert(food);
            food = new Food("Chilli Con Carne","", "34.00", 160);
            foodDao.insert(food);
            food = new Food("Bacon and Cheese","", "42.00", 160);
            foodDao.insert(food);

            Log.i("SplitBill/Database","The Food table (heh) has successfully been populated.");


            Log.i("SplitBill/Database","The database is now good to go. :)");

            return null;
        }

    }

}
