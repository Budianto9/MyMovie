package com.example.mymovie.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovie.R
import com.example.mymovie.databinding.LoadingMoreBinding

class ItemLoadingAdapter(private val retry: () -> Unit) : LoadStateAdapter<ItemLoadingViewHolder>() {

    override fun onBindViewHolder(holder: ItemLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemLoadingViewHolder {
        return ItemLoadingViewHolder.create(parent)
    }
}

class ItemLoadingViewHolder(private val binding: LoadingMoreBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState, retry: () -> Unit) {
        with(binding) {

            btnRetry.setOnClickListener {
                retry()
            }

            if (imgLoading.isVisible)
                Glide.with(root.context)
                    .load(R.drawable.loading)
                    .into(imgLoading)

            if (loadState is LoadState.Loading) {
                motionLayout.transitionToEnd()
            } else {
                motionLayout.transitionToStart()
            }
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup): ItemLoadingViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val view = LoadingMoreBinding.inflate(inflater, viewGroup, false)
            return ItemLoadingViewHolder(view)
        }
    }
}