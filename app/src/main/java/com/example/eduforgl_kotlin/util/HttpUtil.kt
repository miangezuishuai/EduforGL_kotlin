package com.example.eduforgl_kotlin.util

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.FormBody




class HttpUtil {
    companion object {

        //用于获取验证码
        fun httpGetNoHeader(address: String, callback: okhttp3.Callback) {
            val client = OkHttpClient()
            val request = Request.Builder().url(address).build()
            client.newCall(request).enqueue(callback)
        }

        //获取名字
        fun httpGetName(account: String, header_s: String, callback: okhttp3.Callback) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .addHeader("cookie",header_s)
                .url("http://jwc.gdlgxy.com/xs_main.aspx?xh=" + account)
                .build()
            client.newCall(request).enqueue(callback)
        }

        //登录,这个只是判断连接是否成功，登录是否成功要在连接成功后进行判断
        fun postLogin(account: String, password: String, checkcode: String,header_s:String ,callback: okhttp3.Callback) {
            val formbody = FormBody.Builder()
                .add("__VIEWSTATE", "dDwyODE2NTM0OTg7Oz4un94v297GQr+0LRyaj2U4eJ629w==")
                .add("__VIEWSTATEGENERATOR", "92719903")
                .add("txtUserName", account)
                .add("TextBox2", password)
                .add("txtSecretCode", checkcode)
                .add("RadioButtonList1", "%D1%A7%C9%FA")
                .add("Button1", "")
                .add("lbLanguage", "")
                .add("hidPdrs", "")
                .add("hidsc", "")
                .build()
            val request = Request.Builder()
                .addHeader("cookie", header_s)
                .url("http://jwc.gdlgxy.com/default2.aspx")
                .post(formbody)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(callback)
        }

        //获取分数
        fun httpGetGrade(xh: String, xm_gb2312: String,header_s: String,callback: okhttp3.Callback) {
            var formbody = FormBody.Builder()
                .add("__VIEWSTATE", "dDw0MTg3MjExMDA7dDw7bDxpPDE+Oz47bDx0PDtsPGk8MT47aTwxNT47aTwxNz47aTwyMz47aTwyNT47aTwyNz47aTwyOT47aTwzMD47aTwzMj47aTwzND47aTwzNj47aTw0OD47aTw1Mj47PjtsPHQ8dDw7dDxpPDIwPjtAPFxlOzIwMDEtMjAwMjsyMDAyLTIwMDM7MjAwMy0yMDA0OzIwMDQtMjAwNTsyMDA1LTIwMDY7MjAwNi0yMDA3OzIwMDctMjAwODsyMDA4LTIwMDk7MjAwOS0yMDEwOzIwMTAtMjAxMTsyMDExLTIwMTI7MjAxMi0yMDEzOzIwMTMtMjAxNDsyMDE0LTIwMTU7MjAxNS0yMDE2OzIwMTYtMjAxNzsyMDE3LTIwMTg7MjAxOC0yMDE5OzIwMTktMjAyMDs+O0A8XGU7MjAwMS0yMDAyOzIwMDItMjAwMzsyMDAzLTIwMDQ7MjAwNC0yMDA1OzIwMDUtMjAwNjsyMDA2LTIwMDc7MjAwNy0yMDA4OzIwMDgtMjAwOTsyMDA5LTIwMTA7MjAxMC0yMDExOzIwMTEtMjAxMjsyMDEyLTIwMTM7MjAxMy0yMDE0OzIwMTQtMjAxNTsyMDE1LTIwMTY7MjAxNi0yMDE3OzIwMTctMjAxODsyMDE4LTIwMTk7MjAxOS0yMDIwOz4+Oz47Oz47dDxwPDtwPGw8b25jbGljazs+O2w8cHJldmlldygpXDs7Pj4+Ozs+O3Q8cDw7cDxsPG9uY2xpY2s7PjtsPHdpbmRvdy5jbG9zZSgpXDs7Pj4+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85a2m5Y+377yaMTYxMjQwMjYwMjA0NDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85aeT5ZCN77ya6ZmI5qOJ5p2wOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlrabpmaLvvJrkv6Hmga/lt6XnqIvns7s7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOS4k+S4mu+8mjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w86K6h566X5py656eR5a2m5LiO5oqA5pyv77yI5pys56eR77yJOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzooYzmlL/nj63vvJoxNuacrOenkeiuoeenkTLnj607Pj47Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8Ozs7Ozs7Ozs7Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDxISFhZOz4+Oz47Oz47dDxAMDw7Ozs7Ozs7Ozs7Pjs7Pjs+Pjs+Pjs+Zt3Ki4a8ni7Os9kcEqQGEw6N6g4=")
                .add("__VIEWSTATEGENERATOR", "8963BEEC")
                .add("ddlXN", "")
                .add("ddlXQ", "")
                .add("txtQSCJ","0")
                .add("txtZZCJ","100")
                .add("Button2", "%D4%DA%D0%A3%D1%A7%CF%B0%B3%C9%BC%A8%B2%E9%D1%AF")
                .build();
            var request = Request.Builder()
                .addHeader("cookie", header_s)
                .addHeader("Referer","http://jwc.gdlgxy.com/xscj.aspx?xh="+xh+"&xm="+xm_gb2312+"&gnmkdm=N121604")
                .addHeader("Host","jwc.gdlgxy.com")
                .url("http://jwc.gdlgxy.com/xscj.aspx?xh="+xh+"&xm="+xm_gb2312+"&gnmkdm=N121604")
                .post(formbody)
                .build();
            val client = OkHttpClient()
            client.newCall(request).enqueue(callback)
        }
    }
}