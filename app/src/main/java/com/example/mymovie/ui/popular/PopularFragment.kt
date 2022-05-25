package com.example.mymovie.ui.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.databinding.FragmentPopularBinding
import com.example.mymovie.detail.DetailMovieActivity
import com.example.mymovie.main.MainViewModel
import com.example.mymovie.main.MovieAdapter
import com.example.mymovie.ui.base.BaseFragment
import com.example.mymovie.util.ItemLoadingAdapter
import com.kennyc.view.MultiStateView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : BaseFragment<FragmentPopularBinding>() {
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MainViewModel by viewModel()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPopularBinding
        get() = FragmentPopularBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            initUI()
            initObserve()
        }
    }

    override fun FragmentPopularBinding.initUI() {
        movieAdapter = MovieAdapter{
            val intent = Intent(requireContext(), DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }
        rvMovie.layoutManager = LinearLayoutManager(requireContext())
        rvMovie.setHasFixedSize(true)

        val footerLoadingAdapter = ItemLoadingAdapter{
            movieAdapter.retry()
        }
        rvMovie.adapter = movieAdapter.withLoadStateFooter(footerLoadingAdapter)

        lifecycleScope.launchWhenCreated {
            movieAdapter.loadStateFlow.collectLatest { state ->
                when {
                    state.source.refresh is LoadState.Loading -> {
                        layoutState.viewState = MultiStateView.ViewState.LOADING
                        proggerBarShimmer.visibility = View.VISIBLE
                    }
                    movieAdapter.itemCount < 1 -> {
                        layoutState.viewState = MultiStateView.ViewState.ERROR
                    }
                    else -> {
                        layoutState.viewState = MultiStateView.ViewState.CONTENT
                        proggerBarShimmer.visibility = View.GONE
                    }
                }
            }
        }
    }


    override fun FragmentPopularBinding.initObserve() {
        lifecycleScope.launchWhenCreated {
            viewModel.getMoviePopular().distinctUntilChanged().collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }




}