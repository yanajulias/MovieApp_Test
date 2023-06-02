package parkee.technical.test.movieapp.data.retrofit

import parkee.technical.test.movieapp.data.response.MovieReviewsResponse
import parkee.technical.test.movieapp.data.response.NowPlayingResponse
import parkee.technical.test.movieapp.data.response.PopularMovieResponse
import parkee.technical.test.movieapp.data.response.TopRatedResponse
import parkee.technical.test.movieapp.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.POPULAR_MOVIE_ENDPOINT)
    fun getPopularMovies(): Call<PopularMovieResponse>

    @GET(Constants.TOP_RATED)
    fun getTopRatedMovies(): Call<TopRatedResponse>

    @GET(Constants.NOW_PLAYING)
    fun getNowPlayingMovies(): Call<NowPlayingResponse>

    @GET(Constants.MOVIE_REVIEWS)
    fun getMovieReviews(@Path("movieId") movieId: Int): Call<MovieReviewsResponse>

}