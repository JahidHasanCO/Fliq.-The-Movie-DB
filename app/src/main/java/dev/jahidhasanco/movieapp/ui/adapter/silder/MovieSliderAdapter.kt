package dev.jahidhasanco.movieapp.ui.adapter.silder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.data.model.movie.Result
import dev.jahidhasanco.movieapp.data.utils.Util


public class MovieSliderAdapter(val ctx :Context , val movies : List<Result>):
    SliderViewAdapter<MovieSliderAdapter.MyViewHolder>() {


    override fun getCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.single_movie_slider, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder?, position: Int) {
        val movie: Result = movies[position]
        Glide.with(ctx)
            .load(Util.posterUrlMake(movie.posterPath))
            .into(viewHolder!!.poster)
        viewHolder.movieTitle.text = movie.title
        viewHolder.releaseDate.text = movie.releaseDate
    }


    class MyViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        val poster = itemView.findViewById<ImageView>(R.id.imageView_single_movie_slider)
        val movieTitle = itemView.findViewById<TextView>(R.id.title_single_movie_slider)
        val releaseDate = itemView.findViewById<TextView>(R.id.date_single_movie_slider)
    }
}