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
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.databinding.ActivityDetailTvShowBinding
import com.dzulfikar.subs3keloladata.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SHOW_ID = "show_id"
    }

    private lateinit var viewBinding : ActivityDetailTvShowBinding
    private val detailViewModel : DetailViewModel by viewModel()
    private var extraShowId : Int? = null
    private var isFavorite : Boolean? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        val view = viewBinding.root

        extraShowId = intent?.getIntExtra(EXTRA_SHOW_ID, 0)

        if(extraShowId != null){
            detailViewModel.getDetailTvShow(extraShowId!!).observe(this, {data ->
                when(data.status){
                    Status.LOADING -> {
                        viewBinding.detailTvShowProgressBar.visibility = View.VISIBLE
                        viewBinding.tvShowMainLayout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        viewBinding.detailTvShowProgressBar.visibility = View.GONE
                        viewBinding.tvShowMainLayout.visibility = View.VISIBLE
                        isFavorite = data.data?.isFavorite
                        enableFavorite(menu)
                        data.data?.let { populateData(it) }
                    }
                    Status.ERROR -> {
                        viewBinding.detailTvShowProgressBar.visibility = View.VISIBLE
                        viewBinding.tvShowMainLayout.visibility = View.GONE
                        Toast.makeText(this, data.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
        setContentView(view)
    }



    private fun populateData(tvShow: TvShowEntity){
        supportActionBar?.title = tvShow.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(tvShow.posterPath?.isNotEmpty() == true){
            Glide.with(this)
                    .load(BuildConfig.API_IMG_URL + tvShow.posterPath)
                    .fitCenter()
                    .into(viewBinding.imgDetailTvShow)
        }

        viewBinding.tvDetailShowTitle.text = tvShow.name
        viewBinding.tvDetailShowGenre.text = getString(R.string.tv_show_genre, tvShow.detail?.genre)
        viewBinding.tvDetailShowReleaseDate.text = getString(R.string.tv_show_release_date_string, tvShow.firstAirDate)
        viewBinding.tvDetailShowRating.text = tvShow.voteAverage.toString()
        viewBinding.tvDetailShowOverviewText.text = tvShow.detail?.overview
        viewBinding.tvDetailShowSeason.text = getString(R.string.tv_show_season, tvShow.detail?.seasons.toString())

        viewBinding.btnDetailShowBrowser.setOnClickListener {
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(tvShow.detail?.url))
            startActivity(intentBrowser)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val tvIsFavorite = menu?.findItem(R.id.detailIsFavorite)
        val tvIsNotFavorite = menu?.findItem(R.id.detailNotFavorite)
        return when(isFavorite){
            true -> {
                tvIsFavorite?.isVisible = true
                tvIsNotFavorite?.isVisible = false
                true
            }
            false -> {
                tvIsFavorite?.isVisible = false
                tvIsNotFavorite?.isVisible = true
                true
            }
            else -> {
                tvIsFavorite?.isVisible = false
                tvIsNotFavorite?.isVisible = false
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

    private fun setFavoriteButton(check : Boolean){
        val tvIsFavorite = menu?.findItem(R.id.detailIsFavorite)
        val tvIsNotFavorite = menu?.findItem(R.id.detailNotFavorite)
        if(check) {
            tvIsFavorite?.isVisible = true
            tvIsNotFavorite?.isVisible = false
        } else {
            tvIsFavorite?.isVisible = false
            tvIsNotFavorite?.isVisible = true
        }
    }

    private fun enableFavorite(menu: Menu?){
        val tvIsFavorite = menu?.findItem(R.id.detailIsFavorite)
        val tvIsNotFavorite = menu?.findItem(R.id.detailNotFavorite)
        if(isFavorite == true){
            tvIsFavorite?.isVisible = true
            tvIsNotFavorite?.isVisible = false
        } else {
            tvIsFavorite?.isVisible = false
            tvIsNotFavorite?.isVisible = true
        }
    }

    private fun setFavorite(){
        extraShowId?.let { detailViewModel.setFavoriteTvShow(it) }
        setFavoriteButton(true)
    }

    private fun deleteFavorite(){
        extraShowId?.let { detailViewModel.deleteFavoriteTvShow(it) }
        setFavoriteButton(false)
    }
}