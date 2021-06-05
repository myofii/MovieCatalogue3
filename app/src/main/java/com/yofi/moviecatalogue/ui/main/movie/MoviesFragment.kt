package com.yofi.moviecatalogue.ui.main.movie

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.databinding.FragmentListMovieBinding
import com.yofi.moviecatalogue.ui.main.MainViewModel
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(R.layout.fragment_list_movie) {
    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentListMovieBinding.bind(view)

        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.layoutManager = LinearLayoutManager(activity)
            rvMovie.adapter = adapter

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
            viewModel.getSearchMovie(q).observe(viewLifecycleOwner,{
                if (it == ArrayList<ItemMovie>(0)) {
                    showLoading(false)
                    adapter.setListDataMovie(it)
                    binding.dataNotFound.visibility = View.VISIBLE
                } else if(it!=null) {
                    binding.dataNotFound.visibility = View.GONE
                    adapter.setListDataMovie(it)
                    showLoading(false)
                }
            })
        }
    }

    private fun viewModel() {
        activity?.let {
            viewModel = ViewModelProvider(it, ViewModelFactory.getInstance())[MainViewModel::class.java]
        }

        viewModel.getListMovie().observe(viewLifecycleOwner, { listMovie ->
            binding.rvMovie.adapter?.let { adapter ->
                when (adapter) {
                    is MovieAdapter -> adapter.setListDataMovie(listMovie)
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