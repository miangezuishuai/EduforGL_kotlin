package com.example.eduforgl_kotlin.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.eduforgl_kotlin.R
import com.example.eduforgl_kotlin.fragment.ChooseClassFragment
import com.example.eduforgl_kotlin.fragment.GradeFragment
import com.example.eduforgl_kotlin.util.HttpUtil
import com.example.eduforgl_kotlin.util.LogUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {

    companion object{
        const val INDEX_SUCC = 1
    }

    private var account:String = "" //学号
    private var header_s:String = "" //cokie,,登录头信息
    private var name_gb2312:String = "" //gb2312编码后的名字

    private var gradeFragment = GradeFragment()
    private var chooseClassFragment = ChooseClassFragment()


    private val handel:Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                INDEX_SUCC -> supportActionBar?.title = "欢迎您,"+msg.obj
            }
        }
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_grade -> {
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.main_frameLayout,gradeFragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_class -> {
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.main_frameLayout,chooseClassFragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
//            R.id.navigation_notifications -> {
//                textMessage.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        account = intent.getStringExtra("account")
        header_s = intent.getStringExtra("cookie_header")

        getName()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frameLayout,gradeFragment)
        transaction.commit()

        //向Fragment传递信息
        var bundle = Bundle()
        bundle.putString("account", account)
        bundle.putString("cookie_header", header_s)
        bundle.putString("name_gb2312",name_gb2312)
        gradeFragment.arguments = bundle
    }

    private fun getName() {
        HttpUtil.httpGetName(account,header_s,object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                LogUtil.d("MainActivity:","获取主页失败")
            }

            override fun onResponse(call: Call, response: Response) {
                var message = Message()
                message.what = INDEX_SUCC

                //获取名字
                var doc = Jsoup.parse(response.body?.string())
                var element_name = doc.getElementById("xhxm")
                var element_text = element_name.text() //此时获取到的字符串为xxx同学
                //中文状态的真实名字
                var real_name = element_text.substring(0,element_text.length-2)
                //urlDecode编码后的姓名，用于后边成绩查询和选课的参数
                name_gb2312 = java.net.URLEncoder.encode(real_name, "gb2312")
                message.obj = real_name
                handel.sendMessage(message)
            }
        })
    }
}
