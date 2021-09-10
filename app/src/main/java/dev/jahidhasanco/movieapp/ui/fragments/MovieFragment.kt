package dev.jahidhasanco.movieapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.data.viewModel.MovieViewModel
import dev.jahidhasanco.movieapp.ui.adapter.silder.MovieSliderAdapter


class MovieFragment : Fragment() {

    lateinit var image_slider_movieFragment: SliderView

    lateinit var movieSliderAdapter: MovieSliderAdapter
    lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie, container, false)

        image_slider_movieFragment = view.findViewById(R.id.image_slider_movieFragment)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.refresh()

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        movieViewModel.upComingMovies.observe(requireActivity(), Observer {countries ->
            countries?.let {
//                usersList.visibility = View.VISIBLE
//                userListAdapter.updateCountries(it)
//                Log.d("JAHIDHASAN", "Succes to get $it")
//               movies.addAll(it)

                movieSliderAdapter = MovieSliderAdapter(requireActivity(),it)
                image_slider_movieFragment.setSliderAdapter(movieSliderAdapter)
                image_slider_movieFragment.setIndicatorAnimation(IndicatorAnimationType.WORM)
                image_slider_movieFragment.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
                image_slider_movieFragment.startAutoCycle()


            }

        })
        movieViewModel.movieLoadError.observe(requireActivity(), Observer { isError ->

//            listError.visibility = if(isError == "") View.GONE else View.VISIBLE
            Log.d("JAHIDHASAN","Error to get $isError")

        })
        movieViewModel.loading.observe(requireActivity(), Observer { isLoading ->
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