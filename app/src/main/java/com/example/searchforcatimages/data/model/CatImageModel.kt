package com.example.searchforcatimages.data.model

data class CatImageModel(
    val id: String,
    val title: String?,
    val description: String?,
    val name: String?,
    val link: String?
) {
    fun toCatImg()=  CatImageModel(
            id = id,
            title = title,
            description = description,
            name = name,
            link = link
        )
    }




