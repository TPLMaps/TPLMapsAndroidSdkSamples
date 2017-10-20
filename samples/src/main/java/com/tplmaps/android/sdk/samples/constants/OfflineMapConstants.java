package com.tplmaps.android.sdk.samples.constants;

import android.content.Context;

import com.tplmaps.android.sdk.samples.utils.CipherUtils;

@SuppressWarnings("FieldCanBeLocal")
public class OfflineMapConstants {

    private Context mContext;

    private static OfflineMapConstants mInstance;

    private final String O_M_K = "MiXViOMloiBvoSa2KD9g7RGtFSSMhQq83llsbfh7X9cDaNNKT4bQFStaj6XV3fn0";

    private OfflineMapConstants(Context context) {
        mContext = context;
    }

    public static OfflineMapConstants getInstance(Context context) {
        if (mInstance == null)
            mInstance = new OfflineMapConstants(context);

        return mInstance;
    }

    public String getOfflineMapKey() {
        return CipherUtils.decrypt(mContext, O_M_K);
    }
}
