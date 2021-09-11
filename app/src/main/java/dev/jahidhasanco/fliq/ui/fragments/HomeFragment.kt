package dev.jahidhasanco.fliq.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dev.jahidhasanco.fliq.R
import dev.jahidhasanco.fliq.ui.adapter.tab.HomeTabAdapter

class HomeFragment : Fragment() {

    lateinit var homeTabAdapter: HomeTabAdapter
    lateinit var tab_layout_HomeFrag: TabLayout
    lateinit var view_pager2_HomeFrag: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        tab_layout_HomeFrag = view.findViewById(R.id.tab_layout_HomeFrag)
        view_pager2_HomeFrag = view.findViewById(R.id.view_pager2_HomeFrag)


        val fm: FragmentManager = childFragmentManager

        homeTabAdapter = HomeTabAdapter(fm, lifecycle)
        view_pager2_HomeFrag.adapter = homeTabAdapter

        tab_layout_HomeFrag.addTab(tab_layout_HomeFrag.newTab().setText("Movie"))
        tab_layout_HomeFrag.addTab(tab_layout_HomeFrag.newTab().setText("Tv Show"))
        tab_layout_HomeFrag.setSelectedTabIndicatorHeight(0)

        tab_layout_HomeFrag.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                view_pager2_HomeFrag.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        view_pager2_HomeFrag.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tab_layout_HomeFrag.selectTab(tab_layout_HomeFrag.getTabAt(position))
            }
        })




        return view
    }



}