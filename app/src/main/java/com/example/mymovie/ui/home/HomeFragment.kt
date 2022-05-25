package com.example.mymovie.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentHomeBinding
import com.example.mymovie.detail.DetailMovieActivity
import com.example.mymovie.main.MainViewModel
import com.example.mymovie.main.MovieAdapter
import com.example.mymovie.ui.base.BaseFragment
import com.example.mymovie.util.ItemLoadingAdapter
import com.kennyc.view.MultiStateView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MainViewModel by viewModel()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        observeView()
    }

    private fun initUi(){
        movieAdapter = MovieAdapter{
            val intent = Intent(requireContext(), DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovie.setHasFixedSize(true)

        val footerLoadingAdapter = ItemLoadingAdapter{
            movieAdapter.retry()
        }
        binding.rvMovie.adapter = movieAdapter.withLoadStateFooter(footerLoadingAdapter)

        lifecycleScope.launchWhenCreated {
            movieAdapter.loadStateFlow.collectLatest { state ->
                when {
                    state.source.refresh is LoadState.Loading -> {
                        binding.layoutState.viewState = MultiStateView.ViewState.LOADING
                        binding.proggerBarShimmer.visibility = View.VISIBLE
                    }
                    movieAdapter.itemCount < 1 -> {
                        binding.layoutState.viewState = MultiStateView.ViewState.ERROR
                    }
                    else -> {
                        binding.layoutState.viewState = MultiStateView.ViewState.CONTENT
                        binding.proggerBarShimmer.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun observeView(){
        lifecycleScope.launchWhenCreated {
            viewModel.getMoviePopular().distinctUntilChanged().collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }

}