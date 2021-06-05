package com.yofi.moviecatalogue.ui.main.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yofi.moviecatalogue.BuildConfig
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.databinding.ItemFilmBinding
import com.yofi.moviecatalogue.ui.detail.DetailActivity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.LinearViewHolder>() {
    private val list = ArrayList<ItemMovie>()

    fun setListDataMovie(data: List<ItemMovie>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class LinearViewHolder(val binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ItemMovie){
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
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}