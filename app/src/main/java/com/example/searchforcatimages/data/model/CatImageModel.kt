package com.example.searchforcatimages.data.model

data class CatImageModel(
    val id: String,
    val title: String?,
    val description: String?,
    val name: String?,
    val link: String?
) {
    fun toCatImage() {
        // Atualiza o objeto existente
        title?.trim()
        description?.trim()
        name?.trim()
        link?.trim()
    }
}




