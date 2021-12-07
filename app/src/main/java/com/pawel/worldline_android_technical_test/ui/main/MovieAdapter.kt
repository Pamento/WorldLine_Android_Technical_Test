package com.pawel.worldline_android_technical_test.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.data.model.movies.Result
import com.pawel.worldline_android_technical_test.databinding.ItemMovieBinding
import com.pawel.worldline_android_technical_test.util.Consts.POSTER_URL

class MovieAdapter(private val context: Context): RecyclerView.Adapter<MovieViewHolder>() {

    var movies = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
//        with(holder) {
//            with(movies[position]) {
//
//            }
//        }
    }
    override fun getItemCount(): Int = movies.size

    fun setItems(data: List<Result>) {
        movies = data.toMutableList()
        notifyItemInserted(movies.size)
    }
}
class MovieViewHolder(context: Context, private val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {

    private val adultStr: String = context.getString(R.string.concerned_public_adult)
    private val allPublic: String = context.getString(R.string.concerned_public_all)

    private fun publicFor(bool: Boolean) = if (bool) adultStr else allPublic

    // url example: https://image.tmdb.org/t/p/w200//rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg
    private fun setUrl(endpoint: String?): String {
//        val urlBuilder = Uri.Builder()
//        urlBuilder.scheme("https")
//            .authority(POSTER_URL)
//            .appendPath("w200/")
//            .appendPath(endpoint)
//        urlStr = urlBuilder.toString()
        return "${POSTER_URL}w200/$endpoint"
    }
    fun bind(movie: Result) {
        val url = setUrl(movie.posterPath)
        Log.i("TAG", "bind: endpoint?:: ${movie.posterPath}")
        Log.i("TAG", "bind: URL?:: $url")
        view.itemTitle.text = movie.title
        view.itemConcernedPublic.text = publicFor(movie.isAdult)
        view.itemDescription.text = movie.overview
        view.itemDateProduction.text = movie.releaseDate
        Glide.with(view.itemMoviePoster.context)
            .load(url)
            .error(R.drawable.img_not_found_square)
            .placeholder(R.drawable.img_not_found_square)
            .into(view.itemMoviePoster)
    }
}