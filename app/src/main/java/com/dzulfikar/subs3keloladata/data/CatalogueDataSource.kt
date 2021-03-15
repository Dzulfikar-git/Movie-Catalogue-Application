package com.dzulfikar.subs3keloladata.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.vo.Resource

interface CatalogueDataSource {
   // Movie
   fun getMovies(): LiveData<Resource<List<MovieEntity>>>
   fun getDetailMovie(id: Int) : LiveData<Resource<MovieEntity>>
   fun getTvShows() : LiveData<Resource<List<TvShowEntity>>>
   suspend fun setFavoriteMovie(id: Int)
   suspend fun deleteFavoriteMovie(id: Int)

   // Tv Show
   fun getDetailTvShow(id: Int) : LiveData<Resource<TvShowEntity>>
   fun getFavoriteMovies() : LiveData<PagedList<MovieEntity>>
   fun getFavoriteTvShows() : LiveData<PagedList<TvShowEntity>>
   suspend fun setFavoriteTvShow(id: Int)
   suspend fun deleteFavoriteTvShow(id: Int)
}