package dev.jahidhasanco.fliq.data.network

import dev.jahidhasanco.fliq.data.model.movie.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY = "86ab73a94cd4cfeaedc37fc0e2c0ed1a"

interface ApiService {

    @GET("3/movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(@Query("language") language: String,@Query("page") page : Int): Response<Movie>


    @GET("3/movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(@Query("language") language: String,@Query("page") page : Int): Response<Movie>


}

class RetrofitService {

    fun getRetrofitService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}