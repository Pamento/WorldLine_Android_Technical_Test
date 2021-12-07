package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawel.worldline_android_technical_test.MainActivity
import com.pawel.worldline_android_technical_test.data.api.ApiHelperImpl
import com.pawel.worldline_android_technical_test.data.api.createNetworkService
import com.pawel.worldline_android_technical_test.databinding.MainFragmentBinding
import com.pawel.worldline_android_technical_test.di.ViewModelFactory
import com.pawel.worldline_android_technical_test.ui.detailMovie.DetailMovieFragment

class MoviesListFragment : Fragment(), OnMovieItemClickListener {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private lateinit var viewModel: MoviesViewModel
    private var binding : MainFragmentBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelperImpl(createNetworkService()))
        )[MoviesViewModel::class.java]
        adapter = MovieAdapter(requireContext(),this)
        setRecyclerView()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieObserver()
    }

    private fun setRecyclerView() {
        recyclerView = (binding?.listMoviesRV) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    binding?.listMoviesRV?.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setMovieObserver() {
        viewModel.movies.observe(viewLifecycleOwner,{
            it?.let {
                adapter.setItems(it)
            }
        })
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
        binding = null
        super.onDestroyView()
    }
}