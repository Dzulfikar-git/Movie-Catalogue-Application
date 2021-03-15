package com.dzulfikar.subs3keloladata.ui.moviefavorites

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzulfikar.subs3keloladata.BuildConfig
import com.dzulfikar.subs3keloladata.R
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.databinding.ItemMovieFavoritesBinding
import com.dzulfikar.subs3keloladata.ui.detail.DetailMovieActivity

class MovieFavoritesAdapter(private val context: Context?, private val viewModel : MovieFavoritesViewModel) : PagedListAdapter<MovieEntity, MovieFavoritesAdapter.MovieFavoriteViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MovieFavoriteViewHolder {
        val itemMovieFavoritesBinding = ItemMovieFavoritesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MovieFavoriteViewHolder(itemMovieFavoritesBinding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie != null){
            holder.bind(movie)
        }
    }

    inner class MovieFavoriteViewHolder(private val binding: ItemMovieFavoritesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (movieEntity: MovieEntity){
            with(binding){
                tvFavoriteMovieRating.text =  context?.resources?.getString(R.string.tv_movie_rating_string, movieEntity.voteAverage)
                tvFavoriteMovieReleaseDate.text = context?.resources?.getString(R.string.tv_movie_release_date_string, movieEntity.releaseDate)
                tvFavoriteMovieTitle.text = movieEntity.title

                if(movieEntity.posterPath?.isNotEmpty() == true){
                    context?.let {
                        Glide.with(it)
                                .load(BuildConfig.API_IMG_URL + movieEntity.posterPath)
                                .fitCenter()
                                .into(imgFavoriteMovie)
                    }
                }


                imageButtonFavoriteMovie.setOnClickListener {
                    viewModel.deleteFavorite(movieEntity.id)
                }

                itemView.setOnClickListener {
                    val detailMovieIntent = Intent(context, DetailMovieActivity::class.java)
                    detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_SHOW_ID, movieEntity.id)
                    context?.startActivity(detailMovieIntent)
                }

            }
        }
    }

}