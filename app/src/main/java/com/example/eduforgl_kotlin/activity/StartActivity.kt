package com.example.eduforgl_kotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.eduforgl_kotlin.R
import com.example.eduforgl_kotlin.util.ScreenUtil
import java.util.*

class StartActivity : AppCompatActivity() {

    private var len = 5

    private var timer = Timer()

    var task = object:TimerTask(){
        override fun run() {
            runOnUiThread(Runnable {
                len--
                if (len < 0) {
                    timer.cancel()
                    var intent = Intent(this@StartActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        ScreenUtil.TranScreen(this)  //状态栏透明
        timer.schedule(task,1000,1000)
    }


}
