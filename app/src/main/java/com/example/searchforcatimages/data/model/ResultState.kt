import com.example.searchforcatimages.data.model.CatImageModel

sealed class ResultState {
    data class Success(val catImages: List<CatImageModel>): ResultState()
    data class Loading(val message: String? = "Loading...") : ResultState()
    data class Error(val exception: Throwable, val message: String) : ResultState()

}