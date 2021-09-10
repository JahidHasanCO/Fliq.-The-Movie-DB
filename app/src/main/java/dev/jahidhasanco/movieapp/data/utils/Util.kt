package dev.jahidhasanco.movieapp.data.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.jahidhasanco.movieapp.R

//buildImageUrl(movie.posterPath!!)

object Util {
    fun posterUrlMake(uri: String?):String {
       return "http://image.tmdb.org/t/p/w342$uri"

    }
}

