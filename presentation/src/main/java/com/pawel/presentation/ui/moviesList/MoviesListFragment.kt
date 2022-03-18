package com.pawel.presentation.ui.moviesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawel.common.networkErrorHandling.MovieException
import com.pawel.presentation.EspressoIdlingResource
import com.pawel.presentation.helpers.ExtensionsErrors.showAlertDialog
import com.pawel.presentation.helpers.MoviesError
import com.pawel.presentation.helpers.MoviesList
import com.pawel.presentation.ui.main.MainActivity
import com.pawel.presentation.ui.movieDetail.DetailMovieFragment
import com.pawel.movieapp.presentation.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment(), OnMovieItemClickListener {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private val viewModel: MoviesViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter(requireContext(), this)
        setRecyclerView()
        setMovieObserver()
    }

    private fun setRecyclerView() {
        recyclerView = binding.listMoviesRV
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun setMovieObserver() {
        /**
         * EspressoIdlingResource...() utility for AndroidTest
         */
        EspressoIdlingResource.increment()
        viewModel.networkResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is MoviesList -> adapter.setItems(result.movies)
                is MoviesError -> {
                    displayNoDataMessage()
                    viewModel.error.getContentIfNotHandled()?.let {
                        showAlertDialog(it)
                    }
                }
                else -> {}
            }
            EspressoIdlingResource.decrement()
        }
    }

    private fun showAlertDialog(error: MovieException) {
        context?.showAlertDialog(error)
    }

    private fun displayNoDataMessage() {
        binding.listMovieNoDataMessage.viewNoDataToDisplay.visibility = View.VISIBLE
    }

    override fun onMovieItemClick(position: Int) {
        val movieID = viewModel.movieId(position)
        movieID.let {
            val mainActivity = requireActivity() as MainActivity
            mainActivity.replaceFragment(DetailMovieFragment.newInstance(it))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}