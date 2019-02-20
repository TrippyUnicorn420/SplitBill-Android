package com.vita.splitbill.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.vita.splitbill.BillActivity;

// YallDoneDialog.class uses this class to
// launch the bill activity. That's it.

public class LaunchBill {

    private Context mContext;
    private Activity mActivity;

    public LaunchBill(Context mContext, Activity mActivity){
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public void LaunchTheDuckingBill(String resName){
        Intent intent = new Intent(mContext, BillActivity.class);
        intent.putExtra("resName",resName);
        mContext.startActivity(intent);
    }

}
