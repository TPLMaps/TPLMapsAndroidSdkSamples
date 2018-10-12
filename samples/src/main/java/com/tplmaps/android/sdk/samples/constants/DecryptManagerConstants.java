package com.tplmaps.android.sdk.samples.constants;

import android.content.Context;

import com.tplmaps.android.sdk.samples.utils.CipherUtils;

@SuppressWarnings("FieldCanBeLocal")
public class DecryptManagerConstants {

    private Context mContext;

    private static DecryptManagerConstants mInstance;

    private final String O_M_K = "MiXViOMloiBvoSa2KD9g7RGtFSSMhQq83llsbfh7X9cDaNNKT4bQFStaj6XV3fn0";

    private DecryptManagerConstants(Context context) {
        mContext = context;
    }

    public static DecryptManagerConstants getInstance(Context context) {
        if (mInstance == null)
            mInstance = new DecryptManagerConstants(context);

        return mInstance;
    }

    public String getKey() {
        return CipherUtils.decrypt(mContext, O_M_K);
    }
}
