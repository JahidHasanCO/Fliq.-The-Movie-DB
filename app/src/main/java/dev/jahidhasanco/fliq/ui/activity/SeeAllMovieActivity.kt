package dev.jahidhasanco.fliq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.PopularMovieAdapter
import dev.jahidhasanco.fliq.ui.adapter.SeeAllMovieAdapter
import dev.jahidhasanco.fliq.ui.adapter.silder.MovieSliderAdapter
import org.w3c.dom.Text

class SeeAllMovieActivity : AppCompatActivity() {

    private var comeFrom = ""

    lateinit var toolbar: Toolbar
    lateinit var collapsingToolbar_seeAllMovies: CollapsingToolbarLayout
    lateinit var recyclerView_seeAllMovies: RecyclerView

    lateinit var seeAllMovieAdapter: SeeAllMovieAdapter
    lateinit var movieViewModel: MovieViewModel

    lateinit var textTitle_seeAllMovies: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_movie)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        comeFrom = intent.getStringExtra("ComeFrom").toString()
        toolbar = findViewById(R.id.toolbar_seeAllMovies)
        collapsingToolbar_seeAllMovies = findViewById(R.id.collapsingToolbar_seeAllMovies)
        textTitle_seeAllMovies = findViewById(R.id.textTitle_seeAllMovies)
        recyclerView_seeAllMovies = findViewById(R.id.recyclerView_seeAllMovies)
        setSupportActionBar(toolbar)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)


        when(comeFrom){
            "PopularMovies" -> {
                collapsingToolbar_seeAllMovies.title = "Popular Movies"
                textTitle_seeAllMovies.text = "Popular Movies"
                movieViewModel.getPopularMovies("",1)
                observeViewModel()
            }
            else -> {
                collapsingToolbar_seeAllMovies.title = ""
                textTitle_seeAllMovies.text = ""
            }

        }


    }

    private fun observeViewModel() {


        if(comeFrom == "PopularMovies"){
            getPopularMovies()
        }


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


//                animationView_movieFragment.visibility = if(it) View.VISIBLE else View.GONE
//                if(it) {
//                    image_slider_movieFragment.visibility = View.GONE
//                    popular_MovieLayout_movieFrag.visibility = View.GONE
//                    topRated_MovieLayout_movieFrag.visibility = View.GONE
//                }

            }
        })
    }

    private fun getPopularMovies() {
        movieViewModel.PopularMovies.observe(this, Observer {movies ->
            movies?.let {

                seeAllMovieAdapter = SeeAllMovieAdapter(this,it)
                recyclerView_seeAllMovies.apply {
                    adapter = seeAllMovieAdapter
                    layoutManager = GridLayoutManager(this@SeeAllMovieActivity,3)
                    setHasFixedSize(false)

                }


            }

        })
    }


}