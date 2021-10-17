package dev.jahidhasanco.fliq.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Wave
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.activity.MovieDetailsActivity
import dev.jahidhasanco.fliq.ui.activity.SeeAllMovieActivity
import dev.jahidhasanco.fliq.ui.adapter.PopularMovieAdapter
import dev.jahidhasanco.fliq.ui.adapter.silder.MovieSliderAdapter


class MovieFragment : Fragment() {

    lateinit var image_slider_movieFragment: SliderView

    lateinit var movieSliderAdapter: MovieSliderAdapter
    lateinit var popularMovieAdapter: PopularMovieAdapter
    lateinit var topRatedMovieAdapter: PopularMovieAdapter

    lateinit var movieViewModel: MovieViewModel
    lateinit var popularMovieRecView_moviesFragment: RecyclerView
    lateinit var topRated_MovieLayout_movieFrag: LinearLayout
    lateinit var topRatedMovieRecView_moviesFragment: RecyclerView

    lateinit var noInternet_Layout_movieFragment: LinearLayout
    lateinit var popular_MovieLayout_movieFrag: LinearLayout

    lateinit var spin_kit_movieFrag: SpinKitView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie, container, false)

        image_slider_movieFragment = view.findViewById(R.id.image_slider_movieFragment)

        popularMovieRecView_moviesFragment = view.findViewById(R.id.popularMovieRecView_moviesFragment)
        noInternet_Layout_movieFragment = view.findViewById(R.id.noInternet_Layout_movieFragment)
        popular_MovieLayout_movieFrag = view.findViewById(R.id.popular_MovieLayout_movieFrag)
        topRated_MovieLayout_movieFrag = view.findViewById(R.id.topRated_MovieLayout_movieFrag)
        topRatedMovieRecView_moviesFragment = view.findViewById(R.id.topRatedMovieRecView_moviesFragment)
        spin_kit_movieFrag = view.findViewById(R.id.spin_kit_movieFrag)
        val popular_MovieSeeAll_movieFrag = view.findViewById<TextView>(R.id.popular_MovieSeeAll_movieFrag)
        val topRated_MovieSeeAll_movieFrag = view.findViewById<TextView>(R.id.topRated_MovieSeeAll_movieFrag)

        val doubleBounce: Sprite = Wave()
        spin_kit_movieFrag.setIndeterminateDrawable(doubleBounce)
        hideLayout()

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.refresh()
        movieViewModel.getPopularMovies("",1)

        observeViewModel()

        popular_MovieSeeAll_movieFrag.setOnClickListener {
            val intent = Intent(context, SeeAllMovieActivity::class.java)
            intent.putExtra("ComeFrom","PopularMovies")
            startActivity(intent)
        }

        topRated_MovieSeeAll_movieFrag.setOnClickListener {
            val intent = Intent(context, SeeAllMovieActivity::class.java)
            intent.putExtra("ComeFrom","TopRatedMovies")
            startActivity(intent)
        }

        showLayout()

        return view
    }

    private fun hideLayout() {

        image_slider_movieFragment.visibility = View.GONE
        popular_MovieLayout_movieFrag.visibility = View.GONE
        topRated_MovieLayout_movieFrag.visibility = View.GONE
        spin_kit_movieFrag.visibility = View.VISIBLE
    }

    private fun showLayout(){

        spin_kit_movieFrag.visibility = View.GONE
        image_slider_movieFragment.visibility = View.VISIBLE
        popular_MovieLayout_movieFrag.visibility = View.VISIBLE
        topRated_MovieLayout_movieFrag.visibility = View.VISIBLE
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

                }

            }

        })

        movieViewModel.TopRatedMovies.observe(requireActivity(), Observer {countries ->
            countries?.let {

                topRatedMovieAdapter = PopularMovieAdapter(requireActivity(),it)
                topRatedMovieRecView_moviesFragment.apply {
                    adapter = topRatedMovieAdapter
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    setHasFixedSize(false)
                }

            }

        })

        movieViewModel.movieLoadError.observe(requireActivity(), Observer { isError ->


            if(isError == "" || isError == null){
                noInternet_Layout_movieFragment.visibility =  View.GONE
                image_slider_movieFragment.visibility = View.VISIBLE
                popular_MovieLayout_movieFrag.visibility = View.VISIBLE
                topRated_MovieLayout_movieFrag.visibility = View.VISIBLE
            }else{
                noInternet_Layout_movieFragment.visibility =  View.VISIBLE
                image_slider_movieFragment.visibility = View.INVISIBLE
                popular_MovieLayout_movieFrag.visibility = View.INVISIBLE
                topRated_MovieLayout_movieFrag.visibility = View.INVISIBLE
            }

        })
        movieViewModel.loading.observe(requireActivity(), Observer { isLoading ->
            isLoading?.let {


                spin_kit_movieFrag.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    image_slider_movieFragment.visibility = View.GONE
                    popular_MovieLayout_movieFrag.visibility = View.GONE
                    topRated_MovieLayout_movieFrag.visibility = View.GONE
                }

            }
        })
    }


}