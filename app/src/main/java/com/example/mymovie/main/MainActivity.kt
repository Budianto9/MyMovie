package com.example.mymovie.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.databinding.ActivityMainBinding
import com.example.mymovie.util.ItemLoadingAdapter
import com.kennyc.view.MultiStateView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        observeView()
    }

    private fun initUi(){
        movieAdapter = MovieAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
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