package com.dzulfikar.subs3keloladata.ui.tvshowfavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import kotlinx.coroutines.launch

class TvShowFavoritesViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavoriteTvShows() : LiveData<PagedList<TvShowEntity>> = catalogueRepository.getFavoriteTvShows()
    fun deleteFavoriteTvShow(id: Int) = viewModelScope.launch {
        catalogueRepository.deleteFavoriteTvShow(id)
    }
}