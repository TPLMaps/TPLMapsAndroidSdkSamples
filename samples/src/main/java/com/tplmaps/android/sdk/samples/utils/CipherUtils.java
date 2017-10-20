
package com.tplmaps.android.sdk.samples.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;

import com.tplmaps.android.sdk.samples.constants.SecurityConstants;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtils {

    private static final String TAG = CipherUtils.class.getSimpleName();

    private static SecretKeySpec getSecretKey(Context context) {
        try {
            String keyHash;

            if (SecurityConstants.BUILD_CONFIG_DEBUG)
                keyHash = SecurityConstants.KEY_HASH_DEBUG;
            else
                keyHash = KeyHashUtils.getKeyHashBase64(context);

            byte[] key = new byte[0]; // use only first 128 bit
            if (keyHash != null) {
                key = Arrays.copyOf(keyHash.getBytes(), 16);
            }
            return new SecretKeySpec(key, "AES");

        } catch (Exception e) {
            e.printStackTrace();
            CommonUtilities.log_e(TAG, "getSecretKey(Context) - Something is going wrong, " +
                    "please check your key/pass for the Build Type you compiled");
            return null;
        }
    }

    /*public static String encrypt(Context context, String strToEncrypt) {
        try {
            @SuppressLint("GetInstance")
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(context));
            return Base64.encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")), Base64.DEFAULT);
        } catch (Exception e) {
            CommonUtilities.log_e(TAG, "Error while encrypting: " + e.toString());
            return null;
        }
    }*/

    public static String decrypt(Context context, String strToDecrypt) {
        try {
            @SuppressLint("GetInstance")
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(context));
            return new String(cipher.doFinal(Base64.decode(strToDecrypt, Base64.DEFAULT)));
        } catch (Exception e) {
            CommonUtilities.log_e(TAG, "Error while decrypting: " + e.toString());
            return null;
        }
    }
}