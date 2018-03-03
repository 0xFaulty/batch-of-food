package com.comand.foodhack.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressBarViewer {
    private static ProgressDialog progress;

    public static void view(Activity activity, String message) {
        progress = new ProgressDialog(activity);
        progress.setMessage(message);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }

    public static void hide() {
        if ((progress != null) && (progress.isShowing()))
            progress.dismiss();
    }
}
