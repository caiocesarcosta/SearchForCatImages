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

    suspend fun searchCatImages(authHeader: String, s: String): List<CatImageModel> {
        val authHeader = authHeader
        val response = apiService.searchCatImages(authHeader, s)

        return response.data

    }
}