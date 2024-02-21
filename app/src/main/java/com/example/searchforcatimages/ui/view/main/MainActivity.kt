package com.example.searchforcatimages.ui.view.main


import ResultState
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchforcatimages.databinding.ActivityMainBinding
import com.example.searchforcatimages.ui.adapter.ImageAdapter
import com.example.searchforcatimages.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainActivityViewModel by viewModels()

//    private val adapter =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.loadImgsFromApi()

        setupUI()
        observeResults()
    }

    private fun setupUI() {
//        binding.progressBar.isVisible = false

    }

    private fun observeResults() {
        mainViewModel.imgurRepositories.observe(this@MainActivity, Observer { state ->
            when (state) {
                is ResultState.Success -> {
//                    binding.progressBar.isVisible = false

                    binding.recyclerView.layoutManager = LinearLayoutManager(this)
                    binding.recyclerView.adapter = ImageAdapter(this, state.catImages)

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

    private  fun extractUrlsFromString(input: List<Any?>): List<String> {
        val regex = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()


        val teste = regex.findAll(input.toString()).map { it.value

        }.toList()

        teste.forEach {
            println(it)
        }

        return teste
    }
}