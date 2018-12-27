package com.nurdcoder.customerappdemo.other;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Base64;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.google.android.gms.analytics.Tracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ApplicationController extends MultiDexApplication {
    public static final String TAG = ApplicationController.class.getSimpleName();
    private static ApplicationController mInstance;
    private RequestQueue mRequestQueue;
    private Tracker mTracker;

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    /**
     * insight.arup@gmail.com
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    @Override
    public void onCreate() {
        super.onCreate();
        printKeyHash();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    /**
     * Call this method inside onCreate once to get your hash key
     */
    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.afchealth.afcpharmacyonline", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
    }
}