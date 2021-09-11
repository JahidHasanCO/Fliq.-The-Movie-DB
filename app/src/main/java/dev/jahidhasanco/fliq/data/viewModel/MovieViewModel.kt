package dev.jahidhasanco.fliq.data.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.jahidhasanco.fliq.data.model.movie.Result
import dev.jahidhasanco.fliq.data.network.RetrofitService
import kotlinx.coroutines.*

class MovieViewModel(): ViewModel() {

    val retrofitService = RetrofitService().getRetrofitService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val upComingMovies = MutableLiveData<List<Result>>()
    val PopularMovies = MutableLiveData<List<Result>>()
    val movieLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchUpcomingMovies()
        fetchPopularMovies()
    }

    private fun fetchUpcomingMovies() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getUpcomingMovies("",1)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    upComingMovies.value = response.body()?.results
                    movieLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
        movieLoadError.value = ""
        loading.value = false
    }

    private fun fetchPopularMovies(){
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getPopularMovies("",1)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    PopularMovies.value = response.body()?.results
                    movieLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
        movieLoadError.value = ""
        loading.value = false
    }
    private fun onError(message: String) {
        movieLoadError.value = message
        loading.value = false
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}