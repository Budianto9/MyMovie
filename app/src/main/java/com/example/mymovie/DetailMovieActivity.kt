package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private lateinit var movieResponse: MovieResponse

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<MovieResponse>(EXTRA_MOVIE) as MovieResponse
        binding.detail = movie
    }
}