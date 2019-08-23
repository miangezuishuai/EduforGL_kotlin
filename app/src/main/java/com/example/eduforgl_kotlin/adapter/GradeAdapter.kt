package com.example.eduforgl_kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.eduforgl_kotlin.R
import com.example.eduforgl_kotlin.bean.Bean_Grade

class GradeAdapter(val gradeList: List<Bean_Grade>) : RecyclerView.Adapter<GradeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title1:TextView = view.findViewById(R.id.title_textview1)
        var title2:TextView = view.findViewById(R.id.title_textview2)
        var title3:TextView = view.findViewById(R.id.title_textview3)
        var title4:TextView = view.findViewById(R.id.title_textview4)
    }

    override fun getItemCount(): Int {
        return gradeList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if (p1 == 0) {
            //html解析第一个为标题，并非成绩
            p0.itemView.visibility = View.GONE
        } else {
            var bean_Grade: Bean_Grade = gradeList.get(p1)
            p0.title1.setText(bean_Grade.class_name)
            p0.title2.setText(bean_Grade.final_grade)
            p0.title3.setText(bean_Grade.grade)
            p0.title4.setText(bean_Grade.grade_bukao)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_title, p0, false)
        return ViewHolder(view)
    }


}