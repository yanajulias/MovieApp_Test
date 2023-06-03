package parkee.technical.test.movieapp.util

class Constants {

    companion object{
        private const val API_KEY = "a0dd7e223fadf4e7bfa1b7eebe381267"
        const val BEARER = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMGRkN2UyMjNmYWRmNGU3YmZhMWI3ZWViZTM4MTI2NyIsInN1YiI6IjY0NzZkMGVlMjU1ZGJhMDBjNjJmZTU2ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zE-nm-QCLksJSFJOlODVavSmYKQoMY3gGGCSn1n7Cuw"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
        const val POPULAR_MOVIE_ENDPOINT = "movie/popular?api_key=$API_KEY"
        const val TOP_RATED = "movie/top_rated?api_key=$API_KEY"
        const val NOW_PLAYING = "movie/now_playing?api_key=$API_KEY"
        const val MOVIE_REVIEWS = "movie/{movieId}/reviews?api_key=$API_KEY"
        const val MOVIE_DETAILS = "movie/{movieId}?api_key=$API_KEY"
    }
}