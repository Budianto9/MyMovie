package com.example.mymovie.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymovie.databinding.FragmentFavoriteBinding
import com.example.mymovie.ui.base.BaseFragment


class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

}