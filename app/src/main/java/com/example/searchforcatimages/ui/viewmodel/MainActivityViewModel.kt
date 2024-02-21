package com.example.searchforcatimages.ui.viewmodel

import ResultState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchforcatimages.data.model.CatImageModel
import com.example.searchforcatimages.data.model.CatImageResponse
import com.example.searchforcatimages.data.remote.ImgurRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivityViewModel : ViewModel() {
//    private var _imgurRepositories = MutableLiveData<ResultState>()
//    val imgurRepositories = MutableLiveData<ResultState>()

    private var _imgurRepositories = MutableLiveData<ResultState>()
    val imgurRepositories: LiveData<ResultState> = _imgurRepositories

    private val job by lazy { Job() }
    private val scope = CoroutineScope(Dispatchers.IO + job)


    fun loadImgsFromApi() {
        viewModelScope.launch {
            try {
                _imgurRepositories.value = fetchImagesFromApi()
            } catch (exception: IOException) {
                _imgurRepositories.value =
                    ResultState.Error(exception, "Ocorreu um erro ao buscar as imagens de gato.")
            } catch (exception: IOException) {
                _imgurRepositories.value = ResultState.Error(exception, "Error!!")
            }
        }
    }

    private suspend fun fetchImagesFromApi(): ResultState {
        return withContext(Dispatchers.IO) {
            try {
                val clientId = "1ceddedc03a5d71"
                val authHeader = "Client-ID $clientId"
                val response = ImgurRepository().searchCatImages(authHeader, "cats")

                return@withContext ResultState.Success(response.data)

            } catch (e: IOException) {
                return@withContext ResultState.Error(
                    e,
                    "Erro de conexão ao buscar as imagens de gato. Verifique sua conexão com a internet e tente novamente."
                )
            } catch (e: Exception) {
                return@withContext ResultState.Error(
                    e,
                    "Ocorreu um erro ao buscar as imagens de gato."
                )
            }
        }
    }


}

