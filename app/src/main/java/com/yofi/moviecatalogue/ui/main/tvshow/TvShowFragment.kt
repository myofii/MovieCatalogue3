package com.yofi.moviecatalogue.ui.main.tvshow

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.databinding.FragmentListTvshowBinding
import com.yofi.moviecatalogue.ui.main.MainViewModel
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory
import com.yofi.moviecatalogue.vo.Status

class TvShowFragment: Fragment(R.layout.fragment_list_tvshow) {
    private var _binding : FragmentListTvshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TvShowAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentListTvshowBinding.bind(view)

        adapter = TvShowAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvTvShow.setHasFixedSize(true)
            rvTvShow.layoutManager = LinearLayoutManager(activity)
            rvTvShow.adapter = adapter

            btnSearch.setOnClickListener {
                searchMovie()
            }

            etSearch.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    searchMovie()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        viewModel()
    }

    private fun searchMovie(){
        binding.apply {
            val q = etSearch.text.toString()
            if (q.isEmpty()){
                viewModel()
                return
            }

            showLoading(true)
            viewModel.getSearchTvShow(q).observe(viewLifecycleOwner,{ listTvShow ->
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
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.dataNotFound.visibility = View.VISIBLE
                }
            })
        }
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

    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}