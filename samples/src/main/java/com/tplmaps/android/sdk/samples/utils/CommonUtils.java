package com.tplmaps.android.sdk.samples.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hassanjamil on 2017-01-24.
 *
 * @author hassanjamil
 */

public class CommonUtils {

    private static Toast toast;

    public static void showToast(Context context, String text, int duration, boolean cancelIfAlreadyShown) {

        if (toast != null && cancelIfAlreadyShown)
            toast.cancel();

        toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
