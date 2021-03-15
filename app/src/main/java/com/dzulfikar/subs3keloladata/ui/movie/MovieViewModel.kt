package com.dzulfikar.subs3keloladata.ui.movie


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.vo.Resource
import kotlinx.coroutines.launch

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovies() : LiveData<Resource<List<MovieEntity>>> = catalogueRepository.getMovies()

    fun setFavoriteMovie(id : Int) = viewModelScope.launch {
        catalogueRepository.setFavoriteMovie(id)
    }

    fun deleteFavoriteMovie(id : Int) = viewModelScope.launch {
        catalogueRepository.deleteFavoriteMovie(id)
    }

}