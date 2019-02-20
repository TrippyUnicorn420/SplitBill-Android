package com.vita.splitbill.dialogs;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.vita.splitbill.R;

public class YallDoneDialog extends DialogFragment {

    public YallDoneDialog() {

    }

    public static YallDoneDialog newInstance(String title) {
        YallDoneDialog frag = new YallDoneDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.done)
                .setTitle(R.string.done_title)
                .setPositiveButton(R.string.y, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LaunchBill l = new LaunchBill(getActivity(), getActivity());
                        l.LaunchTheDuckingBill(getArguments().getString("title"));
                    }
                })
                .setNegativeButton(R.string.n, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();
    }


}
