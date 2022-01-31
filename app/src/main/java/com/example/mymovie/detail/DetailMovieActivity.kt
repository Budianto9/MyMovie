package com.example.mymovie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.adapter.SimiliarAdapter
import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.databinding.ActivityDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var castAdapter: CastMovieAdapter
    private lateinit var similiarAdapter: SimiliarAdapter
    private val viewModel: DetailMovieViewModel by viewModel()
    private var movie: MovieResponse? = null

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra<MovieResponse>(EXTRA_MOVIE) as MovieResponse
        binding.detail = movie

        initUI()
        initObserver()
    }

    private fun initUI(){
        castAdapter = CastMovieAdapter()
        binding.rvCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCast.setHasFixedSize(true)
        binding.rvCast.adapter = castAdapter


        similiarAdapter = SimiliarAdapter()
        binding.rvSimiliar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSimiliar.setHasFixedSize(true)
        binding.rvSimiliar.adapter = similiarAdapter
    }

    private fun initObserver(){

        lifecycleScope.launchWhenCreated {
            viewModel.cast.collect{
                when(it){
                    is Resource.Loading -> ""
                    is Resource.Success -> castAdapter.initNewItems(it.data!!)
                    else -> ""
                }
            }
        }

        viewModel.getMovieCast(movie?.id!!)


        lifecycleScope.launchWhenCreated {
            viewModel.similiar.collect{
                when(it){
                    is Resource.Loading -> ""
                    is Resource.Success -> similiarAdapter.initNewItems(it.data!!)
                    else -> ""
                }
            }
        }

        viewModel.getSimiliarMovie(movie?.id!!)
    }
}