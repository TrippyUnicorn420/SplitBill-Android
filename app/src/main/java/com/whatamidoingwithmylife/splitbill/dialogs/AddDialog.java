package com.whatamidoingwithmylife.splitbill.dialogs;

// When the Amy button is pressed, this dialog pops up
// to let you add something that isn't on the menu.

// This makes the app actually usable by people such as Amy.

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whatamidoingwithmylife.splitbill.R;
import com.whatamidoingwithmylife.splitbill.core.TI84PLUS;
import com.whatamidoingwithmylife.splitbill.entities.Food;

public class AddDialog extends DialogFragment {

    public AddDialog() {

    }

    public static AddDialog newInstance(String title) {
        AddDialog frag = new AddDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.amybuttonlayout, null))
                .setTitle(R.string.amybuttontitle)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText tf1 = getDialog().findViewById(R.id.amyFoodEditText);
                        EditText tf2 = getDialog().findViewById(R.id.amyPriceEditText);
                        Food food = new Food(tf1.getText().toString(), null, tf2.getText().toString(), 0);
                        TI84PLUS.addToBill(food);
                        Toast.makeText(getContext(), "Added " + tf1.getText().toString() + " to the bill.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.n, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });

        return builder.create();
    }

}
