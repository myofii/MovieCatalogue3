package com.yofi.moviecatalogue.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getListMovie() {
        val getAllDummyMovies = Resource.success(moviePagedList)
        Mockito.`when`(getAllDummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = getAllDummyMovies

        Mockito.`when`(repository.getMovie()).thenReturn(movies)
        val moviesEntity = viewModel.getListMovie().value?.data
        Mockito.verify(repository).getMovie()
        assertNotNull(moviesEntity)
        assertEquals(5, moviesEntity?.size)

        viewModel.getListMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(getAllDummyMovies)
    }

    @Test
    fun getListTvShow() {
        val getAllDummyTvShow = Resource.success(tvShowPagedList)
        Mockito.`when`(getAllDummyTvShow.data?.size).thenReturn(5)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = getAllDummyTvShow

        Mockito.`when`(repository.getTvShow()).thenReturn(tvShows)
        val tvShowsEntity = viewModel.getListTvShow().value?.data
        Mockito.verify(repository).getTvShow()
        assertNotNull(tvShowsEntity)
        assertEquals(5, tvShowsEntity?.size)

        viewModel.getListTvShow().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(getAllDummyTvShow)
    }

    @Test
    fun getMovieByName() {
        val getAllDummyMovies = Resource.success(moviePagedList)
        Mockito.`when`(getAllDummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = getAllDummyMovies

        Mockito.`when`(repository.getMovieByName("a")).thenReturn(movies)
        val moviesEntity = viewModel.getSearchMovie("a").value?.data
        Mockito.verify(repository).getMovieByName("a")
        assertNotNull(moviesEntity)
        assertEquals(5, moviesEntity?.size)

        viewModel.getSearchMovie("a").observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(getAllDummyMovies)
    }

    @Test
    fun getTvShowByName() {
        val getAllDummyTvShow = Resource.success(tvShowPagedList)
        Mockito.`when`(getAllDummyTvShow.data?.size).thenReturn(5)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = getAllDummyTvShow

        Mockito.`when`(repository.getTvShowByName("a")).thenReturn(tvShows)
        val tvShowsEntity = viewModel.getSearchTvShow("a").value?.data
        Mockito.verify(repository).getTvShowByName("a")
        assertNotNull(tvShowsEntity)
        assertEquals(5, tvShowsEntity?.size)

        viewModel.getSearchTvShow("a").observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(getAllDummyTvShow)
    }
}