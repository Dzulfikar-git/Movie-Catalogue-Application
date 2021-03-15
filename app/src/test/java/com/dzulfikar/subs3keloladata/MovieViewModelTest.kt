package com.dzulfikar.subs3keloladata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.ui.movie.MovieViewModel
import com.dzulfikar.subs3keloladata.utils.DataDummy
import com.dzulfikar.subs3keloladata.vo.Resource
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setup(){
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovies(){
        val dummyMovies = Resource.success(DataDummy.dummyApiMovieDiscover())
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(repository).getMovies()
        assertNotNull(movieEntities)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }
}