package com.dzulfikar.subs3keloladata.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.vo.Resource
import kotlinx.coroutines.launch

class TvShowsViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getTvShows() : LiveData<Resource<List<TvShowEntity>>> =
        catalogueRepository.getTvShows()

    fun setFavoriteTvShow(id: Int) = viewModelScope.launch {
        catalogueRepository.setFavoriteTvShow(id)
    }

    fun deleteFavoriteTvShow(id: Int) = viewModelScope.launch {
        catalogueRepository.deleteFavoriteTvShow(id)
    }

}