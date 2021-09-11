package dev.jahidhasanco.movieapp.ui.adapter.tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.jahidhasanco.movieapp.ui.fragments.MovieFragment

class HomeTabAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            1 -> return MovieFragment()
            2 -> return MovieFragment()
        }
        return MovieFragment()
    }

}