package com.example.leedonggyu.mobile_app_develop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


import android.content.Intent;
import android.os.Bundle;

public class showMemoActivity extends Activity {

    private Intent intent;
    private String title;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_memo);

        intent = getIntent();
        title = intent.getStringExtra("date");
        text = intent.getStringExtra("text");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(text).setCancelable(false).setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                stopService(getIntent());
                dialogInterface.cancel();
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
