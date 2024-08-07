package com.android.inventariocmrm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public SharedPreferences preferences;
    Activity activity;
    public String prefName = "equipamentos";

    public MyApplication() {
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Realm.init(getApplicationContext());
        // init realm database
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("equipamentos.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
