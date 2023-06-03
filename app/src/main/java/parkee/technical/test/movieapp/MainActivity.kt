package parkee.technical.test.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import parkee.technical.test.movieapp.adapter.ListPopularMovieAdapter
import parkee.technical.test.movieapp.adapter.ListTopRatedAdapter
import parkee.technical.test.movieapp.data.response.MovieItem
import parkee.technical.test.movieapp.databinding.ActivityMainBinding
import parkee.technical.test.movieapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val adapter by lazy {
        ListPopularMovieAdapter {
            Intent(this, DetailMovieActivity::class.java).apply {
                putExtra("movie_id", it.id)
                startActivity(this)
            }
        }
    }
    private val adapterTopRated by lazy{
        ListTopRatedAdapter {
            Intent(this, DetailMovieActivity::class.java).apply {
                putExtra("movie_id", it.id)
                startActivity(this)
            }
        }
    }
    private val adapterNowPlaying by lazy{
        ListTopRatedAdapter {
            Intent(this, DetailMovieActivity::class.java).apply {
                putExtra("movie_id", it.id)
                startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.apply {
            getDetailPopularMovie()
            getTopRatedMovie()
            getNowPlayingMovie()
        }

        supportActionBar?.hide()

        binding.apply {
            rvPopularMovie.layoutManager =
                LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
            rvPopularMovie.setHasFixedSize(true)

            rvTopRatedMovie.layoutManager =
                LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
            rvTopRatedMovie.setHasFixedSize(true)

            rvNowPlaying.layoutManager =
                LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
            rvNowPlaying.setHasFixedSize(true)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.popularMovie.observe(this) {
            setPopularMovie(it)
        }

        mainViewModel.topRatedMovie.observe(this){
            setTopRatedMovie(it)
        }

        mainViewModel.nowPlayingMovie.observe(this){
            setNowPlayingMovie(it)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setPopularMovie(listPopularMovie: MutableList<MovieItem>) {
        val adapter = adapter
        adapter.setData(listPopularMovie)
        binding.rvPopularMovie.adapter = adapter
    }

    private fun setTopRatedMovie(listTopRatedMovie: MutableList<MovieItem>) {
        val adapter = adapterTopRated
        adapter.setData(listTopRatedMovie)
        binding.rvTopRatedMovie.adapter = adapter
    }

    private fun setNowPlayingMovie(listNowPlayingMovie: MutableList<MovieItem>) {
        val adapter = adapterNowPlaying
        adapter.setData(listNowPlayingMovie)
        binding.rvNowPlaying.adapter = adapter
    }
}