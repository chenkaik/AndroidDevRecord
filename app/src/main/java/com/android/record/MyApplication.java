package com.android.record;

import android.app.Application;

import com.android.record.db.DaoManager;

/**
 * @author chen_kai
 * @date：2021-07-15
 * @desc：程序入口
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.getInstance().init(this);
    }

}
