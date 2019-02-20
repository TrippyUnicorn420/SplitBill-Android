package com.vita.splitbill.dialogs;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.vita.splitbill.core.TI84PLUS;
import com.vita.splitbill.R;

// Android phones do this cute thing where the
// system back button is annoyingly close to where
// one rests their thumb. This class is basically
// a dialog that responds to the back button in the
// Menu activity to stop the activity from being
// closed from an accidental tap of the back button.

public class BackDialog extends DialogFragment {

    public BackDialog(){

    }

    public static BackDialog newInstance(String title) {
        BackDialog frag = new BackDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.exit)
                .setTitle(R.string.exit_confirm)
                .setCancelable(false)
                .setPositiveButton(R.string.y, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TI84PLUS.clearBill();
                        getActivity().finish();
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
