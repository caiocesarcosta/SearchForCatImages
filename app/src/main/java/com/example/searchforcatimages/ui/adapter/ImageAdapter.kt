package com.example.searchforcatimages.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchforcatimages.data.model.CatImageModel
import com.example.searchforcatimages.data.model.CatImageResponse


class ImageAdapter(private val context: Context, val list: List<CatImageModel>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

        private val items = ArrayList<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(android.R.layout.simple_list_item_1, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        fun submitList(imagens: List<CatImageModel>) {
            items.clear()
            items.addAll(extractUrlsFromString(imagens))
            notifyDataSetChanged()
        }

        private  fun extractUrlsFromString(input: List<Any?>): List<String> {
            val regex = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()


            val teste = regex.findAll(input.toString()).map { it.value

            }.toList()

            teste.forEach {
                println(it)
            }

            return teste
        }

        fun getLinkFromRetrofitResult(resp: CatImageResponse): List<CatImageModel> {

            val catImages = mutableListOf<CatImageModel>()
            resp.data.forEach { it ->
                catImages.add(it)
            }

            return catImages
        }


        inner class ViewHolder(layoutRes: Int, parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(context).inflate(layoutRes, parent, false)
        ) {

            private val textView = itemView.findViewById<View>(android.R.id.text1) as TextView

            fun bind(url: String) {
                textView.setOnClickListener { openBrowser(url) }
                textView.text = url
            }
        }

        private fun openBrowser(url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            context.startActivity(intent)
        }
    }


