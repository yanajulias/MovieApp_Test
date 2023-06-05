package parkee.technical.test.movieapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import parkee.technical.test.movieapp.data.response.MovieItem
import retrofit2.http.DELETE

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieItem)

    @Query("SELECT * FROM favorite")
    fun loadAll(): LiveData<MutableList<MovieItem>>

    @Query("SELECT * FROM favorite WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): MovieItem

    @Delete
    fun delete(movie: MovieItem)

}