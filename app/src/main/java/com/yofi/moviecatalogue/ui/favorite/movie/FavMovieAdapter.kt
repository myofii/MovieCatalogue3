package com.yofi.moviecatalogue.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yofi.moviecatalogue.BuildConfig
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.databinding.ItemFilmBinding
import com.yofi.moviecatalogue.ui.detail.DetailActivity

class FavMovieAdapter: PagedListAdapter<MovieEntity, FavMovieAdapter.LinearViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val list = ArrayList<ItemMovie>()

    inner class LinearViewHolder(private val binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: MovieEntity){
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.IMAGE_URL + data.posterPath)
                    .into(imgPoster)
                tvName.text = data.originalTitle + " ("+if (data.releaseDate != "") data.releaseDate?.substring(0,4)+")" else "-)"
                tvRating.text = data.voteAverage.toString()

                itemView.setOnClickListener{
                    val moveIntent = Intent(itemView.context, DetailActivity::class.java)
                    moveIntent.putExtra(DetailActivity.EXTRA_ID, data.id)
                    moveIntent.putExtra(DetailActivity.EXTRA_TYPE, "MOVIE")
                    itemView.context.startActivity(moveIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinearViewHolder((view))
    }

    override fun onBindViewHolder(holder: LinearViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie!=null){
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int = list.size
}