package parkee.technical.test.movieapp.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DbModule(private val mContext : Context) {
    private val db = Room.databaseBuilder(mContext, AppDb::class.java, "favoriteMovie.db")
        .allowMainThreadQueries()
        .build()

    val movieDao = db.movieDao()
}