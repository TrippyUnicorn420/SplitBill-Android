package com.whatamidoingwithmylife.splitbill.dialogs;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.whatamidoingwithmylife.splitbill.R;

// This dialog exists as a failsafe, really.

// If my S5 gets into the hands of the wrong person and they
// send SplitBill to their own device before I put it on the
// Play Store, this basically indemnifies me from all their
// potential complaints.

public class FirstLaunchDialog extends DialogFragment {

    public FirstLaunchDialog(){

    }

    public static FirstLaunchDialog newInstance(String title) {
        FirstLaunchDialog frag = new FirstLaunchDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.firstlaunchmessage)
                .setTitle(R.string.firstlaunchtitle)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();
    }

}
