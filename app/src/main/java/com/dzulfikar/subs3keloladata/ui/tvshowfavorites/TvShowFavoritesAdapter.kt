package com.dzulfikar.subs3keloladata.ui.tvshowfavorites

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
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.databinding.ItemTvShowFavoritesBinding
import com.dzulfikar.subs3keloladata.ui.detail.DetailTvShowActivity

class TvShowFavoritesAdapter(private var context : Context?, private val viewModel: TvShowFavoritesViewModel) : PagedListAdapter<TvShowEntity, TvShowFavoritesAdapter.TvShowFavoritesViewHolder>(DIFF_CALLBACK)  {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>(){
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): TvShowFavoritesViewHolder {
        val itemTvShowFavoritesBinding = ItemTvShowFavoritesBinding.inflate(LayoutInflater.from(context), viewGroup, false )
        return TvShowFavoritesViewHolder(itemTvShowFavoritesBinding)
    }

    override fun onBindViewHolder(holder: TvShowFavoritesViewHolder, position: Int) {
        val tvShow = getItem(position)
        if(tvShow != null){
            holder.bind(tvShow)
        }
    }

    inner class TvShowFavoritesViewHolder(private val binding: ItemTvShowFavoritesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TvShowEntity){
            with(binding){
                if(tvShowEntity.posterPath?.isNotEmpty() == true){
                    context?.let {
                        Glide.with(it)
                                .load(BuildConfig.API_IMG_URL + tvShowEntity.posterPath)
                                .fitCenter()
                                .into(imgFavoriteTvShow)
                    }
                }

                tvFavoriteTvShowTitle.text = tvShowEntity.name
                tvFavoriteTvShowReleaseDate.text = context?.resources?.getString(R.string.tv_show_release_date_string, tvShowEntity.firstAirDate)
                tvFavoriteTvShowRating.text = context?.resources?.getString(R.string.tv_show_rating_string, tvShowEntity.voteAverage.toString())
                imageButtonFavoriteTvShow.setOnClickListener {
                    viewModel.deleteFavoriteTvShow(tvShowEntity.id)
                }

                itemView.setOnClickListener {
                    val detailTvShowIntent = Intent(context, DetailTvShowActivity::class.java)
                    detailTvShowIntent.putExtra(DetailTvShowActivity.EXTRA_SHOW_ID, tvShowEntity.id)
                    context?.startActivity(detailTvShowIntent)
                }
            }
        }
    }
}