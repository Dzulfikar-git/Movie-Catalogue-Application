package com.dzulfikar.subs3keloladata.ui.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzulfikar.subs3keloladata.BuildConfig
import com.dzulfikar.subs3keloladata.R
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.databinding.ItemMovieBinding
import com.dzulfikar.subs3keloladata.ui.detail.DetailMovieActivity

class MovieFragmentAdapter(private val context: Context?, private val viewModel: MovieViewModel) : RecyclerView.Adapter<MovieFragmentAdapter.MovieFragmentViewHolder>() {

    private val movieList = ArrayList<MovieEntity>()

    fun setData(movies: List<MovieEntity>?){
        if(movies == null) return
            this.movieList.clear()
            this.movieList.addAll(movies)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieFragmentViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MovieFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieFragmentViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieFragmentViewHolder(private val viewBinding: ItemMovieBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(movie: MovieEntity){
            with(viewBinding){
                tvMovieTitle.text = movie.title
                tvMovieRating.text = context?.resources?.getString(R.string.tv_movie_rating_string, movie.voteAverage)
                tvMovieReleaseDate.text = context?.resources?.getString(R.string.tv_movie_release_date_string, movie.releaseDate)

                if(movie.posterPath?.isNotEmpty() == true){
                    context?.let {
                        Glide.with(it)
                                .load(BuildConfig.API_IMG_URL + movie.posterPath)
                                .fitCenter()
                                .into(imgMovie)
                    }
                }


                itemView.setOnClickListener {
                    val detailMovieIntent = Intent(context, DetailMovieActivity::class.java)
                    detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_SHOW_ID, movie.id)
                    context?.startActivity(detailMovieIntent)
                }

                itemView.setOnLongClickListener {
                    if(!movie.isFavorite){
                        Toast.makeText(context, "${movie.title} is added to favorite", Toast.LENGTH_LONG).show()
                        viewModel.setFavoriteMovie(movie.id)
                    } else {
                        Toast.makeText(context, "${movie.title} is removed fom favorite", Toast.LENGTH_LONG).show()
                        viewModel.deleteFavoriteMovie(movie.id)
                    }

                    true
                }
            }
        }
    }
}