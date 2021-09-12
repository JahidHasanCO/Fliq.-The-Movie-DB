package dev.jahidhasanco.fliq.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dev.jahidhasanco.fliq.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dev.jahidhasanco.fliq.data.viewModel.MovieViewModel
import dev.jahidhasanco.fliq.ui.adapter.MovieCrewAdapter


class YoutubeVideoPlayerActivity : AppCompatActivity() {

    private var movieId: String = ""
    private var  videoId = ""
    lateinit var youtube_player_view: YouTubePlayerView
    lateinit var backBtn_youtubeVideoPlayerPage: ImageView
    lateinit var movieViewModel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_video_player)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        movieId = intent.getStringExtra("MovieIdPass").toString()
        youtube_player_view = findViewById(R.id.youtube_player_view)
        backBtn_youtubeVideoPlayerPage = findViewById(R.id.backBtn_youtubeVideoPlayerPage)

        backBtn_youtubeVideoPlayerPage.visibility = View.INVISIBLE

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovieTrailer(movieId, "en-US")
        observeViewModel()

        lifecycle.addObserver(youtube_player_view)

        backBtn_youtubeVideoPlayerPage.setOnClickListener {
            onBackPressed()
        }

    }

    private fun observeViewModel() {

        movieViewModel.MovieTrailer.observe(this, Observer { trailer ->

            trailer?.let {
                youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        if(it.results[0].type == "Trailer"){
                            videoId = it.results[0].key
                        }else{
                            videoId = it.results[1].key
                        }

                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }

        })


        movieViewModel.movieLoadError.observe(this, Observer { isError ->

        })
        movieViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
            }
        })
    }
}