package com.dzulfikar.subs3keloladata.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dzulfikar.subs3keloladata.BuildConfig
import com.dzulfikar.subs3keloladata.data.remote.api.ApiConfig
import com.dzulfikar.subs3keloladata.data.remote.api.ApiResponse
import com.dzulfikar.subs3keloladata.data.remote.entity.*
import com.dzulfikar.subs3keloladata.utils.DataDummy
import com.dzulfikar.subs3keloladata.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        val TAG: String = RemoteDataSource::class.java.simpleName
        const val ERRORTAG: String = "Failed to Get Data From Server"

    }

    fun discoverMovies(): LiveData<ApiResponse<List<ResultsMovieItem>>> {
        val movieItems = MutableLiveData<ApiResponse<List<ResultsMovieItem>>>()
        val client = ApiConfig.getApiService().getMovies(BuildConfig.API_KEY)
        Log.d(TAG, "discoverMovies Called")
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<DiscoverMovieResponse>{
            override fun onResponse(call: Call<DiscoverMovieResponse>, response: Response<DiscoverMovieResponse>) {
                if(response.isSuccessful){
                    movieItems.value = response.body()?.let { ApiResponse.success(it.results) }
                } else {
                    Log.e(TAG, "Failed Discover Movie Response")
                    movieItems.value = response.body()?.let { ApiResponse.empty(ERRORTAG, it.results) }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DiscoverMovieResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
                movieItems.value = ApiResponse.error(t.message.toString(), emptyList())
            }
        })
        return movieItems
    }

     fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        val detailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        val client = ApiConfig.getApiService().getDetailMovie(id, BuildConfig.API_KEY)
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<DetailMovieResponse>{
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if(response.isSuccessful){
                    detailMovie.value = response.body()?.let { ApiResponse.success(it) }
                } else {
                    Log.e(TAG, "Failed Detail Movie Response")
                    detailMovie.value = response.body()?.let { ApiResponse.empty(ERRORTAG, it) }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
                detailMovie.value = ApiResponse.error(ERRORTAG, DataDummy.dummyEmptyDetailMovie())
            }
        })
        return detailMovie
    }

     fun discoverTvShows(): LiveData<ApiResponse<List<ResultsTvItem>>> {
        val tvShowItems = MutableLiveData<ApiResponse<List<ResultsTvItem>>>()
        val client = ApiConfig.getApiService().getTvShows(BuildConfig.API_KEY)

        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<DiscoverTvResponse>{
            override fun onResponse(call: Call<DiscoverTvResponse>, response: Response<DiscoverTvResponse>) {
                if(response.isSuccessful){
                    tvShowItems.value = response.body()?.let { ApiResponse.success(it.results) }
                } else {
                    Log.e(TAG, "Failed Discover Tv Response")
                    tvShowItems.value = response.body()?.let { ApiResponse.empty(ERRORTAG, it.results) }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DiscoverTvResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
                tvShowItems.value = ApiResponse.error(ERRORTAG, emptyList())
            }
        })
        return tvShowItems
    }

     fun getDetailTvShow(id: Int): LiveData<ApiResponse<DetailTvResponse>> {
        val detailTvShow = MutableLiveData<ApiResponse<DetailTvResponse>>()
        val client = ApiConfig.getApiService().getDetailTvShow(id, BuildConfig.API_KEY)

        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<DetailTvResponse>{
            override fun onResponse(call: Call<DetailTvResponse>, response: Response<DetailTvResponse>) {
                if(response.isSuccessful){
                    detailTvShow.value = response.body()?.let { ApiResponse.success(it) }
                } else {
                    Log.e(TAG, "Failed Detail Tv Response")
                    detailTvShow.value = response.body()?.let { ApiResponse.empty(ERRORTAG, it) }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
                detailTvShow.value = ApiResponse.error(ERRORTAG, DataDummy.dummyEmptyDetailTvShow())
            }
        })
        return detailTvShow
    }
}