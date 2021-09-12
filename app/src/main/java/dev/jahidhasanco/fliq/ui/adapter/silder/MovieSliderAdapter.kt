package dev.jahidhasanco.fliq.ui.adapter.silder

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.smarteist.autoimageslider.SliderViewAdapter
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.model.movie.Result
import dev.jahidhasanco.fliq.data.utils.Constants
import dev.jahidhasanco.fliq.data.utils.Helper
import dev.jahidhasanco.fliq.data.utils.Util
import dev.jahidhasanco.fliq.ui.activity.MovieDetailsActivity


public class MovieSliderAdapter(val ctx :Context , val movies : List<Result>):
    SliderViewAdapter<MovieSliderAdapter.MyViewHolder>() {


    override fun getCount(): Int {
        return 5
    }

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.single_movie_slider, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder?, position: Int) {
        val movie: Result = movies[position]

        Glide
            .with(ctx)
            .load(Util.posterUrlMake(movie.posterPath))
            .into(viewHolder!!.poster)

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



        if(Helper.CompareDate(movie.releaseDate) == 1){
            viewHolder!!.titleBig.text = "New Movies"
        }else if(Helper.CompareDate(movie.releaseDate) == 2){
            viewHolder!!.titleBig.text = "Upcoming Movies"
        }
        if(movie.adult){
            viewHolder.adultCheck.text = "18+"
        }
        else{
            viewHolder.adultCheck.text = "13+"
        }

        viewHolder!!.movieTitle.text = movie.title
        viewHolder.releaseDate.text = movie.releaseDate
        viewHolder.genre1.text = Constants.getGenre(movie.genreIds[0])


        if(movie.genreIds.size > 1){
            viewHolder.genre2.text = Constants.getGenre(movie.genreIds[1])
            viewHolder.genre2Layout.visibility = View.VISIBLE
        }else{
            viewHolder.genre2Layout.visibility = View.INVISIBLE
        }



        viewHolder.itemView.setOnClickListener {
            val intent = Intent(ctx, MovieDetailsActivity::class.java)
            val movieId:String = movie.id.toString()
            intent.putExtra("MovieIdPass",movieId)
            ctx.startActivity(intent)
        }


    }


    class MyViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        val poster = itemView.findViewById<ImageView>(R.id.imageView_single_movie_slider)
        val movieTitle = itemView.findViewById<TextView>(R.id.title_single_movie_slider)
        val titleBig = itemView.findViewById<TextView>(R.id.titleBig_single_movie_slider)
        val releaseDate = itemView.findViewById<TextView>(R.id.date_single_movie_slider)
        val genre1 = itemView.findViewById<TextView>(R.id.genre1_movie_slider)
        val genre2 = itemView.findViewById<TextView>(R.id.genre2_movie_slider)
        val genre2Layout = itemView.findViewById<LinearLayout>(R.id.genre2Layout_movie_slider)
        val adultCheck = itemView.findViewById<TextView>(R.id.adultCheck_movie_slider)
    }
}