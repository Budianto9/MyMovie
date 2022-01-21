package com.example.mymovie.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

//    private fun initUi(){
//        movieAdapter = MovieAdapter()
//        binding.rvMovie.layoutManager = LinearLayoutManager(this)
//        binding.rvMovie.setHasFixedSize(true)
//        binding.rvMovie.adapter = movieAdapter
//
//
//    }
//
//    private fun observeView(){
//        lifecycleScope.launchWhenCreated {
//            viewModel.getMoviePopular().distinctUntilChanged().collectLatest {
//                movieAdapter.submitData(it)
//            }
//        }
//    }
}