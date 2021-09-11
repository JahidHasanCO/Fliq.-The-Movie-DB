package dev.jahidhasanco.fliq.data.utils

//buildImageUrl(movie.posterPath!!)

object Util {

    fun posterUrlMake(uri: String?):String {
       return "https://image.tmdb.org/t/p/w342$uri"

    }



}

