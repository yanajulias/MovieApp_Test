package parkee.technical.test.movieapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import parkee.technical.test.movieapp.adapter.ListFavoriteAdapter
import parkee.technical.test.movieapp.data.response.MovieItem
import parkee.technical.test.movieapp.databinding.ActivityFavoriteActivityBinding
import parkee.technical.test.movieapp.local.DbModule
import parkee.technical.test.movieapp.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteActivityBinding: ActivityFavoriteActivityBinding
    private val adapter by lazy {
        ListFavoriteAdapter {
            Intent(this, DetailMovieActivity::class.java).apply {
                putExtra("movie_id", it)
                startActivity(this)
            }
        }
    }

    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModel.Factory(DbModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteActivityBinding = ActivityFavoriteActivityBinding.inflate(layoutInflater)
        setContentView(favoriteActivityBinding.root)

        favoriteActivityBinding.apply {
            rvFavoriteList.layoutManager =
                LinearLayoutManager(applicationContext)
            rvFavoriteList.setHasFixedSize(true)
        }

        favoriteViewModel.getMovieFavorite().observe(this){
            setFavoriteList(it)
        }
    }

    private fun setFavoriteList(listResult: MutableList<MovieItem>) {
        favoriteActivityBinding.apply {
            val adapter = adapter
            adapter.setData(listResult)
            favoriteActivityBinding.rvFavoriteList.adapter = adapter
        }
    }

}