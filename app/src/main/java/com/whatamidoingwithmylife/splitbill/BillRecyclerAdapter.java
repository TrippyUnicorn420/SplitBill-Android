package com.whatamidoingwithmylife.splitbill;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// This is the adapter for the Recycler View in the BillActivity.
// It takes the ArrayList<String> of the bill from the activity
// and displays them all very neatly.

public class BillRecyclerAdapter extends RecyclerView.Adapter<BillRecyclerAdapter.BillViewHolder> {

    // This defines the aspects of the View that will be held
    // in the RecyclerView.
    class BillViewHolder extends RecyclerView.ViewHolder {
        private final TextView billItem;

        private BillViewHolder(View itemView) {
            super(itemView);

            billItem = itemView.findViewById(R.id.billItem);
        }
    }

    private final LayoutInflater mInflater;
    private ArrayList<String> mBill;

    // This constructor gets a LayoutInflater from the activity Context.
    BillRecyclerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    // This method receives the bill from the bill activity, which gets
    // it from the TI-84.
    void setBill(ArrayList<String> bill) {
        mBill = bill;
        notifyDataSetChanged();
    }

    // This method tells the RecyclerView how many TextViews
    // to create.
    @Override
    public int getItemCount() {
        if (mBill != null)
            return mBill.size();
        else return 0;
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.billitem, parent, false);
        return new BillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        if (mBill != null) {

            final String current = mBill.get(position);
            holder.billItem.setText(current);

        }
    }

}
