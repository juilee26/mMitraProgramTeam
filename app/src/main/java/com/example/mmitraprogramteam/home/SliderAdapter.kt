package com.example.mmitraprogramteam.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mmitraprogramteam.R
import com.smarteist.autoimageslider.SliderViewAdapter

 class SliderAdapter (private val context: Context) :
        SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
        private var mCount: Int = 0

        fun setCount(count: Int) {
            this.mCount = count
        }

        override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
            val inflate =
                LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
            return SliderAdapterVH(inflate)
        }

        override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {


            viewHolder.itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    "This is item in position $position",
                    Toast.LENGTH_SHORT
                ).show()
            }


            when (position) {
                0 -> {
                    viewHolder.textViewDescription.text = "This is slider item $position"
                    viewHolder.textViewDescription.textSize = 16f
                    viewHolder.textViewDescription.setTextColor(Color.WHITE)
                    viewHolder.imageGifContainer.visibility = View.GONE
                    Glide.with(viewHolder.itemView)
                        .load(R.drawable.image1)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground)
                }
                2 -> {
                    viewHolder.textViewDescription.text = "This is slider item $position"
                    viewHolder.textViewDescription.textSize = 16f
                    viewHolder.textViewDescription.setTextColor(Color.WHITE)
                    viewHolder.imageGifContainer.visibility = View.GONE
                    Glide.with(viewHolder.itemView)
                        .load(R.drawable.imag2)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground)
                }
                4 -> {
                    viewHolder.textViewDescription.text = "This is slider item $position"
                    viewHolder.textViewDescription.textSize = 16f
                    viewHolder.textViewDescription.setTextColor(Color.WHITE)
                    viewHolder.imageGifContainer.visibility = View.GONE
                    Glide.with(viewHolder.itemView)
                        .load(R.drawable.image3)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground)
                }
                else -> {
                    viewHolder.textViewDescription.textSize = 29f
                    viewHolder.textViewDescription.setTextColor(Color.WHITE)
                    viewHolder.textViewDescription.text = "Ohhhh! look at this!"
                    viewHolder.imageGifContainer.visibility = View.VISIBLE
                    Glide.with(viewHolder.itemView)
                        .load(R.drawable.image1)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground)
                    Glide.with(viewHolder.itemView)
                        .asGif()
                        .load(R.drawable.imag2)
                        .into(viewHolder.imageGifContainer)
                }
            }

        }

        override fun getCount(): Int {
            //slider view count could be dynamic size
            return mCount
        }

        inner class SliderAdapterVH(var itemView: View) :
            SliderViewAdapter.ViewHolder(itemView) {
            var imageViewBackground: ImageView
            var imageGifContainer: ImageView
            var textViewDescription: TextView

            init {
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
                imageGifContainer = itemView.findViewById(R.id.iv_gif_container)
                textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider)
            }
        }


    }
