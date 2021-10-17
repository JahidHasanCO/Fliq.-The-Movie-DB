package dev.jahidhasanco.fliq.data.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.jahidhasanco.fliq.data.model.movie.Result
import dev.jahidhasanco.fliq.data.model.movie.YoutubeTrailer.MovieTrailer
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
    var popullarMoviesTotalResults = -1
    val TopRatedMovies = MutableLiveData<List<Result>>()
    val MovieDetails = MutableLiveData<MovieDetails>()
    val MovieCast = MutableLiveData<List<Cast>>()
    val MovieCrew = MutableLiveData<List<Crew>>()
    val MovieTrailer = MutableLiveData<MovieTrailer>()
    val movieLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchUpcomingMovies()
        fetchTopRatedMovies("",1)
    }

    fun getPopularMovies(language: String,page : Int){
        fetchPopularMovies(language,page)
    }
    fun getTopRatedMovies(language: String,page : Int){
        fetchTopRatedMovies(language,page)
    }

    fun getMovieDetails(movieId : String,language: String) {
        fetchMovieDetails(movieId,language)
    }

    fun getMovieCredit(movieId : String,language: String) {
        fetchMovieCredits(movieId,language)
    }

    fun getMovieTrailer(movieId : String,language: String) {
        fetchMovieTrailer(movieId,language)
    }

    private fun fetchMovieTrailer(movieId: String, language: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getMovieTrailer(movieId,language)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    MovieTrailer.value = response.body()
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

    private fun fetchPopularMovies(language: String,page : Int){
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getPopularMovies(language,page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    PopularMovies.value = response.body()?.results
                    popullarMoviesTotalResults = response.body()!!.totalResults
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

    private fun fetchTopRatedMovies(language: String,page : Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getTopRatedMovies(language, page)
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