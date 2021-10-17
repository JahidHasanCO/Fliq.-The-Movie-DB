package dev.jahidhasanco.fliq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar


import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.SeeAllMovieAdapter


import dev.jahidhasanco.fliq.R
import kotlin.math.log


class SeeAllMovieActivity : AppCompatActivity() {

    private var comeFrom = ""

    lateinit var toolbar_seeAllMovies: MaterialToolbar

    lateinit var recyclerView_seeAllMovies: RecyclerView

    lateinit var seeAllMovieAdapter: SeeAllMovieAdapter
    lateinit var movieViewModel: MovieViewModel
    lateinit var layoutManagerGrid: GridLayoutManager

    var page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_movie)


        comeFrom = intent.getStringExtra("ComeFrom").toString()

        toolbar_seeAllMovies = findViewById(R.id.toolbar_seeAllMovies)

        setSupportActionBar(toolbar_seeAllMovies)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar_seeAllMovies.setTitleTextColor(resources.getColor(R.color.white))
        toolbar_seeAllMovies.setNavigationIconTint(resources.getColor(R.color.white))

        layoutManagerGrid = GridLayoutManager(this@SeeAllMovieActivity,3)
        recyclerView_seeAllMovies = findViewById(R.id.recyclerView_seeAllMovies)




        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        seeAllMovieAdapter = SeeAllMovieAdapter(this)
        recyclerView_seeAllMovies.apply {
            adapter = seeAllMovieAdapter
            layoutManager = layoutManagerGrid
            setHasFixedSize(false)

        }

        when(comeFrom){

            "PopularMovies" -> {

                toolbar_seeAllMovies.title =  "Popular Movies"
                movieViewModel.getPopularMovies("",page)
                observeViewModel()
            }
            "TopRatedMovies" -> {

                toolbar_seeAllMovies.title =  "Top Rated Movies"
                movieViewModel.getTopRatedMovies("",page)
                observeViewModel()
            }
            else -> {
                toolbar_seeAllMovies.title =  ""
            }

        }

//        recyclerView_seeAllMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (layoutManagerGrid.childCount + layoutManagerGrid.findFirstVisibleItemPosition()
//                    >= layoutManagerGrid.itemCount - 2) {  //if near fifth item from end
//                    page++
//                    getPagedData()
//                }
//            }
//
//
//
//        })

//        recyclerView_seeAllMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//
//                    var visibleitem = layoutManagerGrid.childCount
//                    var pastvisibleitem = layoutManagerGrid.findFirstCompletelyVisibleItemPosition()
//                    var total = 20
//
//                    if(visibleitem + pastvisibleitem >= total){
//                        page++
//                        movieViewModel.getPopularMovies("",page)
//
//                        observeViewModel()
//                    }
//
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })



    }

    private fun getPagedData(){
        when(comeFrom){
            "PopularMovies" -> {

                toolbar_seeAllMovies.title =  "Popular Movies"
                movieViewModel.getPopularMovies("",page)
                observeViewModel()
            }
            else -> {
                toolbar_seeAllMovies.title =  ""
            }

        }
    }
    private fun observeViewModel() {


        if(comeFrom == "PopularMovies"){
            getPopularMovies()
        }
        else if(comeFrom == "TopRatedMovies"){
            getTopRatedMovies()
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

    private fun getTopRatedMovies() {

        movieViewModel.TopRatedMovies.observe(this, Observer {movies ->
            movies?.let {
                seeAllMovieAdapter.addMovie(it)
            }

        })
    }

    private fun getPopularMovies() {
        movieViewModel.PopularMovies.observe(this, Observer {movies ->
            movies?.let {
                seeAllMovieAdapter.addMovie(it)
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

}