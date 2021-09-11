package dev.jahidhasanco.fliq.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.ui.fragments.MovieFragment
import dev.jahidhasanco.fliq.ui.fragments.ProfileFragment
import dev.jahidhasanco.fliq.ui.fragments.SearchFragment
import dev.jahidhasanco.fliq.ui.fragments.TvShowFragment


class MainActivity : AppCompatActivity() ,BottomNavigationView.OnNavigationItemSelectedListener{

    lateinit var bottomNavMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        bottomNavMenu = findViewById(R.id.bottomNavMenu)
        bottomNavMenu.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_activityMain, MovieFragment())
            .commit()


//        usersList.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = userListAdapter
//        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeMenu -> {
                val fragment = MovieFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer_activityMain, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.tvShowMenu -> {
                val fragment = TvShowFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer_activityMain, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.searchMenu -> {
                val fragment = SearchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer_activityMain, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }

            R.id.profileMenu -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer_activityMain, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
        }
        return false
    }


}