package com.tplmaps.android.sdk.samples.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.constants.SecurityConstants;


public class CommonUtilities {

    //private static final String TAG = CommonUtilities.class.getSimpleName();

    private static Toast toastShort;
    private static Toast toastlong;

    /**
     * Shows toast for short duration
     */
    public static void toastShort(Context context, String msg) {
        if (context == null)
            return;

        if (toastShort != null)
            toastShort.cancel();
        toastShort = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toastShort.show();
    }

    /**
     * Shows toast for long duration
     */
    public static void toastLong(Context context, String msg) {
        if (context == null)
            return;

        if (toastlong != null)
            toastlong.cancel();
        toastlong = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toastlong.show();
    }


    public static void log_i(String tag, String message) {
        if (SecurityConstants.BUILD_CONFIG_DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void log_e(String tag, String message) {
        if (SecurityConstants.BUILD_CONFIG_DEBUG) {
            Log.e(tag, message);
        }
    }

    @SuppressWarnings("unused")
    public static void log_v(String tag, String message) {
        if (SecurityConstants.BUILD_CONFIG_DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void log_d(String tag, String message) {
        if (SecurityConstants.BUILD_CONFIG_DEBUG) {
            Log.d(tag, message);
        }
    }


    /**
     * Use to hide soft keyboard from screen whenever its call.
     */
    /*public static void hideSoftKeyboard(Context context){
        //InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
        
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

	/**
	 * Used to hide/show keyboard instance attached to a view.
	 */
    public static void hideShowSoftKeyboardFromView(Context context, View view, boolean shouldHide) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (shouldHide)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        else
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Used to hide/show keyboard by getting focused view.
     */
    @SuppressWarnings("SameParameterValue")
    public static void hideShowSoftKeyboardFromFocusedView(Activity activity, boolean shouldHide) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (shouldHide)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            else
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * Returns status bar height in dps
     *
     * @return height
     */
    public static int getStatusBarHeight(Activity activity) {
        int height = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    /**
     * Get action bar height
     *
     * @return {@code Integer} value > -1 if action bar height calculated otherwise will return -1
     */
    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());

        return -1;
    }


    private static Toast toastUp;

    /**
     * Shows toast for short duration
     */
    public static void toastShortUp(Context context, String msg) {
        if (toastUp != null)
            toastUp.cancel();
        toastUp = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toastUp.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, (int) context.getResources().getDimension(R.dimen.toast_top_padding));
        toastUp.show();
    }
}
