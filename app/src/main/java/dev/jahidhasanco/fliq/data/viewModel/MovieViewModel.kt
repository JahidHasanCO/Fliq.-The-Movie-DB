package dev.jahidhasanco.fliq.data.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.jahidhasanco.fliq.data.model.movie.Result
import dev.jahidhasanco.fliq.data.model.movie.movieCredit.Cast
import dev.jahidhasanco.fliq.data.model.movie.movieCredit.Crew
import dev.jahidhasanco.fliq.data.model.movie.movieCredit.MovieCredit
import dev.jahidhasanco.fliq.data.model.movie.movieDetails.MovieDetails
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
    val TopRatedMovies = MutableLiveData<List<Result>>()
    val MovieDetails = MutableLiveData<MovieDetails>()
    val MovieCast = MutableLiveData<List<Cast>>()
    val MovieCrew = MutableLiveData<List<Crew>>()
    val movieLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchUpcomingMovies()
        fetchPopularMovies()
        fetchTopRatedMovies()
    }

    fun getMovieDetails(movieId : String,language: String) {
        fetchMovieDetails(movieId,language)
    }

    fun getMovieCredit(movieId : String,language: String) {
        fetchMovieCredits(movieId,language)
    }

    private fun fetchMovieCredits(movieId: String, language: String) {

        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getMovieCredit(movieId,language)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    MovieCast.value = response.body()!!.cast
                    MovieCrew.value = response.body()!!.crew
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

    private fun fetchMovieDetails(movieId: String,language: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getMovieDetails(movieId,language)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    MovieDetails.value = response.body()
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

    private fun fetchTopRatedMovies() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getTopRatedMovies("",1)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    TopRatedMovies.value = response.body()?.results
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

        GlobalScope.launch {
            withContext(Dispatchers.Main){
                movieLoadError.value = message
                loading.value = false
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}