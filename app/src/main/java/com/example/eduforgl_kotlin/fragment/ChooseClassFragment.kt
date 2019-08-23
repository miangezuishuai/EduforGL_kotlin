package com.example.eduforgl_kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eduforgl_kotlin.R
import com.example.eduforgl_kotlin.adapter.ChooseClassPageAdapter
import com.example.eduforgl_kotlin.fragment.choose_class_frag.PhysicalEduClassFragment
import com.example.eduforgl_kotlin.fragment.choose_class_frag.PublicClassFragment
import kotlinx.android.synthetic.main.fragment_choose_class.*

class ChooseClassFragment : Fragment() {

    private val tabTitle = listOf("公共选修","体育选课")

   // private var chooseClass_ViewPage:ViewP

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_choose_class,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tab_choose_class
    }

    private fun initViewPager() {
        for (size in 0 until tabTitle.size) {
            val s:String = tabTitle.get(size)
            tab_choose_class.addTab(tab_choose_class.newTab().setText(s))
        }

//        tab_choose_class.addTab(tab_choose_class.newTab().setText("公共选修"))
//        tab_choose_class.addTab(tab_choose_class.newTab().setText("体育选课"))

        var fragments = ArrayList<Fragment>()
        fragments.add(PublicClassFragment())
        fragments.add(PhysicalEduClassFragment())

        var chooseAdapter = ChooseClassPageAdapter(activity!!.supportFragmentManager,fragments,tabTitle)
        //给ViewPager设置适配器
        viewpager_choose_class.adapter = chooseAdapter
        //将TabLayout和ViewPager关联起来
        tab_choose_class.setupWithViewPager(viewpager_choose_class)

        //给TabLayout设置适配器
        tab_choose_class.setTabsFromPagerAdapter(chooseAdapter)
    }
}