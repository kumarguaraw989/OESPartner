package com.example.oespartner.iosProgressbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;

import com.example.oespartner.R;

public class myProgress extends Activity {
    private static final int DIALOG_LOADING =1000 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_LOADING:
                final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.loading);
                dialog.setCancelable(true);
               dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                   @Override
                   public void onCancel(DialogInterface dialog) {

                   }
               });
                return dialog;

            default:
                return null;
        }
    };


}
