package com.pawel.worldline_android_technical_test.ui.detailMovie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pawel.worldline_android_technical_test.data.api.ApiHelperImpl
import com.pawel.worldline_android_technical_test.data.api.createNetworkService
import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.databinding.DetailMovieFragmentBinding
import com.pawel.worldline_android_technical_test.di.ViewModelFactory

class DetailMovieFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun newInstance(movieID: Int) = DetailMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID, movieID)
            }
        }
    }

    private lateinit var viewModel: DetailMovieViewModel
    private var binding: DetailMovieFragmentBinding? = null
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
        binding = DetailMovieFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelperImpl(createNetworkService()))
        )[DetailMovieViewModel::class.java]
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (idOfMovie > -1) {
            viewModel.getMovie(idOfMovie.toString())
            setOnMovieResponseObserver()
        }
    }

    private fun setOnMovieResponseObserver() {
        viewModel.movie.observe(viewLifecycleOwner, {
            if (it != null) {
                movie = it
                updateUI()
            }
        })
    }

    private fun updateUI() {
        // TODO make xml file and bind it with movie.
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}