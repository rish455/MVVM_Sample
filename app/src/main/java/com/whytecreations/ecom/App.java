package com.whytecreations.ecom;

import android.app.Application;

import com.whytecreations.ecom.utils.PreferenceUtils;

/**
 * @author Rishad
 * @since 17/08/2021
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.newInstance(getApplicationContext());
    }
}
