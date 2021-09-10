package dev.jahidhasanco.movieapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.jahidhasanco.movieapp.R
import dev.jahidhasanco.movieapp.data.viewModel.MovieViewModel
import dev.jahidhasanco.movieapp.ui.fragments.HomeFragment


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_activityMain, HomeFragment())
            .commit()


//        usersList.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = userListAdapter
//        }

    }


}