package com.tplmaps.android.sdk.samples.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Base64;

import java.security.MessageDigest;

import static android.content.pm.PackageManager.GET_SIGNATURES;

/**
 * Created by hassanjamil on 2016-07-26.
 *
 * @author hassanjamil
 */
public class KeyHashUtils {

    //private static String TAG = KeyHashUtils.class.getSimpleName();

    public static String getKeyHashBase64(Context context) {
        try {
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                if (md.digest() != null)
                    return Base64.encodeToString(md.digest(), Base64.DEFAULT);
                //CommonUtilities.log_i(TAG, "KeyHash= " + strKeyHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
