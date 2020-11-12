package com.tplmaps.android.sdk.samples;

import android.app.Application;
import android.content.Context;

//@AcraCore(buildConfigClass = BuildConfig.class)
/*@ReportsCrashes(mailTo = "hassan.jamil@tplmaps.com",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text)*/
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        /*try {
            final ACRAConfiguration config = new ConfigurationBuilder(this)
                    .build();
            // The following line triggers the initialization of ACRA
            ACRA.init(this, config);
        } catch (ACRAConfigurationException e) {
            e.printStackTrace();
        }*/
    }
}
