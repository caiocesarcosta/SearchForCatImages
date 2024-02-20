package com.example.searchforcatimages.ui.view.main


import ResultState
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchforcatimages.data.model.CatImageModel
import com.example.searchforcatimages.databinding.ActivityMainBinding
import com.example.searchforcatimages.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainActivityViewModel by viewModels()

    private val adapter = ImageAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeResults()
    }

    private fun setupUI() {
//        binding.progressBar.isVisible = false
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeResults() {
        mainViewModel.imgurRepositories.observe(this@MainActivity, Observer { state ->
            when (state) {
                is ResultState.Success -> {
//                    binding.progressBar.isVisible = false

//                    adapter.submitList(state.data)
                }
                is ResultState.Error -> {
                    Log.e("MainActivity", "Erro")
//                    binding.progressBar.isVisible = false
                }
                is ResultState.Loading -> {
//                    binding.progressBar.isVisible = true
                }

                else -> {

                }
            }
        })
    }

    inner class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

        private val items = ArrayList<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(android.R.layout.simple_list_item_1, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        fun submitList(urls: List<CatImageModel>) {
            items.clear()
            items.addAll(extractUrlsFromString(urls))
            notifyDataSetChanged()
        }

        private  fun extractUrlsFromString(input: List<Any?>): List<String> {
            val regex = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()
            return regex.findAll(input.toString()).map { it.value }.toList()
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
            startActivity(intent)
        }
    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                mainViewModel.loadImgsFromApi()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }*/
}