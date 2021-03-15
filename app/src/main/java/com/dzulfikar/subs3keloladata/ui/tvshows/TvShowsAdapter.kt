package com.dzulfikar.subs3keloladata.ui.tvshows

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzulfikar.subs3keloladata.BuildConfig
import com.dzulfikar.subs3keloladata.R
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.databinding.ItemTvShowsBinding
import com.dzulfikar.subs3keloladata.ui.detail.DetailTvShowActivity

class TvShowsAdapter(private var context: Context?, private val viewModel : TvShowsViewModel) : RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>() {

    private val tvShowLists = ArrayList<TvShowEntity>()

    fun setData(tvShow: List<TvShowEntity>?){
        if(tvShow == null) return
            this.tvShowLists.clear()
            this.tvShowLists.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShowLists[position])
    }

    override fun getItemCount(): Int = tvShowLists.size

    inner class TvShowViewHolder(private val viewBinding: ItemTvShowsBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(tvShow: TvShowEntity){
            with(viewBinding){
                if (tvShow.posterPath?.isNotEmpty() == true){
                    context?.let {
                        Glide.with(it)
                                .load(BuildConfig.API_IMG_URL + tvShow.posterPath)
                                .fitCenter()
                                .into(imgTvShows)
                    }
                }

                tvShowTitle.text = tvShow.name
                tvShowReleaseDate.text = context?.resources?.getString(R.string.tv_show_release_date_string, tvShow.firstAirDate)
                tvShowRating.text = context?.resources?.getString(R.string.tv_show_rating_string, tvShow.voteAverage.toString())

                itemView.setOnClickListener{
                    val detailShowIntent = Intent(context, DetailTvShowActivity::class.java)
                    detailShowIntent.putExtra(DetailTvShowActivity.EXTRA_SHOW_ID, tvShow.id)
                    context?.startActivity(detailShowIntent)
                }

                itemView.setOnLongClickListener {
                    if(!tvShow.isFavorite){
                        viewModel.setFavoriteTvShow(tvShow.id)
                        Toast.makeText(context, "${tvShow.name} is added to favorite", Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.deleteFavoriteTvShow(tvShow.id)
                        Toast.makeText(context, "${tvShow.name} is removed from favorite", Toast.LENGTH_LONG).show()
                    }
                    true
                }
            }
        }
    }
}