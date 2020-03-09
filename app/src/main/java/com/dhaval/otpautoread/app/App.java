package com.dhaval.otpautoread.app;

import android.app.Application;
import android.content.Context;

/**
 * Created on : March 09, 2020
 * Author     : Dhaval Jayani
 */

public class App extends Application {
    private static App mInstance;
    private static Context context;
    private AppSignatureHelper appSignatureHelper;
    private static String APP_HASH_KEY = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        App.context = getApplicationContext();

        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(this);
        APP_HASH_KEY = appSignatureHelper.getAppSignatures().get(0);
    }

    public static String GetAppSignature()
    {
        return APP_HASH_KEY;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return App.context;
    }
}
