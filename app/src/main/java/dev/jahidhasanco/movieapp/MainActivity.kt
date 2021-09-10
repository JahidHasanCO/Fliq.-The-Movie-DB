package dev.jahidhasanco.movieapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.jahidhasanco.movieapp.data.viewModel.MovieViewModel


class MainActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.refresh()

//        usersList.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = userListAdapter
//        }
        observeViewModel()
    }

    private fun observeViewModel() {
        movieViewModel.upComingMovies.observe(this, Observer {countries ->
            countries?.let {
//                usersList.visibility = View.VISIBLE
//                userListAdapter.updateCountries(it)
                Log.d("JAHIDHASAN", "Succes to get $it")
                           }
        })
        movieViewModel.movieLoadError.observe(this, Observer { isError ->

//            listError.visibility = if(isError == "") View.GONE else View.VISIBLE
            Log.d("JAHIDHASAN","Error to get $isError")

        })
        movieViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {

//                loadingView.visibility = if(it) View.VISIBLE else View.GONE
//                if(it) {
//                    listError.visibility = View.GONE
//                    usersList.visibility = View.GONE
//                }
//
            }
        })
    }
}