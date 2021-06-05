package com.yofi.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.yofi.moviecatalogue.BuildConfig
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.databinding.ActivityDetailBinding
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory
import com.yofi.moviecatalogue.vo.Status

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showLoading(true)
        val viewModel = ViewModelProvider(
            this@DetailActivity,
            ViewModelFactory.getInstance(this)
        )[DetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_ID,0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals("MOVIE")) {
            viewModel.setSelectedMovie(id)
            viewModel.getDetailMovieById().observe(this,{ movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            showDetailMovie(movie.data)
                            binding.togFav.setOnClickListener{
                                viewModel.setFavoriteMovie(movie.data!!)
                            }
                            setFavorite(movie.data!!.favorite)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        } else if (type.equals("TVSHOW")) {
            viewModel.setSelectedTvShow(id)
            viewModel.getDetailTvShowById().observe(this,{ tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showLoading(false)
                            showDetailTvShow(tvShow.data)
                            binding.togFav.setOnClickListener{
                                viewModel.setFavoriteTvShow(tvShow.data!!)
                            }
                            setFavorite(tvShow.data!!.favorite)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
    }

    private fun showDetailMovie(result: MovieEntity?) {
        if (result != null) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(BuildConfig.IMAGE_URL + result.posterPath)
                    .into(imgPoster)
                tvName.text =
                    result.originalTitle + " (" + if (result.releaseDate != "") result.releaseDate?.substring(
                        0,
                        4
                    ) + ")" else "-)"
                tvRating.text = result.voteAverage.toString()
                tvDesc.text = result.overview
            }
        }
    }

    private fun showDetailTvShow(result: TvShowEntity?) {
        if (result != null) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(BuildConfig.IMAGE_URL + result.posterPath)
                    .into(imgPoster)
                tvName.text =
                    result.originalName + " (" + if (result.firstAirDate != "") result.firstAirDate?.substring(
                        0,
                        4
                    ) + ")" else "-)"
                tvRating.text = result.voteAverage.toString()
                tvDesc.text = result.overview
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state == true) {
            binding.togFav.isChecked = state
        } else {
            binding.togFav.isChecked = state
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}