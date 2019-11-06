package com.example.mmitraprogramteam.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import com.example.mmitraprogramteam.R
import kotlin.random.Random

internal class SliderAdapter() : PagerAdapter() {


    lateinit var  context: Context
    lateinit var image_Array: IntArray
    lateinit var imageName: List<String>
    constructor(context: Context, image_Array: IntArray) : this() {
        this.context = context
        this.image_Array = image_Array
    }



    override fun getCount(): Int {
        return image_Array.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @SuppressLint("NewApi")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_slider, null)

        val linearLayout = view.findViewById(R.id.linearLayout) as LinearLayout
        var imageView= view.findViewById(R.id.imageView) as ImageView
        imageView.setImageResource(image_Array[position])
        //linearLayout.background=ContextCompat.getDrawable(context, R.drawable.image1)
      /*  when (position){
            1 ->
                linearLayout.background=(context.getDrawable(R.drawable.image1))
            2 ->
                linearLayout.background=(context.getDrawable(R.drawable.imag2))
            3->
                linearLayout.background=(context.getDrawable(R.drawable.image3))
        }*/

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
