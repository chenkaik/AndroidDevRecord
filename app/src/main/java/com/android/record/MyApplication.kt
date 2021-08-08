package com.android.record

import android.app.Application
import com.android.record.db.DaoManager

/**
 * @author chen_kai
 * @date：2021-07-15
 * @desc：程序入口
 */
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaoManager.getInstance().init(this)
    }

}