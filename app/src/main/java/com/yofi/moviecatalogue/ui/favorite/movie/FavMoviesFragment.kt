package com.yofi.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.databinding.FragmentFavMovieBinding
import com.yofi.moviecatalogue.ui.favorite.FavoriteViewModel
import com.yofi.moviecatalogue.ui.main.MainViewModel
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory
import com.yofi.moviecatalogue.vo.Status

class FavMoviesFragment : Fragment(R.layout.fragment_fav_movie) {
    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: FavMovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavMovieBinding.bind(view)

        adapter = FavMovieAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.layoutManager = LinearLayoutManager(activity)
            rvMovie.adapter = adapter
        }
        viewModel()
    }

    private fun viewModel() {
        activity?.let {
            viewModel = ViewModelProvider(it, ViewModelFactory.getInstance(requireActivity()))[FavoriteViewModel::class.java]
        }

        viewModel.getFavMovie().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(listMovie)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}