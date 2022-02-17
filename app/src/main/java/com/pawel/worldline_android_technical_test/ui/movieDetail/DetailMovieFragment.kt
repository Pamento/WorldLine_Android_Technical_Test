package com.pawel.worldline_android_technical_test.ui.movieDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.databinding.DetailMovieFragmentBinding
import com.pawel.worldline_android_technical_test.util.Consts.POSTER_URL
import com.pawel.worldline_android_technical_test.util.helpers.addComaInPrice
import com.pawel.worldline_android_technical_test.util.helpers.buildStringForCompanies
import com.pawel.worldline_android_technical_test.util.helpers.frenchFormatOfDate
import com.pawel.worldline_android_technical_test.util.helpers.getListSize
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

    @Inject lateinit var viewModel: DetailMovieViewModel
    private var _binding: DetailMovieFragmentBinding? = null
    private val binding get() = _binding!!
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
//        viewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(ApiHelperImpl(createNetworkService()))
//        )[DetailMovieViewModel::class.java]
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
        viewModel.movie.observe(viewLifecycleOwner) {
            it?.let {
                movie = it
                updateUITexts()
                updateUIImageView(
                    binding.detailMovieBackdrop.context,
                    "${POSTER_URL}w500/${movie.backdropPath}",
                    binding.detailMovieBackdrop
                )
                updateUIImageView(
                    binding.detailMoviePoster.context,
                    "${POSTER_URL}w300/${movie.posterPath}",
                    binding.detailMoviePoster
                )

            }
        }
    }

    private fun updateUIImageView(context: Context, url: String, into: AppCompatImageView) {
        Glide.with(context)
            .load(url)
            .error(R.drawable.img_not_found_square)
            .into(into)
    }

    private fun updateUITexts() {
        val companiesNumber: Int = movie.productionCompanies?.let {
            getListSize(it)
        } ?: 0
        binding.detailMovieTitle.text = movie.title
        binding.detailMovieOverview.text = movie.overview
        movie.releaseDate?.let {
            binding.detailMovieReleaseDateBody.text = frenchFormatOfDate(it)
        } ?: run {
            binding.detailMovieReleaseDateBody.visibility = View.GONE
            binding.detailMovieReleaseDateTitle.visibility = View.GONE
        }
        binding.detailMovieRatingBody.text = movie.voteAverage.toString()
        if (movie.budget!! > 0) {
            val budget = movie.budget.toString()
            binding.detailMovieBudgetBody.text =
                String.format(getString(R.string.budget_in_dolar), addComaInPrice(budget))
        } else {
            binding.detailMovieBudgetTitle.visibility = View.GONE
            binding.detailMovieBudgetBody.visibility = View.GONE
        }

        binding.detailMovieOriginalTitleBody.text = movie.originalTitle
        if (companiesNumber > 0) {
            val companies =
                if (companiesNumber == 1) movie.productionCompanies?.get(0)?.name
                else movie.productionCompanies?.let { buildStringForCompanies(it) }

            binding.detailMovieCompanyBody.text = companies.toString()
        } else {
            binding.detailMovieCompanyTitle.visibility = View.GONE
            binding.detailMovieCompanyBody.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}