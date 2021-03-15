package com.dzulfikar.subs3keloladata.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.vo.Resource
import kotlinx.coroutines.launch

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getDetailMovie(id: Int) : LiveData<Resource<MovieEntity>>  =
            catalogueRepository.getDetailMovie(id)

    fun setFavoriteMovie(id: Int) = viewModelScope.launch {
        catalogueRepository.setFavoriteMovie(id)
    }

    fun deleteFavoriteMovie(id: Int) = viewModelScope.launch {
        catalogueRepository.deleteFavoriteMovie(id)
    }


    fun getDetailTvShow(id: Int) : LiveData<Resource<TvShowEntity>>  =
            catalogueRepository.getDetailTvShow(id)

    fun setFavoriteTvShow(id: Int) = viewModelScope.launch {
        catalogueRepository.setFavoriteTvShow(id)
    }

    fun deleteFavoriteTvShow(id: Int) = viewModelScope.launch {
        catalogueRepository.deleteFavoriteTvShow(id)
    }

}
