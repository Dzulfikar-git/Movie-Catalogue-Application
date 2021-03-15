package com.dzulfikar.subs3keloladata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.ui.moviefavorites.MovieFavoritesViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoritesViewModelTest {

    private lateinit var viewModel: MovieFavoritesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>


    @Before
    fun setup(){
        viewModel = MovieFavoritesViewModel(repository)
    }

    @Test
    fun getFavoriteMovies(){
        val dummyMovies = pagedList
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(repository.getFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovies().value
        verify(repository).getFavoriteMovies()
        assertNotNull(movieEntities)
        movieEntities?.get(0)?.let { assertEquals(it.isFavorite, true) }

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }

}