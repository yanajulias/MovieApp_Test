package parkee.technical.test.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import parkee.technical.test.movieapp.R
import parkee.technical.test.movieapp.data.response.Result
import parkee.technical.test.movieapp.databinding.ItemListReviewMovieBinding

class ListReviewAdapter(
    private val data: MutableList<Result> = mutableListOf()
) : RecyclerView.Adapter<ListReviewAdapter.ResultViewHolder>() {

    fun setData(data: MutableList<Result>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class ResultViewHolder(private val binding: ItemListReviewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Result) {
            binding.authorId.text = items.author
            binding.authorReview.text = items.content
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultViewHolder = ResultViewHolder(
        ItemListReviewMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}