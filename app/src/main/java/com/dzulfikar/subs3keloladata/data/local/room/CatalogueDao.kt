package com.dzulfikar.subs3keloladata.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity

@Dao
abstract class CatalogueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movie: List<MovieEntity>)

    @Query("SELECT * FROM movie")
    abstract fun getMovies() : LiveData<List<MovieEntity>>

    @Update
    abstract fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    abstract fun getDetailMovie(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    abstract fun getFavoriteMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("UPDATE movie SET isFavorite = 1 WHERE movieId = :movieId ")
    abstract fun setFavoriteMovie(movieId: Int)

    @Query("UPDATE movie SET isFavorite = 0 WHERE movieId = :movieId")
    abstract fun deleteFavoriteMovie(movieId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTvShows(tvShow : List<TvShowEntity>)

    @Query("SELECT * FROM tvshow")
    abstract fun getTvShows() : LiveData<List<TvShowEntity>>

    @Update
    abstract fun updateTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM tvshow WHERE tvShowId = :tvShowId")
    abstract fun getDetailTvShow(tvShowId: Int) : LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshow WHERE isFavorite = 1")
    abstract fun getFavoriteTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("UPDATE tvshow SET isFavorite = 1 WHERE tvShowId = :tvShowId ")
    abstract fun setFavoriteTvShow(tvShowId: Int)

    @Query("UPDATE tvshow SET isFavorite = 0 WHERE tvShowId = :tvShowId")
    abstract fun deleteFavoriteTvShow(tvShowId: Int)
}