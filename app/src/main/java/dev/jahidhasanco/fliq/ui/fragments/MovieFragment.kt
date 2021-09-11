package dev.jahidhasanco.fliq.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.PopularMovieAdapter
import dev.jahidhasanco.fliq.ui.adapter.silder.MovieSliderAdapter


class MovieFragment : Fragment() {

    lateinit var image_slider_movieFragment: SliderView

    lateinit var movieSliderAdapter: MovieSliderAdapter
    lateinit var popularMovieAdapter: PopularMovieAdapter

    lateinit var movieViewModel: MovieViewModel
    lateinit var animationView_movieFragment: LottieAnimationView
    lateinit var popularMovieRecView_moviesFragment: RecyclerView

    lateinit var noInternet_Layout_movieFragment: LinearLayout
    lateinit var popular_MovieLayout_movieFrag: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie, container, false)

        image_slider_movieFragment = view.findViewById(R.id.image_slider_movieFragment)
        animationView_movieFragment = view.findViewById(R.id.animationView_movieFragment)
        popularMovieRecView_moviesFragment = view.findViewById(R.id.popularMovieRecView_moviesFragment)
        noInternet_Layout_movieFragment = view.findViewById(R.id.noInternet_Layout_movieFragment)
        popular_MovieLayout_movieFrag = view.findViewById(R.id.popular_MovieLayout_movieFrag)


        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.refresh()

        observeViewModel()
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
        popular_MovieLayout_movieFrag.visibility = View.VISIBLE
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

        movieViewModel.PopularMovies.observe(requireActivity(), Observer {countries ->
            countries?.let {

                popularMovieAdapter = PopularMovieAdapter(requireActivity(),it)
                popularMovieRecView_moviesFragment.apply {
                    adapter = popularMovieAdapter
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    setHasFixedSize(false)
                    showLayout()
                }

            }

        })
        movieViewModel.movieLoadError.observe(requireActivity(), Observer { isError ->


            if(isError == "" || isError == null){
                noInternet_Layout_movieFragment.visibility =  View.GONE
                image_slider_movieFragment.visibility = View.VISIBLE
                popular_MovieLayout_movieFrag.visibility = View.VISIBLE
            }else{
                noInternet_Layout_movieFragment.visibility =  View.VISIBLE
                image_slider_movieFragment.visibility = View.INVISIBLE
                popular_MovieLayout_movieFrag.visibility = View.INVISIBLE
            }

        })
        movieViewModel.loading.observe(requireActivity(), Observer { isLoading ->
            isLoading?.let {


                animationView_movieFragment.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    image_slider_movieFragment.visibility = View.GONE
                    popular_MovieLayout_movieFrag.visibility = View.GONE
                }

            }
        })
    }


}