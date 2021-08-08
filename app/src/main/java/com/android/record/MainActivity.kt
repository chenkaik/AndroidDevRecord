package com.android.record

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.record.activity.GreenDaoActivity
import com.android.record.activity.PopupWindowActivity
import com.android.record.activity.SpeechActivity
import com.android.record.databinding.ActivityMainBinding

/**
 * @author: chen_kai
 * @date：2021-07-15
 * @desc：主页面
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        //        setContentView(R.layout.activity_main);
        setContentView(activityMainBinding.root)
        activityMainBinding.toGreenDao.setOnClickListener(this)
        activityMainBinding.toTTs.setOnClickListener(this)
        activityMainBinding.toPopupWindow.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.to_Green_Dao -> { // greenDao的使用
                startActivity(Intent(this, GreenDaoActivity::class.java))
            }
            R.id.to_TTs -> { // 汉字转换语音
                startActivity(Intent(this, SpeechActivity::class.java))
            }
            R.id.to_PopupWindow -> { // PopupWindow的使用
                startActivity(Intent(this, PopupWindowActivity::class.java))
            }
        }
    }

}