package com.android.record;

import android.app.Application;

import com.android.record.db.DaoManager;

/**
 * date：2021-07-15
 * desc：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.getInstance().init(this);
    }

}
