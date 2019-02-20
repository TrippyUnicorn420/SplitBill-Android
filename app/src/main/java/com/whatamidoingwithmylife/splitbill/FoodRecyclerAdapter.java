package com.whatamidoingwithmylife.splitbill;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.whatamidoingwithmylife.splitbill.core.TI84PLUS;
import com.whatamidoingwithmylife.splitbill.entities.Food;

import java.util.List;

// This is the real visual meat and potatoes of SplitBill.
// This adapter is responsible for receiving a List of
// Food objects, taking their info and displaying them on
// cards (like I said I would), and passes the Food object
// to the TI-84 PLUS when a card gets tapped.

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder> {

    // This inner class defines the parts of the View that
    // the RecyclerView will need to recycle.
    class FoodViewHolder extends RecyclerView.ViewHolder {
        private final CardView FoodView;
        private final TextView FoodName;
        private final TextView FoodDesc;
        private final TextView FoodPrice;

        private FoodViewHolder(View itemView) {
            super(itemView);

            FoodName = (TextView) itemView.findViewById(R.id.foodName);
            FoodDesc = (TextView) itemView.findViewById(R.id.foodDescription);
            FoodView = (CardView) itemView.findViewById(R.id.foodcardview_id);
            FoodPrice = (TextView) itemView.findViewById(R.id.foodPrice);

        }
    }

    private final LayoutInflater mInflater;
    private List<Food> mFood;
    private Context mContext;

    // This constructor gets a LayoutInflator from the Context.
    FoodRecyclerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    // This method receives the food from the Fragment.
    void heresTheFood(List<Food> food) {
        mFood = food;
        notifyDataSetChanged();
    }

    // This method tells the RecyclerView how many CardViews
    // to create.
    @Override
    public int getItemCount() {
        if (mFood != null)
            return mFood.size();
        else return 0;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cardview_food, parent, false);
        return new FoodViewHolder(itemView);
    }

    // This method runs when the ViewHolder is bound. It puts
    // the values from the database onto the CardView, and
    // sets actions for when the cards are tapped.
    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        if (mFood != null) {

            final Food current = mFood.get(position);
            holder.FoodName.setText(current.getFoodTitle());
            holder.FoodDesc.setText(current.getFoodInfo());
            holder.FoodPrice.setText(current.getPrice());

            // This adds the food item to the TI-84.

            holder.FoodView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    TI84PLUS.addToBill(current);
                    Toast.makeText(mContext, "Added " + current.getFoodTitle() + " to the bill.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
