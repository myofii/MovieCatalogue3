package com.yofi.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.databinding.FragmentFavTvshowBinding
import com.yofi.moviecatalogue.ui.favorite.FavoriteViewModel
import com.yofi.moviecatalogue.ui.main.MainViewModel
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory
import com.yofi.moviecatalogue.vo.Status

class FavTvShowFragment: Fragment(R.layout.fragment_fav_tvshow) {
    private var _binding : FragmentFavTvshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: FavTvShowAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavTvshowBinding.bind(view)

        adapter = FavTvShowAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvTvShow.setHasFixedSize(true)
            rvTvShow.layoutManager = LinearLayoutManager(activity)
            rvTvShow.adapter = adapter
        }
        viewModel()
    }

    private fun viewModel() {
        activity?.let {
            viewModel = ViewModelProvider(it, ViewModelFactory.getInstance(requireActivity()))[FavoriteViewModel::class.java]
        }

        viewModel.getFavTvShow().observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow != null) {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(listTvShow)
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}