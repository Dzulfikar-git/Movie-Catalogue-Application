package com.dzulfikar.subs3keloladata.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dzulfikar.subs3keloladata.ui.moviefavorites.MovieFavoritesFragment
import com.dzulfikar.subs3keloladata.ui.tvshowfavorites.TvShowFavoritesFragment

class FavoritesPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                MovieFavoritesFragment()
            }
            1 -> {
                TvShowFavoritesFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}