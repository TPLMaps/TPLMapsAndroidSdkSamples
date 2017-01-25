package com.tplmaps.android.sdk.samples.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hassanjamil on 2017-01-24.
 * @author hassanjamil
 */

public class CommonUtils {

    private static Toast toast;

    public static void showToast(Context context, String message, int duration) {
        if(toast != null)
            toast.cancel();

        toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
