package com.example.eduforgl_kotlin.util

import android.app.ActionBar
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View

class ScreenUtil {
    companion object {

        //4.4以上系统状态栏透明
        fun TranScreen(activity: Activity) {
            if (Build.VERSION.SDK_INT >= 21) {
                val decorView = activity.getWindow().getDecorView()
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT)
            }
        }

    }
}