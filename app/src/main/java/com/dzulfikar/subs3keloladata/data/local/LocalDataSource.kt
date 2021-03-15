package com.dzulfikar.subs3keloladata.data.local

import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.data.local.room.CatalogueDao

class LocalDataSource constructor(private val catalogueDao: CatalogueDao){

    // Movies
    fun insertMovies(movieEntity: List<MovieEntity>) = catalogueDao.insertMovies(movieEntity)

    fun getMovies() = catalogueDao.getMovies()

    fun updateMovie(movie: MovieEntity) = catalogueDao.updateMovie(movie)

    fun getDetailMovie(id: Int) = catalogueDao.getDetailMovie(id)

    fun getFavoriteMovies() = catalogueDao.getFavoriteMovies()

    fun setFavoriteMovie(id: Int) = catalogueDao.setFavoriteMovie(id)

    fun deleteFavoriteMovie(id: Int) = catalogueDao.deleteFavoriteMovie(id)


    // Tv Shows
    fun insertTvShows(tvShowEntity: List<TvShowEntity>) = catalogueDao.insertTvShows(tvShowEntity)

    fun getTvShows() = catalogueDao.getTvShows()

    fun updateTvShow(tvShow: TvShowEntity) = catalogueDao.updateTvShow(tvShow)

    fun getDetailTvShow(id: Int) = catalogueDao.getDetailTvShow(id)

    fun getFavoriteTvShows() = catalogueDao.getFavoriteTvShows()

    fun setFavoriteTvShow(id: Int) = catalogueDao.setFavoriteTvShow(id)

    fun deleteFavoriteTvShow(id: Int) = catalogueDao.deleteFavoriteTvShow(id)
}