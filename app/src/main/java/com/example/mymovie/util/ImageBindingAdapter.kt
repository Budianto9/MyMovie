package com.example.mymovie.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mymovie.core.util.Constant

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imgUrl")
    fun setImageUrl(view: ImageView, url: String?) {

        if (url == null) return

        Glide.with(view.context)
            .load(Constant.IMAGE_URL + url)
            .into(view)
    }
}