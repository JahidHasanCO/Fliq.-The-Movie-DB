package dev.jahidhasanco.fliq.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.PopularMovieAdapter
import dev.jahidhasanco.fliq.ui.adapter.silder.MovieSliderAdapter

class MovieDetailsActivity : AppCompatActivity() {

    var movieId: String = ""
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        val backBtn_movie_Details: ImageView = findViewById(R.id.backBtn_movie_Details)

        movieId = intent.getStringExtra("MovieIdPass").toString()

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