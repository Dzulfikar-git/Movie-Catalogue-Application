package com.dzulfikar.subs3keloladata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.MovieEntity
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.ui.detail.DetailViewModel
import com.dzulfikar.subs3keloladata.utils.DataDummy
import com.dzulfikar.subs3keloladata.vo.Resource
import org.mockito.Mockito.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel : DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatalogueRepository

    @Mock
    private lateinit var movieObserver : Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver : Observer<Resource<TvShowEntity>>

    @Before
    fun setup(){
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieDetail(){
        val dummyDetailMovie = Resource.success(DataDummy.dummyApiDetailMovie())
        val detailMovie = MutableLiveData<Resource<MovieEntity>>()
        detailMovie.value = dummyDetailMovie

        `when`(repository.getDetailMovie(dummyDetailMovie.data!!.id)).thenReturn(detailMovie)
        val detailMovieEntity = viewModel.getDetailMovie(dummyDetailMovie.data!!.id).value
        verify(repository).getDetailMovie(dummyDetailMovie.data!!.id)
        assertNotNull(detailMovieEntity)

        viewModel.getDetailMovie(dummyDetailMovie.data!!.id).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)

    }

    @Test
    fun getTvShowDetail(){
        val dummyDetailTvShow = Resource.success(DataDummy.dummyApiDetailTvShow())
        val detailTvShow = MutableLiveData<Resource<TvShowEntity>>()
        detailTvShow.value = dummyDetailTvShow

        `when`(repository.getDetailTvShow(dummyDetailTvShow.data!!.id)).thenReturn(detailTvShow)
        val detailTvShowEntity = viewModel.getDetailTvShow(dummyDetailTvShow.data!!.id).value
        verify(repository).getDetailTvShow(dummyDetailTvShow.data!!.id)
        assertNotNull(detailTvShowEntity)

        viewModel.getDetailTvShow(dummyDetailTvShow.data!!.id).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }
}