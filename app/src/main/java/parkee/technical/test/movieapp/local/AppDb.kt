package parkee.technical.test.movieapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import parkee.technical.test.movieapp.data.response.MovieItem

@Database(entities = [MovieItem::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}