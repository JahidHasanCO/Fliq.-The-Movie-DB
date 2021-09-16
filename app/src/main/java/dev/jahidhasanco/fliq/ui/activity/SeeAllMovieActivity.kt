package dev.jahidhasanco.fliq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout
import dev.jahidhasanco.fliq.R
import org.w3c.dom.Text

class SeeAllMovieActivity : AppCompatActivity() {

    private var comeFrom = ""

    lateinit var toolbar: Toolbar
    lateinit var collapsingToolbar_seeAllMovies: CollapsingToolbarLayout

    lateinit var textTitle_seeAllMovies: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_movie)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        comeFrom = intent.getStringExtra("ComeFrom").toString()
        toolbar = findViewById(R.id.toolbar_seeAllMovies)
        collapsingToolbar_seeAllMovies = findViewById(R.id.collapsingToolbar_seeAllMovies)
        textTitle_seeAllMovies = findViewById(R.id.textTitle_seeAllMovies)
        setSupportActionBar(toolbar)




        when(comeFrom){
            "PopularMovies" -> {
                collapsingToolbar_seeAllMovies.title = "Popular Movies"
                textTitle_seeAllMovies.text = "Popular Movies"
            }
            else -> {
                collapsingToolbar_seeAllMovies.title = ""
                textTitle_seeAllMovies.text = ""
            }

        }


    }
}