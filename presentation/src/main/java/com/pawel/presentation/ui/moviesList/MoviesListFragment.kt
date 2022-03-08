package com.pawel.presentation.ui.moviesList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawel.common.util.Constants.MOVIES_RECYCLER_VIEW_TAG
import com.pawel.presentation.ui.main.MainActivity
import com.pawel.presentation.ui.movieDetail.DetailMovieFragment
import com.pawel.worldline_android_technical_test.presentation.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment(), OnMovieItemClickListener {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private val viewModel: MoviesViewModel by viewModels()
    private var _binding : MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        adapter = MovieAdapter(requireContext(),this)
        setRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieObserver()
    }

    private fun setRecyclerView() {
        recyclerView = binding.listMoviesRV
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.tag = MOVIES_RECYCLER_VIEW_TAG
        recyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    binding.listMoviesRV.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setMovieObserver() {
        viewModel.movies.observe(viewLifecycleOwner) {
            it?.let {
                adapter.setItems(it)
            }
        }
    }

    override fun onMovieItemClick(position: Int) {
        Log.i("TAG", "onMovieItemClick: $position")
        val movieID = viewModel.movieId(position)
        movieID?.let {
            val mainActivity = requireActivity() as MainActivity
            mainActivity.replaceFragment(DetailMovieFragment.newInstance(it))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}