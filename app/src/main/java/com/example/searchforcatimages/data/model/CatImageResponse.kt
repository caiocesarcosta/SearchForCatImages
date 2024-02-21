package com.example.searchforcatimages.data.model
import com.google.gson.annotations.SerializedName

data class CatImageResponse(
    @SerializedName("data") val data: List<CatImageModel>,
    val success: Boolean,
    val status: Int
)
