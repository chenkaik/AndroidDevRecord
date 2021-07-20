package com.android.record.activity

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.record.databinding.ActivitySpeechBinding
import java.util.*

class SpeechActivity : AppCompatActivity(), View.OnClickListener, OnInitListener {

    private lateinit var textToSpeech: TextToSpeech
    private lateinit var activitySpeechBinding: ActivitySpeechBinding

    companion object {
        private const val TAG = "SpeechActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySpeechBinding = ActivitySpeechBinding.inflate(layoutInflater);
        setContentView(activitySpeechBinding.root)
        activitySpeechBinding.btnTestPlay.setOnClickListener(this)
        textToSpeech = TextToSpeech(this, this)
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
        textToSpeech.setPitch(1.0f)
        // 设定语速，默认1.0正常语速
        textToSpeech.setSpeechRate(0.5f)
    }

    override fun onClick(v: View?) {
        if (!textToSpeech.isSpeaking) {
            // val random = ((Math.random() * 9 + 1) * 100).toInt()
            // 朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
            // TextToSpeech.QUEUE_FLUSH 新的任务替代以前的任务，直接中断以前的任务
            val content = activitySpeechBinding.editTest.text.toString().trim()
            Log.e(TAG, "onClick: $content")
            if (!TextUtils.isEmpty(content)) {
                textToSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null, null)
            } else {
                textToSpeech.speak("测试语音播放", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    override fun onInit(status: Int) {
        Log.e(TAG, "onInit: $status")
        if (status == TextToSpeech.SUCCESS) {
            // 设置语言
//            textToSpeech.language = Locale.CHINA
            val result = textToSpeech.setLanguage(Locale.CHINA)
            // TextToSpeech.LANG_MISSING_DATA：表示语言的数据丢失
            // TextToSpeech.LANG_NOT_SUPPORTED：不支持
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "初始化失败", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 不管是否正在朗读TTS都被打断
        textToSpeech.stop()
        // 关闭，释放资源
        textToSpeech.shutdown()
    }

}