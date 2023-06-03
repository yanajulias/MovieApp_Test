package parkee.technical.test.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import parkee.technical.test.movieapp.data.response.MovieItem
import parkee.technical.test.movieapp.databinding.ItemPopularMovieBinding
import parkee.technical.test.movieapp.util.Constants

class ListPopularMovieAdapter(
    private val data: MutableList<MovieItem> = mutableListOf(),
    private val listener: (MovieItem) -> Unit
) : RecyclerView.Adapter<ListPopularMovieAdapter.MovieViewHolder>() {

    fun setData(data: MutableList<MovieItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class MovieViewHolder(private val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: MovieItem) {
            binding.imgPopularMovie.load(Constants.IMAGE_URL + items.posterPath)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder = MovieViewHolder(
        ItemPopularMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = data.size
}