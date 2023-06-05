package parkee.technical.test.movieapp.data.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite")
data class MovieItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "originalTitle")
    @SerializedName("original_title")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "posterPath")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
) : Parcelable