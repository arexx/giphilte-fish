package com.x13n.giphiltefish.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;

/**
 * Shortcuts relating to this app just being for code review.
 *
 * Created by alex on 06/10/15.
 */
public class StubHelper {
    public static void showYouBrokeItDialog(String message, @Nullable final Runnable onRetry, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("You broke it")
                .setMessage(message)
                .setNegativeButton("Oh well", null);

        if (onRetry != null) {
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onRetry.run();
                }
            });
        }

        builder.show();
    }

    public static void showYouBrokeItDialog(String message, Context context) {
        showYouBrokeItDialog(message, null, context);
    }
}
