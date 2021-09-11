package dev.jahidhasanco.fliq.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.model.movie.Result
import dev.jahidhasanco.fliq.data.utils.Constants
import dev.jahidhasanco.fliq.data.utils.Util


class PopularMovieAdapter(val ctx: Context, val movies: List<Result>) :
    RecyclerView.Adapter<PopularMovieAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.single_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val movie: Result = movies[position]

        Glide
            .with(ctx)
            .load(Util.posterUrlMake(movie.posterPath))
            .into(viewHolder.poster)


        viewHolder.movieTitle.text = movie.title
        viewHolder.releaseDate.text = movie.releaseDate
        viewHolder.genre1.text = Constants.getGenre(movie.genreIds[0])

        if (movie.genreIds.size > 1) {
            viewHolder.genre2.text = Constants.getGenre(movie.genreIds[1])
            viewHolder.genre2Layout.visibility = View.VISIBLE
        } else {
            viewHolder.genre2Layout.visibility = View.INVISIBLE
        }



//        Glide.with(ctx)
//            .load(Util.posterUrlMake(movie.posterPath))
//            .placeholder(R.drawable.poster_bg)
//            .into(object : CustomTarget<Drawable>(1080, 1080) {
//                override fun onResourceReady(
//                    resource: Drawable,
//                    transition: Transition<in Drawable>?
//                ) {
//                    viewHolder!!.poster.setImageDrawable(resource)
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//
//                }
//
//            })
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster = itemView.findViewById<ImageView>(R.id.imageView_single_movie)
        val movieTitle = itemView.findViewById<TextView>(R.id.title_single_movie)
        val releaseDate = itemView.findViewById<TextView>(R.id.date_single_movie)
        val genre1 = itemView.findViewById<TextView>(R.id.genre1_movie)
        val genre2 = itemView.findViewById<TextView>(R.id.genre2_movie)
        val genre2Layout = itemView.findViewById<LinearLayout>(R.id.genre2Layout_movie)
    }
}