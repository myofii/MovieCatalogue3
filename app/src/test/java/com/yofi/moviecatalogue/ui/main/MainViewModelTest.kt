package com.yofi.moviecatalogue.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.yofi.moviecatalogue.data.source.local.Dummy
import com.yofi.moviecatalogue.data.source.remote.Repository
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
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
    private lateinit var movieObserver: Observer<List<ItemMovie>>

    @Mock
    private lateinit var tvShowObserver: Observer<List<ItemTvShow>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getListMovie() {
        val dummyMoviesList = Dummy.getDataMovie()
        val movie = MutableLiveData<List<ItemMovie>>()
        movie.value = dummyMoviesList

        Mockito.`when`(repository.getMovie()).thenReturn(movie)
        val movieList = viewModel.getListMovie().value
        verify(repository).getMovie()
        assertNotNull(movie)
        if (movieList != null) {
            assertEquals(10, movieList.size)
        }

        viewModel.getListMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMoviesList)
    }

    @Test
    fun getListTvShow() {
        val dummyTvShowList = Dummy.getDataTvShow()
        val tvShow = MutableLiveData<List<ItemTvShow>>()
        tvShow.value = dummyTvShowList

        Mockito.`when`(repository.getTvShow()).thenReturn(tvShow)
        val tvShowList = viewModel.getListTvShow().value
        verify(repository).getTvShow()
        assertNotNull(tvShow)
        if (tvShowList != null) {
            assertEquals(10, tvShowList.size)
        }

        viewModel.getListTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShowList)
    }
}