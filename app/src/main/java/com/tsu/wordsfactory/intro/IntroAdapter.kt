package com.tsu.wordsfactory.intro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class IntroAdapter(fragment: FragmentActivity, private var headerList: List<Int>, private var imgList: List<Int>) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = IntroFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, headerList[position])
            putInt(ARG_OBJECT2, imgList[position])
        }
        return fragment
    }

}