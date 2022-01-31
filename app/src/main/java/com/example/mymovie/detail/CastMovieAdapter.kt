package com.example.mymovie.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.adapter.BaseAdapter
import com.example.mymovie.core.data.source.remote.response.Cast
import com.example.mymovie.databinding.ItemListActorBinding

class CastMovieAdapter : BaseAdapter<CastMovieAdapter.CastViewHolder, Cast>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemListActorBinding.inflate(inflater, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val data = itemList[position]
        holder.bindTo(data)
    }

    override fun getItemCount(): Int = itemList.size

    inner class CastViewHolder(private val binding: ItemListActorBinding): RecyclerView.ViewHolder(binding.root){

        fun bindTo(cast: Cast){
            binding.cast = cast
            binding.executePendingBindings()
        }
    }
}