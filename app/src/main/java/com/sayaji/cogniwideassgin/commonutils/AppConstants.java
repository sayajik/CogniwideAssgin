package com.sayaji.cogniwideassgin.commonutils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.sayaji.cogniwideassgin.datamodel.Result;

import java.util.ArrayList;
import java.util.List;

public class AppConstants {
    public static String TAG = "Test";
    public static int START_MAIN_ACTIVITY = 10;
    public static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    public static List<Result> movieItems = new ArrayList<>();
    public static int pageCount = 0;

    public static void showErrorDialog(Context mContext, String errorMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(errorMsg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        builder.setCancelable(false);
        builder.create().show();
    }
}
