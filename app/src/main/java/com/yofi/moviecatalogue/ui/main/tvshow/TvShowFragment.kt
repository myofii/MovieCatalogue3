package com.yofi.moviecatalogue.ui.main.tvshow

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.databinding.FragmentListTvshowBinding
import com.yofi.moviecatalogue.ui.main.MainViewModel
import com.yofi.moviecatalogue.viewmodel.ViewModelFactory

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
            viewModel.getSearchTvShow(q).observe(viewLifecycleOwner,{
                if (it == ArrayList<ItemTvShow>(0)) {
                    showLoading(false)
                    adapter.setListDataTvShow(it)
                    binding.dataNotFound.visibility = View.VISIBLE
                } else if(it!=null) {
                    binding.dataNotFound.visibility = View.GONE
                    adapter.setListDataTvShow(it)
                    showLoading(false)
                }
            })
        }
    }

    private fun viewModel() {
        activity?.let {
            viewModel = ViewModelProvider(it, ViewModelFactory.getInstance())[MainViewModel::class.java]
        }

        viewModel.getListTvShow().observe(viewLifecycleOwner, { listTvShow ->
            binding.rvTvShow.adapter?.let { adapter ->
                when (adapter) {
                    is TvShowAdapter -> adapter.setListDataTvShow(listTvShow)
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