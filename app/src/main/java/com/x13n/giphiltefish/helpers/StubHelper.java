package com.x13n.giphiltefish.helpers;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Shortcuts relating to this app just being for code review.
 *
 * Created by alex on 06/10/15.
 */
public class StubHelper {
    public static void showYouBrokeItDialog(String message, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("You broke it")
                .setMessage(message)
                .setPositiveButton("Okay", null)
                .show();
    }
}
