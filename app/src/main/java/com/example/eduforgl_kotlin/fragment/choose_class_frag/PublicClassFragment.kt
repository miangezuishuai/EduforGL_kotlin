package com.example.eduforgl_kotlin.fragment.choose_class_frag

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eduforgl_kotlin.R
import kotlinx.android.synthetic.main.fragment_choose_class_item_frag.*

class PublicClassFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_choose_class_item_frag,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tab_choose_class_item.setText("公共选修选课功能暂未开放")
    }
}