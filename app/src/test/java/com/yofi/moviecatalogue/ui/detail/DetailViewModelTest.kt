package com.yofi.moviecatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.yofi.moviecatalogue.utils.Dummy
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.ui.detail.DetailViewModel
import com.yofi.moviecatalogue.vo.Resource
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
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = Resource.success(Dummy.getDataMovie()[0])
    private val dummyTvShow = Resource.success(Dummy.getDataTvShow()[0])
    private val movieId = dummyMovie.data?.id
    private val tvShowId = dummyTvShow.data?.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getDetailMovieById() {
        viewModel.setSelectedMovie(movieId!!)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie
        `when`(repository.getMovieById(movieId)).thenReturn(movie)
        viewModel.getDetailMovieById().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTvShowById() {
        viewModel.setSelectedTvShow(tvShowId!!)
        val tvshow = MutableLiveData<Resource<TvShowEntity>>()
        tvshow.value = dummyTvShow
        `when`(repository.getTvShowById(tvShowId)).thenReturn(tvshow)
        viewModel.getDetailTvShowById().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun setFavoriteMovie() {
        val movie = Dummy.getDataMovie()[0]

        // set true favorite
        movie.favorite = true
        val addFavorite = viewModel.setFavoriteMovie(movie)
        assertNotNull(addFavorite)

        // set false favorite
        movie.favorite = false
        val removeFavorite = viewModel.setFavoriteMovie(movie)
        assertNotNull(removeFavorite)
    }

    @Test
    fun setFavoriteTvShow() {
        val tvshow = Dummy.getDataTvShow()[0]

        // set true favorite
        tvshow.favorite = true
        val addFavorite = viewModel.setFavoriteTvShow(tvshow)
        assertNotNull(addFavorite)

        // set false favorite
        tvshow.favorite = false
        val removeFavorite = viewModel.setFavoriteTvShow(tvshow)
        assertNotNull(removeFavorite)
    }
}