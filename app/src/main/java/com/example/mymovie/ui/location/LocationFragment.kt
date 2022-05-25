package com.example.mymovie.ui.location

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mymovie.databinding.FragmentLocationBinding
import com.example.mymovie.ui.base.BaseFragment

class LocationFragment : BaseFragment<FragmentLocationBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLocationBinding
        get() = FragmentLocationBinding::inflate

}