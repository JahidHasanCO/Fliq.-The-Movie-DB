package dev.jahidhasanco.fliq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dev.jahidhasanco.fliq.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener




class YoutubeVideoPlayerActivity : AppCompatActivity() {

    private var movieId: String = ""
    private var  videoId = ""
    lateinit var youtube_player_view: YouTubePlayerView
    lateinit var backBtn_youtubeVideoPlayerPage: ImageView

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


        lifecycle.addObserver(youtube_player_view)

        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                 videoId = "S0Q4gqBUs7c"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        backBtn_youtubeVideoPlayerPage.setOnClickListener {
            onBackPressed()
        }

    }
}