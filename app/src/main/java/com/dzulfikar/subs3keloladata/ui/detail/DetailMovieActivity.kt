package com.dzulfikar.subs3keloladata.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dzulfikar.subs3keloladata.BuildConfig
import com.dzulfikar.subs3keloladata.R
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.databinding.ActivityDetailMovieBinding
import com.dzulfikar.subs3keloladata.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        var EXTRA_SHOW_ID = "show_id"
    }

    private lateinit var viewBinding: ActivityDetailMovieBinding
    private val detailViewModel : DetailViewModel by viewModel()
    private var extraShowId : Int? = null
    private var isFavorite : Boolean? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        val view = viewBinding.root
        
        extraShowId = intent?.getIntExtra(EXTRA_SHOW_ID, 0)

        if(extraShowId != null){
            detailViewModel.getDetailMovie(extraShowId!!).observe(this, { data ->
                when(data.status){
                    Status.LOADING -> {
                        viewBinding.detailMovieProgressBar.visibility = View.VISIBLE
                        viewBinding.detailMovieMainLayout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        viewBinding.detailMovieProgressBar.visibility = View.GONE
                        viewBinding.detailMovieMainLayout.visibility = View.VISIBLE
                        isFavorite = data.data?.isFavorite
                        enableFavorite(menu)
                        data.data?.let { populateData(it) }

                    }
                    Status.ERROR -> {
                        viewBinding.detailMovieProgressBar.visibility = View.VISIBLE
                        viewBinding.detailMovieMainLayout.visibility = View.GONE
                        Toast.makeText(this, data.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
        setContentView(view)
    }

    private fun populateData(movie: MovieEntity){
        supportActionBar?.title = movie.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(movie.posterPath?.isNotEmpty() == true){
            Glide.with(this)
                    .load(BuildConfig.API_IMG_URL + movie.posterPath)
                    .fitCenter()
                    .into(viewBinding.imgDetailMovie)
        }


        viewBinding.tvDetailMovieTitle.text = movie.title

        viewBinding.tvDetailMovieGenre.text = getString(R.string.tv_movie_genre, movie.detail?.genre)

        viewBinding.tvDetailMovieReleaseDate.text = getString(R.string.tv_movie_release_date_string, movie.releaseDate)
        viewBinding.tvDetailMovieRating.text = movie.voteAverage.toString()
        viewBinding.tvDetailMovieOverviewText.text = movie.detail?.overview

        viewBinding.btnDetailMovieBrowser.setOnClickListener {
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(movie.detail?.url))
            startActivity(intentBrowser)
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val movieIsFavorite = menu?.findItem(R.id.detailIsFavorite)
        val movieIsNotFavorite = menu?.findItem(R.id.detailNotFavorite)
        return when(isFavorite) {
            true -> {
                movieIsFavorite?.isVisible = true
                movieIsNotFavorite?.isVisible = false
                true
            }
            false -> {
                movieIsNotFavorite?.isVisible = true
                movieIsFavorite?.isVisible = false
                true
            }
            else -> {
                movieIsFavorite?.isVisible = false
                movieIsNotFavorite?.isVisible = false
                true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.detailNotFavorite -> {
                setFavorite()
                true
            }
            R.id.detailIsFavorite -> {
                deleteFavorite()
                true
            }
            else -> false
        }
    }


    private fun setFavoriteButton(check: Boolean){
        val movieIsFavorite = menu?.findItem(R.id.detailIsFavorite)
        val movieIsNotFavorite = menu?.findItem(R.id.detailNotFavorite)
        if(check) {
            movieIsFavorite?.isVisible = true
            movieIsNotFavorite?.isVisible = false
        } else {
            movieIsNotFavorite?.isVisible = true
            movieIsFavorite?.isVisible = false
        }
    }

    private fun enableFavorite(menu: Menu?){
        val movieIsFavorite = menu?.findItem(R.id.detailIsFavorite)
        val movieIsNotFavorite = menu?.findItem(R.id.detailNotFavorite)
        if(isFavorite == true) {
            movieIsFavorite?.isVisible = true
            movieIsNotFavorite?.isVisible = false
        } else {
            movieIsNotFavorite?.isVisible = true
            movieIsFavorite?.isVisible = false
        }
    }

    private fun setFavorite(){
        extraShowId?.let { detailViewModel.setFavoriteMovie(it) }
        setFavoriteButton(true)
    }

    private fun deleteFavorite(){
        extraShowId?.let { detailViewModel.deleteFavoriteMovie(it) }
        setFavoriteButton(false)
    }
}