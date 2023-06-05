package parkee.technical.test.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import parkee.technical.test.movieapp.local.DbModule

class FavoriteViewModel(private val dbModule: DbModule) : ViewModel() {

    fun getMovieFavorite() = dbModule.movieDao.loadAll()

    class Factory(private val db: DbModule) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
    }
}