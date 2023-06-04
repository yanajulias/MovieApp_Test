package parkee.technical.test.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import parkee.technical.test.movieapp.databinding.ActivityFavoriteActivityBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteActivityBinding: ActivityFavoriteActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteActivityBinding = ActivityFavoriteActivityBinding.inflate(layoutInflater)
        setContentView(favoriteActivityBinding.root)


    }
}