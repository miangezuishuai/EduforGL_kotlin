package com.example.eduforgl_kotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ChooseClassPageAdapter(fm:FragmentManager,var fragments:List<Fragment>,var titles:List<String>): FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return fragments.get(p0)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }

}