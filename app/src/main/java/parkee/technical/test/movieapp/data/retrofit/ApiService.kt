package parkee.technical.test.movieapp.data.retrofit

import parkee.technical.test.movieapp.data.response.BaseResponse
import parkee.technical.test.movieapp.data.response.MovieDetailsResponse
import parkee.technical.test.movieapp.data.response.MovieReviewsResponse
import parkee.technical.test.movieapp.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.POPULAR_MOVIE_ENDPOINT)
    fun getPopularMovies(): Call<BaseResponse>

    @GET(Constants.TOP_RATED)
    fun getTopRatedMovies(): Call<BaseResponse>

    @GET(Constants.NOW_PLAYING)
    fun getNowPlayingMovies(): Call<BaseResponse>

    @GET(Constants.MOVIE_DETAILS)
    fun getDetailMovies(@Path("movieId") movieId: Int): Call<MovieDetailsResponse>

    @GET(Constants.MOVIE_REVIEWS)
    fun getMovieReviews(@Path("movieId") movieId: Int): Call<MovieReviewsResponse>
}