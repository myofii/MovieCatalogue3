package com.yofi.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var moviesPagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getFavMovie() {
        val getAllDummyMovie = moviesPagedList
        Mockito.`when`(getAllDummyMovie.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = getAllDummyMovie

        Mockito.`when`(repository.getFavMovie()).thenReturn(movies)
        val moviesEntity = viewModel.getFavMovie().value
        Mockito.verify(repository).getFavMovie()
        Assert.assertNotNull(moviesEntity)
        Assert.assertEquals(5, moviesEntity?.size)

        viewModel.getFavMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(getAllDummyMovie)
    }

    @Test
    fun getFavTvShow() {
        val getAllDummyTvShow = tvShowPagedList
        Mockito.`when`(getAllDummyTvShow.size).thenReturn(5)
        val tvshows = MutableLiveData<PagedList<TvShowEntity>>()
        tvshows.value = getAllDummyTvShow

        Mockito.`when`(repository.getFavTvshow()).thenReturn(tvshows)
        val tvShowsEntity = viewModel.getFavTvShow().value
        Mockito.verify(repository).getFavTvshow()
        Assert.assertNotNull(tvShowsEntity)
        Assert.assertEquals(5, tvShowsEntity?.size)

        viewModel.getFavTvShow().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(getAllDummyTvShow)
    }
}