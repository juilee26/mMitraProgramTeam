package com.example.mmitraprogramteam.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import com.example.mmitraprogramteam.R

internal class SliderAdapter() : PagerAdapter() {


    lateinit var  context: Context
    private var imageArray : IntArray = intArrayOf(R.drawable.slide01,R.drawable.slide02,R.drawable.slide03)
    constructor(context: Context) : this() {
        this.context = context

    }



    override fun getCount(): Int {
        return imageArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @SuppressLint("NewApi")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_slider, null)

        var imageView= view.findViewById(R.id.imageView) as ImageView
        imageView.setImageResource(imageArray[position])
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}
