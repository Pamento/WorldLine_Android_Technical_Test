package com.pawel.presentation.ui.movieDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.pawel.domain.model.movie.Movie
import com.pawel.presentation.util.Consts.POSTER_URL
import com.pawel.domain.util.helpers.addComaInPrice
import com.pawel.domain.util.helpers.buildStringForCompanies
import com.pawel.domain.util.helpers.frenchFormatOfDate
import com.pawel.domain.util.helpers.getListSize
import com.pawel.presentation.EspressoIdlingResource
import com.pawel.presentation.helpers.ExtensionsErrors.showAlertDialog
import com.pawel.presentation.helpers.MoviesError
import com.pawel.presentation.helpers.SingleMovie
import com.pawel.movieapp.presentation.R
import com.pawel.movieapp.presentation.databinding.DetailMovieFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun newInstance(movieID: Int) = DetailMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID, movieID)
            }
        }
    }

    private val viewModel: DetailMovieViewModel by viewModels()
    private var _binding: ViewBinding? = null
    private val binding get() = _binding!! as DetailMovieFragmentBinding
    private var idOfMovie = -1
    private lateinit var movie: Movie

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt(MOVIE_ID)?.let {
            idOfMovie = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (idOfMovie > -1) {
            viewModel.getMovie(idOfMovie.toString())
            setOnMovieResponseObserver()
        }
    }

    private fun setOnMovieResponseObserver() {
        /**
         * EspressoIdlingResource...() utility for AndroidTest
         */
        EspressoIdlingResource.increment()
        viewModel.networkResponse.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { result ->
                when (result) {
                    is SingleMovie -> {
                        movie = result.movie
                        updateUITexts()
                        updateUIImageView(
                            binding.detailMovieBackdrop.context,
                            "${POSTER_URL}w500/${movie.backdrop_path}",
                            binding.detailMovieBackdrop
                        )
                        updateUIImageView(
                            binding.detailMoviePoster.context,
                            "${POSTER_URL}w300/${movie.poster_path}",
                            binding.detailMoviePoster
                        )
                    }
                    is MoviesError -> {
                        displayNoDataMessage()
                        context?.showAlertDialog(result.error)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun displayNoDataMessage() {
        binding.detailMovieContainer.removeAllViews()
        val noDataToDisplay = LayoutInflater.from(requireContext()).inflate(R.layout.view_no_data_to_display, binding.detailMovieContainer, false)
        binding.detailMovieContainer.addView(noDataToDisplay)
    }

    private fun updateUIImageView(context: Context, url: String, into: AppCompatImageView) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.img_not_found_square)
            .error(R.drawable.img_not_found_square)
            .into(into)
    }

    private fun updateUITexts() {
        val companiesNumber: Int = getListSize(movie.production_companies)
        binding.detailMovieTitle.text = movie.title
        binding.detailMovieOverview.text = movie.overview
        movie.release_date.let {
            binding.detailMovieReleaseDateBody.text = frenchFormatOfDate(it)
        }
        binding.detailMovieRatingBody.text = movie.vote_average.toString()
        if (movie.budget > 0) {
            val budget = movie.budget.toString()
            binding.detailMovieBudgetBody.text =
                String.format(getString(R.string.budget_in_dolar), addComaInPrice(budget))
        } else {
            binding.detailMovieBudgetTitle.visibility = View.GONE
            binding.detailMovieBudgetBody.visibility = View.GONE
        }

        binding.detailMovieOriginalTitleBody.text = movie.original_title
        if (companiesNumber > 0) {
            val companies =
                if (companiesNumber == 1) movie.production_companies[0].name
                else buildStringForCompanies(movie.production_companies)

            binding.detailMovieCompanyBody.text = companies.toString()
        } else {
            binding.detailMovieCompanyTitle.visibility = View.GONE
            binding.detailMovieCompanyBody.visibility = View.GONE
        }
        EspressoIdlingResource.decrement()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}