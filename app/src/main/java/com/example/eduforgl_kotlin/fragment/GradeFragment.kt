package com.example.eduforgl_kotlin.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eduforgl_kotlin.R
import com.example.eduforgl_kotlin.adapter.GradeAdapter
import com.example.eduforgl_kotlin.bean.Bean_Grade
import com.example.eduforgl_kotlin.util.HttpUtil
import com.example.eduforgl_kotlin.util.LogUtil
import kotlinx.android.synthetic.main.fragment_grade.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException

class GradeFragment : Fragment() {

    private var account:String = "" //学号
    private var header_s:String = "" //cokie,,登录头信息
    private var name_gb2312:String = "" //gb2312编码后的名字

    private var gradeList = ArrayList<Bean_Grade>()

    private lateinit var gradeAdapter:GradeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        account = arguments!!.getString("account")
        header_s = arguments!!.getString("cookie_header")
        name_gb2312 = arguments!!.getString("name_gb2312")
        var view:View = inflater.inflate(R.layout.fragment_grade,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //先设置适配器，在网络请求拿到数据后进行更新
        grade_recyclerView.layoutManager = LinearLayoutManager(activity)
        gradeAdapter = GradeAdapter(gradeList)
        grade_recyclerView.adapter = gradeAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getGrade(account,name_gb2312)
    }

    private fun getGrade(xh: String, xm: String) {
        HttpUtil.httpGetGrade(account,name_gb2312,header_s,object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                LogUtil.d("GradeFragment:", "获取分数失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val htmlString = response.body?.string()
                if (htmlString != null) {
                    LogUtil.d("GradeFragment:","成绩代码"+htmlString)
                }
                activity!!.runOnUiThread(object: Runnable {
                    override fun run() {
                        if (htmlString != null) {
                            parse_Grade(htmlString)
                        }
                        LogUtil.d("GradeFragment:","获取成绩成功")
                    }
                })
            }
        })

    }

    private fun parse_Grade(htmlString: String) {
        var replace_html = htmlString.replace("&nbsp;","无")  //&nbsp;处理，不处理在后边解析会被过滤掉造成数组越界
        var doc = Jsoup.parse(replace_html)
        var element_grade = doc.getElementById("DataGrid1")
        var links = element_grade.getElementsByTag("tr")
        for (link: Element in links) {
            var link_value = link.text().split(" ")
            var bean_Grade = Bean_Grade(
                link_value[0],
                link_value[1],
                link_value[2],
                link_value[3],
                link_value[4],
                link_value[5],
                link_value[6],
                link_value[7],
                link_value[8],
                link_value[9]
            )
            gradeList.add(bean_Grade)
        }

        gradeAdapter.notifyItemInserted(0)
    }
}