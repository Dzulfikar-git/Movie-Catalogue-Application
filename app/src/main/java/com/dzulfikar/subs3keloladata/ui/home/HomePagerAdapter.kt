package com.dzulfikar.subs3keloladata.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dzulfikar.subs3keloladata.ui.movie.MovieFragment
import com.dzulfikar.subs3keloladata.ui.tvshows.TvShowsFragment

class HomePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                MovieFragment()
            }

            1 -> {
                TvShowsFragment()
            }
           else -> {
               Fragment()
           }
        }
    }


}