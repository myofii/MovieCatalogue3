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
import com.yofi.moviecatalogue.ui.main.MainViewModel
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory
import com.yofi.moviecatalogue.vo.Status

class FavTvShowFragment: Fragment(R.layout.fragment_fav_tvshow) {
    private var _binding : FragmentFavTvshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
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
            viewModel = ViewModelProvider(it, ViewModelFactory.getInstance(requireActivity()))[MainViewModel::class.java]
        }

        viewModel.getListTvShow().observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(listTvShow.data)
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}