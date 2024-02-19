package com.example.searchforcatimages.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchforcatimages.R


class ImageAdapter {
    class ImageAdapter(private val context: Context, private val images: List<String>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.image_view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val imageUrl = images[position]
            Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView)
        }

        override fun getItemCount(): Int = images.size
    }
}