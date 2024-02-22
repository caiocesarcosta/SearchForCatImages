package com.example.searchforcatimages.ui.view.main


import ImageAdapter
import ResultState
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.searchforcatimages.data.model.CatImageModel
import com.example.searchforcatimages.databinding.ActivityMainBinding
import com.example.searchforcatimages.ui.viewmodel.MainActivityViewModel
import com.example.searchforcatimages.util.CustomAppGlideModule

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainActivityViewModel by viewModels()

//    private val adapter = ImageAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Glide.initialize(this) {
//            it.disableDiskCache()
//            it.enableLogging()
//            it.preloaderPoolSize(5)
//            it.setDefaultRequestOptions {
//                 //placeholder(R.drawable.placeholder)
//                  //error(R.drawable.error)
//            }
//            it.setInitializer(CustomAppGlideModule())
//        }

        mainViewModel.loadImgsFromApi()

        setupUI()
        observeResults()
    }

    private fun setupUI() {
//      binding.progressBar.isVisible = false
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//      binding.recyclerView.adapter = ImageAdapter(this)

    }
    private fun observeResults() {
        mainViewModel.imgurRepositories.observe(this@MainActivity, Observer { state ->
            when (state) {
                is ResultState.Success -> {
//                    binding.progressBar.isVisible = false

                    binding.recyclerView.adapter =
                        ImageAdapter(this, getListLink(state.catImages))
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
    private fun getListLink(catImages: List<CatImageModel>): List<String> {
        return catImages.map {
            println(it.link)
            it.link
        }.filter { it.endsWith(".jpg") }
    }
}
