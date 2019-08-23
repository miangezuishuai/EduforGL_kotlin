package com.example.eduforgl_kotlin.bean

class Bean_Grade constructor(val class_code: String, //课程代码
                             val class_name: String,  //课程名称
                             val class_type:String,   //课程性质
                             val final_grade: String, //期末成绩
                             val grade: String,       //成绩
                             val class_attribution: String,  //课程归属
                             val grade_bukao: String,    //补考成绩
                             val grade_chongxiu: String,   //重修成绩
                             val credit: String,         //学分
                             val fuxiu_biaoji: String) {   //辅修标记
}