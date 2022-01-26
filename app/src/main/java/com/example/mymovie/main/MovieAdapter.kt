package com.example.mymovie.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.databinding.ListItemMovieBinding

class MovieAdapter(private val listener: ((MovieResponse) -> Unit)) :
    PagingDataAdapter<MovieResponse, MovieAdapter.MovieViewHolder>(movieDiffCallback){

    companion object {
        val movieDiffCallback = object : DiffUtil.ItemCallback<MovieResponse>() {
            override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                return oldItem == newItem
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.bindTo(data)
        }
    }


    inner class MovieViewHolder(private val binding: ListItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bindTo(item: MovieResponse){
                binding.item = item
                binding.executePendingBindings()


                binding.cardView.setOnClickListener {
                    listener.invoke(item)
                }
            }
        }
}