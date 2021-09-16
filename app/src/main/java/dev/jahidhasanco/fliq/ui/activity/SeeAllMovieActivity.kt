package dev.jahidhasanco.fliq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.jahidhasanco.fliq.R

class SeeAllMovieActivity : AppCompatActivity() {

    private var comeFrom = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_movie)

        comeFrom = intent.getStringExtra("ComeFrom").toString()

    }
}