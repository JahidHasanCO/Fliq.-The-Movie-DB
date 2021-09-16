package dev.jahidhasanco.fliq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toolbar
import dev.jahidhasanco.fliq.R

class SeeAllMovieActivity : AppCompatActivity() {

    private var comeFrom = ""

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_movie)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        toolbar = findViewById(R.id.toolbar_seeAllMovies)
        setActionBar(toolbar)
        toolbar.title = "Popular Movies"

        comeFrom = intent.getStringExtra("ComeFrom").toString()

    }
}