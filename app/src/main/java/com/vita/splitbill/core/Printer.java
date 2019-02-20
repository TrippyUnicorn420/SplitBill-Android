package com.vita.splitbill.core;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// This is the Printer. It's responsible for printing
// bills to internal and external storage. See the readme
// for more details.

// I had to use this annotation because Android Studio
// yelled at me for not using a pre-made date format that
// the Android system would provide.
// I KNOW WHAT IM DOING K THX BYE
@SuppressWarnings("all")
public class Printer {

    private static String resName;
    private static String filename;
    private static File bill;
    private static File publicBill;

    public static void printBill(Context context, String ResName) throws FileNotFoundException {
        resName = ResName;
        Calendar cal = Calendar.getInstance();
        DateFormat fileFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        DateFormat headerDate = new SimpleDateFormat("EEEE, MMMM dd yyyy '@' h:mm:ss aa");

        filename = "SplitBill_" + ResName + "_" + fileFormat.format(cal.getTime()) + ".txt";

        bill = new File(context.getFilesDir(), filename);
        publicBill = new File(context.getExternalFilesDir("Bills"), filename);

        PrintWriter printer = new PrintWriter(bill);
        PrintWriter otherPrinter = new PrintWriter(publicBill);

        String OEM = Build.MANUFACTURER;
        String Model = Build.MODEL;
        String fileHeader = "SplitBill: " + ResName + " visit on " + OEM + " " + Model + "\n" + headerDate.format(cal.getTime()) + "\n\n";

        printer.print(fileHeader + TI84PLUS.getCurrentBillAsString());
        Log.i("SplitBill/Printer","Bill printed to " + bill.getAbsolutePath());

        otherPrinter.print(fileHeader + TI84PLUS.getCurrentBillAsString());
        Log.i("SplitBill/Printer", "Public bill printed to " + publicBill.getAbsolutePath());
        printer.close();
    }

    public static String getFileHeader() {
        Calendar cal = Calendar.getInstance();
        DateFormat headerDate = new SimpleDateFormat("EEEE, MMMM dd yyyy '@' h:mm:ss aa");
        String fileHeader = "SplitBill: visit to " + resName  + " on " + headerDate.format(cal.getTime()) + "\n\n";
        return fileHeader + "\n";
    }

    public static File getBill() throws IOException {
        return publicBill;
    }

}
