package dev.jahidhasanco.movieapp.data.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.jahidhasanco.movieapp.R

//buildImageUrl(movie.posterPath!!)

class Util {
    fun ImageView.loadImage(uri: String?) {
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher_round)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load("http://image.tmdb.org/t/p/w342$uri")
            .into(this)
    }
}

