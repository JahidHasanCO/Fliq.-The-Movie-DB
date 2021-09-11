package dev.jahidhasanco.fliq.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.silder.MovieSliderAdapter


class MovieFragment : Fragment() {

    lateinit var image_slider_movieFragment: SliderView

    lateinit var movieSliderAdapter: MovieSliderAdapter
    lateinit var movieViewModel: MovieViewModel
    lateinit var animationView_movieFragment: LottieAnimationView
    lateinit var popularMovieRecView_moviesFragment: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie, container, false)

        image_slider_movieFragment = view.findViewById(R.id.image_slider_movieFragment)
        animationView_movieFragment = view.findViewById(R.id.animationView_movieFragment)
        popularMovieRecView_moviesFragment = view.findViewById(R.id.popularMovieRecView_moviesFragment)

        hideLayout()

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.refresh()

        observeViewModel()
        showLayout()
        return view
    }

    private fun hideLayout() {
        animationView_movieFragment.playAnimation()
        image_slider_movieFragment.visibility = View.GONE
        animationView_movieFragment.visibility = View.VISIBLE
    }

    private fun showLayout(){
        animationView_movieFragment.pauseAnimation()
        animationView_movieFragment.visibility = View.GONE
        image_slider_movieFragment.visibility = View.VISIBLE
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