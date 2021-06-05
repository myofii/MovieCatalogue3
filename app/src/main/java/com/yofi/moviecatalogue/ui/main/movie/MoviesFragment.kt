package com.yofi.moviecatalogue.ui.main.movie

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.databinding.FragmentListMovieBinding
import com.yofi.moviecatalogue.ui.main.MainViewModel
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory
import com.yofi.moviecatalogue.vo.Status

class MoviesFragment : Fragment(R.layout.fragment_list_movie) {
    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()

        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.layoutManager = LinearLayoutManager(activity)
            rvMovie.adapter = movieAdapter

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
            viewModel.getSearchMovie(q).observe(viewLifecycleOwner,{ listMovie ->
                if (listMovie != null) {
                    when (listMovie.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(listMovie.data)
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

        viewModel.getListMovie().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.submitList(listMovie.data)
                        Log.d("coba2",listMovie.data.toString())
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