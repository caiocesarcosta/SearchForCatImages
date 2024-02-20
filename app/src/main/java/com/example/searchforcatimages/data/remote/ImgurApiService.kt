package com.example.searchforcatimages.data.remote

import com.example.searchforcatimages.data.model.CatImageResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImgurApiService {
    @GET("3/gallery/search")
    suspend fun searchCatImages(
        @Header("Authorization") authHeader: String,
        @Query("q") query: String
    ): CatImageResponse


}