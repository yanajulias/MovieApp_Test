package parkee.technical.test.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import parkee.technical.test.movieapp.data.response.BaseResponse
import parkee.technical.test.movieapp.data.response.MovieItem
import parkee.technical.test.movieapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _popularMovie = MutableLiveData<MutableList<MovieItem>>()
    val popularMovie: LiveData<MutableList<MovieItem>> = _popularMovie

    private val _topRatedMovie = MutableLiveData<MutableList<MovieItem>>()
    val topRatedMovie: LiveData<MutableList<MovieItem>> = _topRatedMovie

    private val _nowPlayingMovie = MutableLiveData<MutableList<MovieItem>>()
    val nowPlayingMovie: LiveData<MutableList<MovieItem>> = _nowPlayingMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetailPopularMovie(){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getPopularMovies()
        client.enqueue(object: Callback<BaseResponse> {
            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful){
                    _popularMovie.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getTopRatedMovie(){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getTopRatedMovies()
        client.enqueue(object: Callback<BaseResponse> {
            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful){
                    _topRatedMovie.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getNowPlayingMovie(){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getNowPlayingMovies()
        client.enqueue(object: Callback<BaseResponse>{
            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful){
                    _nowPlayingMovie.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}