package dev.jahidhasanco.fliq.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.utils.Constants
import dev.jahidhasanco.fliq.data.utils.Util
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.MovieCastAdapter
import dev.jahidhasanco.fliq.ui.adapter.MovieCrewAdapter
import dev.jahidhasanco.fliq.ui.adapter.PopularMovieAdapter
import dev.jahidhasanco.fliq.ui.adapter.silder.MovieSliderAdapter
import com.github.ybq.android.spinkit.style.DoubleBounce

import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Wave


class MovieDetailsActivity : AppCompatActivity() {

    private var movieId: String = ""
    lateinit var movieViewModel: MovieViewModel

    lateinit var title_single_movie_Details: TextView
    lateinit var adultCheck_movie_slider: TextView
    lateinit var date_single_movie_Details: TextView
    lateinit var genre1_movie_Details: TextView
    lateinit var genre2_movie_Details: TextView
    lateinit var movieOverview_MovieDetails: TextView
    lateinit var popularity_movieDetails: TextView
    lateinit var imageView_single_movie_Details: ImageView
    lateinit var trailer_movieDetails: ImageView
    lateinit var genre2Layout_movie_Details: LinearLayout
    lateinit var progress_bar_MovieDetails: ProgressBar
    lateinit var castRecView_movieDetails: RecyclerView
    lateinit var crewRecView_movieDetails: RecyclerView
    lateinit var spin_kit_movieDetails: ProgressBar

    lateinit var movieCastAdapter: MovieCastAdapter
    lateinit var movieCrewAdapter: MovieCrewAdapter
    lateinit var linearLayout_movieTrailer: LinearLayout
    lateinit var adultCheckLayout_movieDetails: LinearLayout
    lateinit var linearLayout2_title_movieDetails: LinearLayout
    lateinit var descLayout: LinearLayout

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
        progress_bar_MovieDetails = findViewById(R.id.progress_bar_MovieDetails)
        popularity_movieDetails = findViewById(R.id.popularity_movieDetails)
        castRecView_movieDetails = findViewById(R.id.castRecView_movieDetails)
        crewRecView_movieDetails = findViewById(R.id.crewRecView_movieDetails)
        spin_kit_movieDetails = findViewById(R.id.spin_kit_movieDetails)
        linearLayout_movieTrailer = findViewById(R.id.linearLayout_movieTrailer)
        adultCheckLayout_movieDetails = findViewById(R.id.adultCheckLayout_movieDetails)
        linearLayout2_title_movieDetails = findViewById(R.id.linearLayout2_title_movieDetails)
        descLayout = findViewById(R.id.descLayout)
        trailer_movieDetails = findViewById(R.id.trailer_movieDetails)

        val doubleBounce: Sprite = Wave()
        spin_kit_movieDetails.indeterminateDrawable = doubleBounce
        hideAllLayouts()

        movieId = intent.getStringExtra("MovieIdPass").toString()

        backBtn_movie_Details.setOnClickListener {
            onBackPressed()
        }


        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovieDetails(movieId, "en-US")
        movieViewModel.getMovieCredit(movieId,"en-US")
        observeViewModel()

        trailer_movieDetails.setOnClickListener {
            val intent = Intent(this,YoutubeVideoPlayerActivity::class.java)
            intent.putExtra("MovieIdPass",movieId)
            startActivity(intent)
        }

        Handler().postDelayed({

            showAllLayouts()

        }, 600)


    }

    private fun hideAllLayouts() {
        spin_kit_movieDetails.visibility = View.VISIBLE
        imageView_single_movie_Details.visibility = View.INVISIBLE
        linearLayout_movieTrailer.visibility = View.INVISIBLE
        descLayout.visibility = View.INVISIBLE
        adultCheckLayout_movieDetails.visibility = View.INVISIBLE
        linearLayout2_title_movieDetails.visibility = View.INVISIBLE
    }

    private fun showAllLayouts() {
        spin_kit_movieDetails.visibility = View.INVISIBLE
        imageView_single_movie_Details.visibility = View.VISIBLE
        linearLayout_movieTrailer.visibility = View.VISIBLE
        descLayout.visibility = View.VISIBLE
        linearLayout2_title_movieDetails.visibility = View.VISIBLE
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

                genre1_movie_Details.text = it.genres[0].name
                if(it.genres.size > 1){
                    genre2_movie_Details.text = it.genres[1].name
                    genre2Layout_movie_Details.visibility = View.VISIBLE
                }else{
                    genre2Layout_movie_Details.visibility = View.INVISIBLE
                }


//                if(it.popularity.toInt() < 99){
//                    progress_bar_MovieDetails.progress = it.popularity.toInt()
//                    popularity_movieDetails.text = "Popularity $it.popularity.toInt()%"
//                }else{
//                    progress_bar_MovieDetails.progress = 100
//                    popularity_movieDetails.text = "Popularity 100%"
//                }
                progress_bar_MovieDetails.progress = (it.voteAverage * 10).toInt()
                popularity_movieDetails.text = "${it.voteAverage} Rating"
            }

        })


        movieViewModel.MovieCast.observe(this, Observer { cast ->

            cast?.let {

                movieCastAdapter = MovieCastAdapter(this,it)
                castRecView_movieDetails.apply {
                    adapter = movieCastAdapter
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    setHasFixedSize(false)
                }

            }

        })

        movieViewModel.MovieCrew.observe(this, Observer { crew ->

            crew?.let {

                movieCrewAdapter = MovieCrewAdapter(this,it)
                crewRecView_movieDetails.apply {
                    adapter = movieCrewAdapter
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    setHasFixedSize(false)
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
            }
        })
    }


}