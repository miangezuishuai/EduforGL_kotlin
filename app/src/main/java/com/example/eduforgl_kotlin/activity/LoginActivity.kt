package com.example.eduforgl_kotlin.activity

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.preference.PreferenceManager
import android.view.View
import android.widget.*
import com.example.eduforgl_kotlin.R
import com.example.eduforgl_kotlin.util.HttpUtil
import com.example.eduforgl_kotlin.util.ImageUtil
import com.example.eduforgl_kotlin.util.LogUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException




class LoginActivity : AppCompatActivity() {

    companion object{
        const val LOGIN_FAIL = 0
        const val LOGIN_SUCC = 1
    }

    private lateinit var edit_accound:EditText
    private lateinit var edit_password:EditText
    private lateinit var remember_pass:CheckBox
    private lateinit var edit_checkCode:EditText
    private lateinit var img_checkCode:ImageView
    private lateinit var btn_login:Button

    private var header_s:String = ""
    private var account:String = ""

    private lateinit var pref:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor

    private val handelr:Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                LOGIN_FAIL -> Toast.makeText(this@LoginActivity, "请输入账号密码验证码或检查网络连接", Toast.LENGTH_SHORT).show()
                LOGIN_SUCC -> {
                    Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@LoginActivity,MainActivity::class.java)
                    intent.putExtra("account", account)
                    intent.putExtra("cookie_header", header_s)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pref = PreferenceManager.getDefaultSharedPreferences(this)
        initView()

        //获取验证码并显示
        getCheckCode()
        //点击验证码图片更新验证码更新验证码
        img_checkCode.setOnClickListener(View.OnClickListener {
            getCheckCode()
        })

        //记住密码功能
        var isRemember = pref.getBoolean("remember_password", false)
        if (isRemember) {
            var account = pref.getString("account","")
            var password = pref.getString("password","")
            edit_accound.setText(account)
            edit_password.setText(password)
            remember_pass.isChecked = true
        }

        //登录
        btn_login.setOnClickListener(View.OnClickListener {
            account = edit_accound.text.toString()
            var password = edit_password.text.toString()
            var checkCode = edit_checkCode.text.toString()
            if (account.isEmpty() || password.isEmpty() || checkCode.isEmpty()||header_s.isEmpty()) {
                Toast.makeText(this@LoginActivity, "请输入账号密码验证码或检查网络连接", Toast.LENGTH_SHORT).show()
            } else {
                //可以登录
                //判断是否记住密码
                editor = pref.edit()
                if (remember_pass.isChecked) {
                    editor.putBoolean("remenber_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                } else {
                    editor.clear()
                }

                editor.apply()

                //登录
                login(account,password,checkCode)
            }
        })
    }

    fun initView() {
        edit_accound = findViewById(R.id.account)
        edit_password = findViewById(R.id.password)
        remember_pass = findViewById(R.id.remember_pass)
        edit_checkCode = findViewById(R.id.edit_checkCode)
        img_checkCode = findViewById(R.id.img_checkCode)
        btn_login = findViewById(R.id.button_login)
    }


    //获取验证码
    fun getCheckCode() {
        HttpUtil.httpGetNoHeader("http://jwc.gdlgxy.com/CheckCode.aspx",object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                //连接失败，
                this@LoginActivity.runOnUiThread(Runnable {
                    Toast.makeText(this@LoginActivity,"获取验证码失败，请检查网络",Toast.LENGTH_SHORT).show()
                })

            }

            override fun onResponse(call: Call, response: Response) {
                //连接成功
                val byte_image:ByteArray = response.body!!.bytes()
                this@LoginActivity.runOnUiThread(Runnable {
                    img_checkCode.setImageBitmap(ImageUtil.getPicFromBytes(byte_image,null))
                })
                //获取头信息
                val headers = response.headers
                val cookies:List<String> = headers.values("Set-Cookie")
                val session = cookies[0]
                LogUtil.d("LoginActivity","session:"+session)
                header_s = session.substring(0,session.indexOf(";"))
                LogUtil.d("LoginActivity","headers_s:"+header_s)
            }
        })
    }


    //登录,这个只是判断连接是否成功，登录是否成功要在连接成功后进行判断
    fun login(account: String, password: String, checkcode: String) {
        HttpUtil.postLogin(account,password,checkcode,header_s,object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                this@LoginActivity.runOnUiThread(Runnable {
                    Toast.makeText(this@LoginActivity,"登录失败，请检查网络",Toast.LENGTH_SHORT).show()
                })
            }

            override fun onResponse(call: Call, response: Response) {
                var message = Message()
                //通过response.toString()查看，如果登录成功则指向学号的网址，如果登录失败则返回default2的网址
                if (response.toString().contains("default2")) {
                    message.what = LOGIN_FAIL
                } else {
                    message.what = LOGIN_SUCC
                }
                handelr.sendMessage(message)
                LogUtil.d("LoginActivity_index",response.toString())
            }
        })
    }
}
