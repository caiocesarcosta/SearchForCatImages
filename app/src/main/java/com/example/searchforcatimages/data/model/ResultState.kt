import com.example.searchforcatimages.data.model.CatImageModel

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<List<CatImageModel>?>()
    data class Loading(val message: String? = "Loading...") : ResultState<Nothing>()
    data class Error(val exception: Throwable, val message: String? = exception.localizedMessage) : ResultState<Nothing>()
}