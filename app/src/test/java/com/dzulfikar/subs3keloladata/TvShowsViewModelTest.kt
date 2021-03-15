package com.dzulfikar.subs3keloladata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dzulfikar.subs3keloladata.data.CatalogueRepository
import com.dzulfikar.subs3keloladata.data.local.entity.TvShowEntity
import com.dzulfikar.subs3keloladata.ui.tvshows.TvShowsViewModel
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
class TvShowsViewModelTest {
    private lateinit var viewModel : TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatalogueRepository

    @Mock
    private lateinit var observer : Observer<Resource<List<TvShowEntity>>>

    @Before
    fun setup(){
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun getTvShows(){
        val dummyTvShows = Resource.success(DataDummy.dummyApiTvShowDiscover())
        val tvShows = MutableLiveData<Resource<List<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(repository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value
        verify(repository).getTvShows()
        assertNotNull(tvShowEntities)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)

    }
}