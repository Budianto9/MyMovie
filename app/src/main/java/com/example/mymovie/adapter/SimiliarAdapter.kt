package com.example.mymovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.databinding.ListItemVerticalMovieBinding

class SimiliarAdapter : BaseAdapter<SimiliarAdapter.SimiliarViewHolder, MovieResponse>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimiliarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListItemVerticalMovieBinding.inflate(inflater, parent, false)
        return SimiliarViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimiliarViewHolder, position: Int) {
        val data = itemList[position]
        holder.bindTo(data)
    }

    override fun getItemCount(): Int = itemList.size

    inner class SimiliarViewHolder(private val binding: ListItemVerticalMovieBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindTo(movie: MovieResponse){
            binding.similiar = movie
            binding.executePendingBindings()
        }
    }
}