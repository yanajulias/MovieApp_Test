package parkee.technical.test.movieapp.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: MutableList<MovieItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
//    @SerializedName("dates")
//    val dates: Dates
)

//data class Dates(
//    @SerializedName("maximum")
//    val maximum: String,
//    @SerializedName("minimum")
//    val minimum: String
//)