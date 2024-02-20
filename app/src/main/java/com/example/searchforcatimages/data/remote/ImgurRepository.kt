package com.example.searchforcatimages.data.remote

import com.example.searchforcatimages.data.model.CatImageModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ImgurRepository {
    private val apiService: ImgurApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ImgurApiService::class.java)
    }

    suspend fun searchCatImages(authHeader: String, s: String): List<CatImageModel>? {
        val clientId = "1ceddedc03a5d71"
        val authHeader = "Client-ID $clientId"
        val response = apiService.searchCatImages(authHeader, "cats").data

        return response.map { item ->
            CatImageModel(
                id = item.id,
                title = item.title,
                description = item.description,
                name = item.name,
                link = item.link
            )
        }
    }
}




