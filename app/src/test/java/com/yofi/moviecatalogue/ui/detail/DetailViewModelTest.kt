package com.yofi.moviecatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.yofi.moviecatalogue.utils.Dummy
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.ui.detail.DetailViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = Dummy.getDataMovie()[1]
    private val dummyTvShow = Dummy.getDataTvShow()[1]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<ItemMovie>

    @Mock
    private lateinit var tvShowObserver: Observer<ItemTvShow>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getDetailMovieById() {
        val movies = MutableLiveData<ItemMovie>()

        movies.value = dummyMovie
        Mockito.`when`(movieId.let { repository.getMovieById(it) }).thenReturn(movies)

        val listMovieData = movieId.let { viewModel.getDetailMovieById(it) }.value as ItemMovie

        assertNotNull(listMovieData)
        assertEquals(dummyMovie.id, listMovieData.id)
        assertEquals(dummyMovie.title, listMovieData.title)
        assertEquals(dummyMovie.posterPath, listMovieData.posterPath)
        assertEquals(dummyMovie.releaseDate, listMovieData.releaseDate)
        assertEquals(dummyMovie.voteAverage.toString(), listMovieData.voteAverage.toString())
        assertEquals(dummyMovie.overview, listMovieData.overview)

        viewModel.getDetailMovieById(movieId).observeForever(movieObserver)

        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTvShowById() {
        val tvShows = MutableLiveData<ItemTvShow>()

        tvShows.value = dummyTvShow
        Mockito.`when`(tvShowId.let { repository.getTvShowById(it) }).thenReturn(tvShows)

        val listTvShowsData = tvShowId.let { viewModel.getDetailTvShowById(it) }.value as ItemTvShow

        assertNotNull(listTvShowsData)
        assertEquals(dummyTvShow.id, listTvShowsData.id)
        assertEquals(dummyTvShow.originalName, listTvShowsData.originalName)
        assertEquals(dummyTvShow.posterPath, listTvShowsData.posterPath)
        assertEquals(dummyTvShow.firstAirDate, listTvShowsData.firstAirDate)
        assertEquals(dummyTvShow.voteAverage.toString(), listTvShowsData.voteAverage.toString())
        assertEquals(dummyTvShow.overview, listTvShowsData.overview)

        viewModel.getDetailTvShowById(tvShowId).observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}