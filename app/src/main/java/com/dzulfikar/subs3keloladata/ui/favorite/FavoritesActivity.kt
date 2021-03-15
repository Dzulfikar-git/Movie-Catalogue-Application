package com.dzulfikar.subs3keloladata.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dzulfikar.subs3keloladata.R
import com.dzulfikar.subs3keloladata.databinding.ActivityFavoritesBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoritesBinding
    private lateinit var favoritesPagerAdapter: FavoritesPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("FavoritesActivity", "Favorites Loaded")

        // set binding view
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set viewpager adapter
        favoritesPagerAdapter = FavoritesPagerAdapter(this)
        binding.viewPagerFavorites.adapter = favoritesPagerAdapter

        // set actionbar elevation
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set viewpager adapter
        TabLayoutMediator(binding.tabFavorites, binding.viewPagerFavorites) {tab, position ->
            when(position){
                0 -> {
                    tab.text = getText(R.string.movie_tab_name)
                }

                1 -> {
                    tab.text = getText(R.string.show_tab_name)
                }
            }
        }.attach()
    }
}