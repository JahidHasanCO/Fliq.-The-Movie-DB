package dev.jahidhasanco.fliq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout
import dev.jahidhasanco.fliq.R

class SeeAllMovieActivity : AppCompatActivity() {

    private var comeFrom = ""

    lateinit var toolbar: Toolbar
    lateinit var collapsingToolbar_seeAllMovies: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_movie)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        toolbar = findViewById(R.id.toolbar_seeAllMovies)
        collapsingToolbar_seeAllMovies = findViewById(R.id.collapsingToolbar_seeAllMovies)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        collapsingToolbar_seeAllMovies.title = "Popular Movies"

        comeFrom = intent.getStringExtra("ComeFrom").toString()

    }
}