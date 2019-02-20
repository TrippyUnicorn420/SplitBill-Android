package com.vita.splitbill.dialogs;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.vita.splitbill.R;

public class BillEmptyDialog extends DialogFragment {

    public BillEmptyDialog(){

    }

    public static BillEmptyDialog newInstance(String title) {
        BillEmptyDialog frag = new BillEmptyDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.mtbill)
                .setTitle(R.string.firstlaunchtitle)
                .setCancelable(false)
                .setPositiveButton(R.string.whoops, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });

        return builder.create();
    }
}
