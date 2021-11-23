package com.soc.shopmanagmentsoc.util;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import androidx.appcompat.app.AlertDialog;

public class AllClassHere {
    Context context;

    public AllClassHere(Context context) {
        this.context = context;
    }

    public void errorAlertBtn1(String title,String msg,String btnName) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan( Color.RED );
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder( title );
        ssBuilder.setSpan( foregroundColorSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setTitle( title );
        builder.setMessage( msg );
        builder.setPositiveButton( btnName,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void validationAlert(String msg) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan( Color.RED );
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setMessage( msg );
        builder.setPositiveButton( "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
}
