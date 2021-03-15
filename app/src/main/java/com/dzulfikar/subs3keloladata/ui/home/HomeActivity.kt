package com.dzulfikar.subs3keloladata.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dzulfikar.subs3keloladata.R
import com.dzulfikar.subs3keloladata.databinding.ActivityHomeBinding
import com.dzulfikar.subs3keloladata.ui.favorite.FavoritesActivity
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homePagerAdapter : HomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set view binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set viewpager adapter
        homePagerAdapter = HomePagerAdapter(this)
        binding.viewPagerHome.adapter = homePagerAdapter

        // set actionbar elevation
        supportActionBar?.elevation = 0f

        // attach fragment to tablayout and viewpager2
        TabLayoutMediator(binding.tabHome, binding.viewPagerHome ) { tab, position ->
            when (position){
                0 -> {
                    tab.text = getText(R.string.movie_tab_name)
                }

                1 -> {
                    tab.text = getText(R.string.show_tab_name)
                }
            }
        }.attach()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater : MenuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menuFavorite -> {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
                true
            }
            else -> false
        }
    }
}