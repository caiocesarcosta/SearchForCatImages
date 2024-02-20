package com.example.searchforcatimages.ui.viewmodel

import ResultState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchforcatimages.data.model.CatImageModel
import com.example.searchforcatimages.data.remote.ImgurRepository
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainActivityViewModel : ViewModel() {
    private var _imgurRepositories = MutableLiveData<ResultState<List<CatImageModel>?>?>()
    val imgurRepositories: MutableLiveData<ResultState<List<CatImageModel>?>?> = _imgurRepositories

    private val job by lazy { Job() }
    private val scope = CoroutineScope(Dispatchers.IO + job)


    fun loadImgsFromApi() {
        viewModelScope.launch {
            try {
                _imgurRepositories.value = ResultState.Success(fetchImagesFromApi())
            } catch (exception: HttpException) {
                _imgurRepositories.value = ResultState.Error(exception, "Ocorreu um erro ao buscar as imagens de gato.")


            } catch (exception: Exception) {
                _imgurRepositories.value = ResultState.Error(exception)
            }
        }
    }

    private suspend fun fetchImagesFromApi(): List<CatImageModel>? {
        val clientId = "1ceddedc03a5d71"
        val authHeader = "Client-ID $clientId"
        val response = ImgurRepository().searchCatImages(authHeader, "cats")
        return response?.map { it.toCatImg() }
    }
}