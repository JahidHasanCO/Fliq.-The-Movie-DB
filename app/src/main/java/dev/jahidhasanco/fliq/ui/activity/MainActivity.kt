package dev.jahidhasanco.fliq.ui.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.ui.fragments.HomeFragment


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