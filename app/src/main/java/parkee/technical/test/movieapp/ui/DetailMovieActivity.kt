package parkee.technical.test.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import parkee.technical.test.movieapp.adapter.ListReviewAdapter
import parkee.technical.test.movieapp.data.response.MovieDetailsResponse
import parkee.technical.test.movieapp.data.response.Result
import parkee.technical.test.movieapp.databinding.ActivityDetailMovieBinding
import parkee.technical.test.movieapp.util.Constants
import parkee.technical.test.movieapp.viewmodel.DetailMovieViewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private val detailMovieViewModel by viewModels<DetailMovieViewModel>()
    private val adapterReview by lazy {
        ListReviewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        detailMovieBinding.apply {
            rvListResult.layoutManager = LinearLayoutManager(applicationContext)
            rvListResult.setHasFixedSize(true)
        }

        val movieId = intent.getIntExtra("movie_id", 0)
        detailMovieViewModel.apply {
            getReviewMovieDetail(movieId)
            getMovieDetail(movieId)
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

}

