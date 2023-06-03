package parkee.technical.test.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import parkee.technical.test.movieapp.data.response.MovieDetailsResponse
import parkee.technical.test.movieapp.data.response.MovieReviewsResponse
import parkee.technical.test.movieapp.data.response.Result
import parkee.technical.test.movieapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetailsResponse>()
    val movieDetail: LiveData<MovieDetailsResponse> = _movieDetail

    private val _reviewMovieDetail = MutableLiveData<MutableList<Result>>()
    val reviewMovieDetail: LiveData<MutableList<Result>> = _reviewMovieDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getReviewMovieDetail(movieId: Int) {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getMovieReviews(movieId)
        client.enqueue(object : Callback<MovieReviewsResponse> {
            override fun onResponse(
                call: Call<MovieReviewsResponse>,
                response: Response<MovieReviewsResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    _reviewMovieDetail.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieReviewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getMovieDetail(movieId: Int) {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getDetailMovies(movieId)
        client.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    _movieDetail.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "DetailMovieActivity"
    }
}