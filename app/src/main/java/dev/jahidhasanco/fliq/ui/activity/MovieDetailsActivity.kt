package dev.jahidhasanco.fliq.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.utils.Constants
import dev.jahidhasanco.fliq.data.utils.Util
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.PopularMovieAdapter
import dev.jahidhasanco.fliq.ui.adapter.silder.MovieSliderAdapter

class MovieDetailsActivity : AppCompatActivity() {

    var movieId: String = ""
    lateinit var movieViewModel: MovieViewModel

    lateinit var title_single_movie_Details: TextView
    lateinit var adultCheck_movie_slider: TextView
    lateinit var date_single_movie_Details: TextView
    lateinit var genre1_movie_Details: TextView
    lateinit var genre2_movie_Details: TextView
    lateinit var movieOverview_MovieDetails: TextView
    lateinit var imageView_single_movie_Details: ImageView
    lateinit var genre2Layout_movie_Details: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        val backBtn_movie_Details: ImageView = findViewById(R.id.backBtn_movie_Details)
        title_single_movie_Details = findViewById(R.id.title_single_movie_Details)
        imageView_single_movie_Details = findViewById(R.id.imageView_single_movie_Details)
        adultCheck_movie_slider = findViewById(R.id.adultCheck_movie_slider)
        date_single_movie_Details = findViewById(R.id.date_single_movie_Details)
        genre1_movie_Details = findViewById(R.id.genre1_movie_Details)
        genre2_movie_Details = findViewById(R.id.genre2_movie_Details)
        genre2Layout_movie_Details = findViewById(R.id.genre2Layout_movie_Details)
        movieOverview_MovieDetails = findViewById(R.id.movieOverview_MovieDetails)

        movieId = intent.getStringExtra("MovieIdPass").toString()
        Toast.makeText(this,"Id: $movieId",Toast.LENGTH_SHORT).show()

        backBtn_movie_Details.setOnClickListener {
            onBackPressed()
        }


        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovieDetails(movieId, "en-US")
        observeViewModel()

    }


    private fun observeViewModel() {

        movieViewModel.MovieDetails.observe(this, Observer { movie ->
            movie?.let {
//                usersList.visibility = View.VISIBLE
//                userListAdapter.updateCountries(it)
//                Log.d("JAHIDHASAN", "Succes to get $it")
//               movies.addAll(it)
                Log.d("JAHIDHASAN", "Success to get $it")
                title_single_movie_Details.text = it.title

                Glide
                    .with(this)
                    .load(Util.posterUrlMake(it.posterPath))
                    .into(imageView_single_movie_Details)

                if(it.adult){
                    adultCheck_movie_slider.text = "18+"
                }
                else{
                    adultCheck_movie_slider.text = "13+"
                }

                date_single_movie_Details.text = it.releaseDate
                movieOverview_MovieDetails.text = it.overview

                genre1_movie_Details.text = Constants.getGenre(it.genres[0].id)
                if(it.genres.size > 1){
                    genre2_movie_Details.text = Constants.getGenre(it.genres[1].id)
                    genre2Layout_movie_Details.visibility = View.VISIBLE
                }else{
                    genre2Layout_movie_Details.visibility = View.INVISIBLE
                }



            }

        })


        movieViewModel.movieLoadError.observe(this, Observer { isError ->


//            if(isError == "" || isError == null){
//                noInternet_Layout_movieFragment.visibility =  View.GONE
//                image_slider_movieFragment.visibility = View.VISIBLE
//                popular_MovieLayout_movieFrag.visibility = View.VISIBLE
//                topRated_MovieLayout_movieFrag.visibility = View.VISIBLE
//            }else{
//                noInternet_Layout_movieFragment.visibility =  View.VISIBLE
//                image_slider_movieFragment.visibility = View.INVISIBLE
//                popular_MovieLayout_movieFrag.visibility = View.INVISIBLE
//                topRated_MovieLayout_movieFrag.visibility = View.INVISIBLE
//            }

        })
        movieViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {

//
//                animationView_movieFragment.visibility = if(it) View.VISIBLE else View.GONE
//                if(it) {
//                    image_slider_movieFragment.visibility = View.GONE
//                    popular_MovieLayout_movieFrag.visibility = View.GONE
//                    topRated_MovieLayout_movieFrag.visibility = View.GONE
//                }

            }
        })
    }


}