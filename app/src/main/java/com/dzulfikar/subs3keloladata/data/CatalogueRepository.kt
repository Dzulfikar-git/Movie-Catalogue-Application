package com.dzulfikar.subs3keloladata.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dzulfikar.subs3keloladata.data.local.LocalDataSource
import com.dzulfikar.subs3keloladata.data.local.entity.DetailMovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.DetailTvEntity
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.data.remote.RemoteDataSource
import com.dzulfikar.subs3keloladata.data.remote.api.ApiResponse
import com.dzulfikar.subs3keloladata.data.remote.entity.DetailMovieResponse
import com.dzulfikar.subs3keloladata.data.remote.entity.DetailTvResponse
import com.dzulfikar.subs3keloladata.data.remote.entity.ResultsMovieItem
import com.dzulfikar.subs3keloladata.data.remote.entity.ResultsTvItem
import com.dzulfikar.subs3keloladata.utils.AppExecutors
import com.dzulfikar.subs3keloladata.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogueRepository constructor(
        val remoteDataSource: RemoteDataSource,
        val localDataSource: LocalDataSource,
        val appExecutors: AppExecutors
) : CatalogueDataSource{

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>,List<ResultsMovieItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                    localDataSource.getMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovieItem>>> =
                    remoteDataSource.discoverMovies()

            override fun saveCallResult(data: List<ResultsMovieItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (movieResponse in data){
                    val movie = MovieEntity(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.posterPath,
                            movieResponse.releaseDate,
                            movieResponse.voteAverage,
                            null)
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> =
                    localDataSource.getDetailMovie(id)

            override fun shouldFetch(data: MovieEntity?): Boolean  =
                   data?.detail == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                    remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: DetailMovieResponse) {
                val movieEntity = MovieEntity(
                        data.id,
                        data.title,
                        data.posterPath,
                        data.releaseDate,
                        data.voteAverage,
                        DetailMovieEntity(data.overview, data.homepage, data.genres.joinToString { it.name })
                )
                localDataSource.updateMovie(movieEntity)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override suspend fun setFavoriteMovie(id: Int) {
        withContext(Dispatchers.IO){
            localDataSource.setFavoriteMovie(id)
        }
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        withContext(Dispatchers.IO){
            localDataSource.deleteFavoriteMovie(id)
        }
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<ResultsTvItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                    localDataSource.getTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<ResultsTvItem>>> =
                    remoteDataSource.discoverTvShows()

            override fun saveCallResult(data: List<ResultsTvItem>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (tvResponse in data){
                    val tvShow = TvShowEntity(
                            tvResponse.id,
                            tvResponse.name,
                            tvResponse.posterPath,
                            tvResponse.firstAirDate,
                            tvResponse.voteAverage,
                            null
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntity> =
                    localDataSource.getDetailTvShow(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                    data?.detail == null

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> =
                    remoteDataSource.getDetailTvShow(id)

            override fun saveCallResult(data: DetailTvResponse) {
                    val tvShowEntity = TvShowEntity(
                            data.id,
                            data.name,
                            data.posterPath,
                            data.firstAirDate,
                            data.voteAverage,
                            DetailTvEntity(data.overview, data.homepage, data.genres.joinToString { it.name }, data.numberOfSeasons)
                    )
                localDataSource.updateTvShow(tvShowEntity)
            }
        }.asLiveData()
    }


    override fun getFavoriteTvShows():  LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override suspend fun setFavoriteTvShow(id: Int) {
        withContext(Dispatchers.IO){
            localDataSource.setFavoriteTvShow(id)
        }
    }

    override suspend fun deleteFavoriteTvShow(id: Int) {
        withContext(Dispatchers.IO){
            localDataSource.deleteFavoriteTvShow(id)
        }
    }
}