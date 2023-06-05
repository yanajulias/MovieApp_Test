package parkee.technical.test.movieapp.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.floatingactionbutton.FloatingActionButton
import parkee.technical.test.movieapp.R
import parkee.technical.test.movieapp.adapter.ListReviewAdapter
import parkee.technical.test.movieapp.data.response.MovieDetailsResponse
import parkee.technical.test.movieapp.data.response.MovieItem
import parkee.technical.test.movieapp.data.response.Result
import parkee.technical.test.movieapp.databinding.ActivityDetailMovieBinding
import parkee.technical.test.movieapp.local.DbModule
import parkee.technical.test.movieapp.util.Constants
import parkee.technical.test.movieapp.viewmodel.DetailMovieViewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private val detailMovieViewModel by viewModels<DetailMovieViewModel> {
        DetailMovieViewModel.Factory(DbModule(this))
    }
    private val adapterReview by lazy {
        ListReviewAdapter()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        //val movieId = intent.getParcelableExtra("movie_id", MovieItem::class.java)
        val movieId = intent.getParcelableExtra<MovieItem>("movie_id")

        val url = Constants.MOVIE_URL + movieId?.id

        detailMovieBinding.apply {
            rvListResult.layoutManager = LinearLayoutManager(applicationContext)
            rvListResult.setHasFixedSize(true)
            btnShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra("Share this..", url)

                val chooser = Intent.createChooser(intent, "Share using..")
                startActivity(chooser)
            }

            btnFavorite.setOnClickListener {
                if (movieId != null) {
                    detailMovieViewModel.setFavoriteMovie(movieId)
                }
            }
        }

        detailMovieViewModel.apply {
            if (movieId != null) {
                getReviewMovieDetail(movieId.id)
            }
            if (movieId != null) {
                getMovieDetail(movieId.id)
            }
        }

        detailMovieViewModel.movieDetail.observe(this) {
            setImageDetailMovie(it)
        }

        detailMovieViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        detailMovieViewModel.reviewMovieDetail.observe(this) {
            setReviewMovie(it)
        }

        movieId?.id?.let {
            detailMovieViewModel.findFavorite(it) {
                detailMovieBinding.btnFavorite.changeIconColor(R.color.red)
            }
        }

        detailMovieViewModel.favoritedMovie.observe(this) {
            detailMovieBinding.btnFavorite.changeIconColor(R.color.red)
        }

        detailMovieViewModel.deletedFavoriteMovie.observe(this) {
            detailMovieBinding.btnFavorite.changeIconColor(R.color.white)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            detailMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailMovieBinding.progressBar.visibility = View.GONE
        }
    }

    private fun setImageDetailMovie(movieDetail: MovieDetailsResponse) {
        detailMovieBinding.apply {
            imgDetailMovie.load(Constants.IMAGE_URL + movieDetail.posterPath)
            txtMovieDetailTitle.text = movieDetail.title
            txtOriginalDetailTitle.text = movieDetail.status
            txtDetailDescription.text = movieDetail.overview
        }
    }

    private fun setReviewMovie(listResult: MutableList<Result>) {
        detailMovieBinding.apply {
            val adapter = adapterReview
            adapter.setData(listResult)
            detailMovieBinding.rvListResult.adapter = adapter
        }
    }

    private fun FloatingActionButton.changeIconColor(@ColorRes color: Int) {
        imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this.context, color))
    }
}

