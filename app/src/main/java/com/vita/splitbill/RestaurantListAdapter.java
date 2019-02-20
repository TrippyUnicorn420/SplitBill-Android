package com.vita.splitbill;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vita.splitbill.entities.Restaurant;

import java.util.List;

// This is the adapter for the list of restaurants
// displayed in the SBSplash activity. It's pretty
// intense stuff, but essentially what it does is
// to receive the list of restaurants from the SBSplash
// activity and displays the data in CardViews.

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    // This subclass defines all the parts of the CardView, namely
    // the name of the restaurant, its little thumbnail and the CardView itself.

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private final CardView restaurantView;
        private final TextView restaurantName;
        private final ImageView restaurantImage;

        private RestaurantViewHolder(View itemView) {
            super(itemView);

            restaurantName = (TextView) itemView.findViewById(R.id.restaurantName);
            restaurantImage = (ImageView) itemView.findViewById(R.id.restaurantImage);
            restaurantView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }

    private final LayoutInflater mInflater;
    private List<Restaurant> mRestaurants;
    private Context mContext;

    // This constructor receives the context from SBSplash
    // to allow inflating the layout, and to switch activities
    // later on.

    RestaurantListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cardview_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        if (mRestaurants != null) {

            // This piece of code is quite special. It sets the name and picture
            // of the restaurant card.

            final Restaurant current = mRestaurants.get(position);
            holder.restaurantName.setText(current.getName());
            holder.restaurantImage.setImageResource(current.getPictureID());

            // This next piece is even more special. It handles launching the MenuActivity
            // using the Context we got from the Constructor earlier on.

            holder.restaurantView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(mContext, NewMenuActivity.class);

                    // Remember back in Restaurant.java when I said accessors and
                    // mutators are never used (or something to that effect)? Well,
                    // I lied. Here, they are used to pass the restaurant name, UID
                    // and picture resource through to the Menu activity.

                    intent.putExtra("ResID", current.getResID());
                    intent.putExtra("Name", current.getName());
                    intent.putExtra("Picture", current.getPictureID());
                    mContext.startActivity(intent);
                }
            });
        }
        else {

            // Android Studio does not like how I've hardcoded
            // a string into this little backup screen for when
            // the CardViews don't appear from the database being
            // empty. It wants me to use a string resource instead
            // to make translation easier later on. I really don't
            // care though, because the database will never be empty,
            // and this will never be seen as such.

            holder.restaurantName.setText("There doesn't seem to be anything here");
        }
    }

    // This updates the restaurant list if it ever changes.

    void setRestaurants(List<Restaurant> restaurants){
        mRestaurants = restaurants;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRestaurants != null)
            return mRestaurants.size();
        else return 0;
    }


}
