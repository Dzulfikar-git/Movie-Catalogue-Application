package com.dzulfikar.subs3keloladata.ui.moviefavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import kotlinx.coroutines.launch

class MovieFavoritesViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavoriteMovies() : LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoriteMovies()
    fun deleteFavorite(id: Int) = viewModelScope.launch {
        catalogueRepository.deleteFavoriteMovie(id)
    }
}