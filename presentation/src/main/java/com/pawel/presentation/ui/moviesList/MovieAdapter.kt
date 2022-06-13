package com.pawel.presentation.ui.moviesList

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pawel.domain.extentions.loadSimpleImg
import com.pawel.movieapp.presentation.R
import com.pawel.movieapp.presentation.databinding.ItemMovieBinding
import com.pawel.domain.model.movies.Result

class MovieAdapter(
    private val context: Context,
    private val onClickItem: OnMovieItemClickListener,
    private val getImageUrl: (endpoint: String) -> String
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(context, binding, onClickItem, this.getImageUrl)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun setItems(data: List<Result>) {
        movies = data.toMutableList()
        notifyItemInserted(movies.size)
    }
}

class MovieViewHolder(
    context: Context,
    private val view: ItemMovieBinding,
    private val onMovieItemClickListener: OnMovieItemClickListener,
    val getImageUrl: (endpoint: String) -> String
) :
    RecyclerView.ViewHolder(view.root), View.OnClickListener {

    private val adultStr: String = context.getString(R.string.concerned_public_adult)
    private val allPublic: String = context.getString(R.string.concerned_public_all)
    val drawable: Drawable =
        AppCompatResources.getDrawable(context, R.drawable.img_not_found_square)!!

    init {
        view.root.setOnClickListener(this)
    }

    private fun publicFor(bool: Boolean) = if (bool) adultStr else allPublic

    // url example: https://image.tmdb.org/t/p/w200//rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg

    fun bind(movie: Result) {
        val url = getImageUrl(movie.poster_path)
        view.itemTitle.text = movie.title
        view.itemConcernedPublic.text = publicFor(movie.adult)
        view.itemDescription.text = movie.overview
        view.itemMoviePoster.loadSimpleImg(url, drawable, Glide.with(view.itemMoviePoster.context))
    }

    override fun onClick(v: View?) {
        val position = absoluteAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            onMovieItemClickListener.onMovieItemClick(position)
        }
    }
}

interface OnMovieItemClickListener {
    fun onMovieItemClick(position: Int)
}
