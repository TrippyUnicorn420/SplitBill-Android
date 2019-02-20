package com.whatamidoingwithmylife.splitbill;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.whatamidoingwithmylife.splitbill.core.TI84PLUS;
import com.whatamidoingwithmylife.splitbill.core.Printer;

import java.io.FileNotFoundException;

// This is the Bill activity.
// This activity has been the most fun to work with,
// primarily because of how easy it's been to implement.

// It literally took me 15 minutes to start and finish.
public class BillActivity extends AppCompatActivity {

    private CollapsingToolbarLayout toolbar;

    private String resName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateBill();
        setActivityTitle();

        try {
            Printer.printBill(this, resName);
        }
        catch (FileNotFoundException f) {
            Log.i("SplitBill", "Failed to print the bill");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, Printer.getFileHeader() + TI84PLUS.getCurrentBillAsString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    // This method populates the RecyclerView responsible
    // for showing the Bill items.
    public void populateBill() {
        RecyclerView yesCanWeHaveTheCheckPlease = (RecyclerView) findViewById(R.id.billRecycler);
        BillRecyclerAdapter adapter = new BillRecyclerAdapter(this);
        yesCanWeHaveTheCheckPlease.setLayoutManager(new LinearLayoutManager(this));
        yesCanWeHaveTheCheckPlease.setAdapter(adapter);
        adapter.setBill(TI84PLUS.getCurrentBill());
    }

    // It's very important to clear the bill when you leave
    // this screen. Otherwise people who successively visit
    // restaurants (weirdos or very rich people) will have
    // an incorrect bill when they come back here.
    public void onBackPressed(){
        TI84PLUS.clearBill();
        Intent intent = new Intent(this, SBSplash.class);
        startActivity(intent);
    }

    // This method sets the title of the activity on the Title bar.
    public void setActivityTitle(){
        String resName;

        toolbar = findViewById(R.id.toolbar_layout);

        Intent intent = getIntent();
        resName = intent.getExtras().getString("resName");

        this.resName = resName;
        toolbar.setTitle("Bill @ " + resName);
    }
}
