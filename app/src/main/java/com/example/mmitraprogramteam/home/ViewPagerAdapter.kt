package com.example.mmitraprogramteam.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.bumptech.glide.Glide
import android.R



/*
class ViewPagerAdapter(var context : Context) : PagerAdapter() {
    private var layoutInflater : LayoutInflater?=null
var Image = arrayOf(R.drawable.image1,R.drawable.imag2,R.drawable.image3)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return  view === `object`
    }

    override fun getCount(): Int {
    return Image.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.viewpager_activity , null)
        val image = v.findViewById<View>(R.id.imageview) as ImageView


        image.setImageResource(Image[position])
        val vp = container as ViewPager
        vp.addView(v , 0)


        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        var vp = container as ViewPager
        var v= `object` as View
        vp.removeView(v)
    }
}*/
/*
private class ViewPagerAdapter(private val context: Context) :
    SliderViewAdapter<ViewPagerAdapter.SliderAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.textViewDescription.text = "This is slider item $position"

        when (position) {
            0 -> Glide.with(viewHolder.itemView)
                .load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
            1 -> Glide.with(viewHolder.itemView)
                .load("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
            2 -> Glide.with(viewHolder.itemView)
                .load("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
            else -> Glide.with(viewHolder.itemView)
                .load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
        }

    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return 4
    }

    internal inner class SliderAdapterVH(var itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider)
        }
    }
}*/
