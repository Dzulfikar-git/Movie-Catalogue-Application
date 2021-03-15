package com.dzulfikar.subs3keloladata.data.remote.api
import com.dzulfikar.subs3keloladata.data.remote.entity.DetailMovieResponse
import com.dzulfikar.subs3keloladata.data.remote.entity.DetailTvResponse
import com.dzulfikar.subs3keloladata.data.remote.entity.DiscoverMovieResponse
import com.dzulfikar.subs3keloladata.data.remote.entity.DiscoverTvResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key")
        api_key: String
    ) : Call<DiscoverMovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
            @Path("id")
            id: Int,
            @Query("api_key")
            api_key: String
    ) : Call<DetailMovieResponse>


    @GET("discover/tv")
    fun getTvShows(
            @Query("api_key")
            api_key: String
    ) : Call<DiscoverTvResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
            @Path("id")
            id: Int,
            @Query("api_key")
            api_key: String
    ) : Call<DetailTvResponse>
}