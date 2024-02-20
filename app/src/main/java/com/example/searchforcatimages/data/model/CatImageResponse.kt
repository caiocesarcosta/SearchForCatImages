package com.example.searchforcatimages.data.model

data class CatImageResponse(
    val data: List<CatImageModel>,
    val success: Boolean,
    val status: Int
)
