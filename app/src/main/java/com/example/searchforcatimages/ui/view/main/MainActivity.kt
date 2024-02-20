package com.example.searchforcatimages.ui.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchforcatimages.R
import com.example.searchforcatimages.R.id.recycler_view
import com.example.searchforcatimages.ui.adapter.ImageAdapter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Certifique-se de que activity_main.xml é o layout correto
        val images = listOf("https://i.imgur.com/NCcUy3B.jpeg", "https://i.imgur.com/POR9ObR.jpeg")
        val recyclerView = findViewById<RecyclerView>(recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ImageAdapter.ImageAdapter(this, images)
    }
}
