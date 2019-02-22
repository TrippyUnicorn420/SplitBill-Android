//package com.vita.splitbill;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.vita.splitbill.deprecated.MenuActivity;
//import com.vita.splitbill.entities.Restaurant;
//
//import java.util.List;
//
//public class RestaurantRecyclerViewAdapterOld extends RecyclerView.Adapter<RestaurantRecyclerViewAdapterOld.theViewHolder> {
//
//    private Context mContext;
//    private List<Restaurant> mData;
//
//    public RestaurantRecyclerViewAdapterOld(Context mContext, List<Restaurant> mData) {
//        this.mContext = mContext;
//        this.mData = mData;
//    }
//
//    @Override
//    public theViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.cardview_restaurant,parent,false);
//        return new theViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(theViewHolder holder, final int position){
//        holder.restaurantName.setText(mData.get(position).getName());
//       // holder.restaurantImage.setImageResource(mData.get(position).getPicture());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(mContext, MenuActivity.class);
//
//                intent.putExtra("Name", mData.get(position).getName());
//            //    intent.putExtra("Picture", mData.get(position).getPicture());
//                mContext.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public static class theViewHolder extends RecyclerView.ViewHolder {
//
//        TextView restaurantName;
//        ImageView restaurantImage;
//        CardView cardView;
//
//        public theViewHolder(View itemView) {
//            super(itemView);
//
//            restaurantName = (TextView) itemView.findViewById(R.id.restaurantName);
//            restaurantImage = (ImageView) itemView.findViewById(R.id.restaurantImage);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
//        }
//
//    }
//}
